import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Panel extends JPanel implements Runnable{

    static final int GAME_WIDTH = 1200;
    static final int GAME_HEIGHT = 1000;
    static final Dimension SCEEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_SIZE = 25;
    static final int RACKET_WIDTH = 10;
    static final int RACKET_HEIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Racket racket1;
    Racket racket2;
    Boll boll;
    Score score;

    Panel(){
        newRacket();
        newBoll();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new actionL());
        this.setPreferredSize(SCEEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }
    public void newBoll(){
        random = new Random();
        boll = new Boll((GAME_WIDTH/2)- (BALL_SIZE/2), (GAME_HEIGHT/2) - (BALL_SIZE/2), BALL_SIZE, BALL_SIZE);
    }
    public void newRacket(){
        racket1 = new Racket(0, (GAME_HEIGHT/2)-(RACKET_HEIGHT/2),RACKET_WIDTH, RACKET_HEIGHT,1);
        racket2 = new Racket(GAME_WIDTH-RACKET_WIDTH, (GAME_HEIGHT/2)-(RACKET_HEIGHT/2),RACKET_WIDTH, RACKET_HEIGHT,2);
    }
    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0, this);
    }
    public void draw(Graphics g){
        racket1.draw(g);
        racket2.draw(g);
        boll.draw(g);
        score.draw(g);
    }
    public void move(){
        racket1.move();
        racket2.move();
        boll.move();
    }
    public void checkCol() {

        if (boll.y <= 0) {
            boll.setYDir(-boll.ySpeed);
        }
        if (boll.y >= GAME_HEIGHT - BALL_SIZE){
            boll.setYDir(-boll.ySpeed);
    }

        if (boll.intersects(racket1)){
            boll.xSpeed = Math.abs(boll.xSpeed);
            boll.xSpeed++;//svårare snabbare boll
            if (boll.ySpeed>0)
                boll.ySpeed++;//svårare snabbare bolll
            else
                boll.ySpeed--;
            boll.setXDir(boll.xSpeed);
            boll.setYDir(boll.ySpeed);
        }
        if (boll.intersects(racket2)){
            boll.xSpeed = Math.abs(boll.xSpeed);
            boll.xSpeed++;//svårare snabbare boll
            if (boll.ySpeed>0)
                boll.ySpeed++;//svårare snabbare bolll
            else
                boll.ySpeed--;
            boll.setXDir(-boll.xSpeed);
            boll.setYDir(boll.ySpeed);
        }

        if (racket1.y<=0)
            racket1.y=0;
        if (racket1.y>= (GAME_HEIGHT-RACKET_HEIGHT))
            racket1.y=GAME_HEIGHT-RACKET_WIDTH;
        if (racket2.y<=0)
            racket2.y=0;
        if (racket2.y>= (GAME_HEIGHT-RACKET_HEIGHT))
            racket2.y=GAME_HEIGHT-RACKET_WIDTH;

        //Ge spelare point
        if (boll.x <= 0){
            score.player2++;
            newRacket();
            newBoll();
        }
        if (boll.x >= GAME_WIDTH-BALL_SIZE){
            score.player1++;
            newRacket();
            newBoll();
        }

    }
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double nanosec = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now -lastTime)/nanosec;
            lastTime = now;
            if (delta >=1){
                move();
                checkCol();
                repaint();
                delta--;

            }
        }

    }
    public class actionL extends KeyAdapter{
        public void keyPress(KeyEvent e){
            racket1.keyPress(e);
            racket2.keyPress(e);
        }
        public void keyRealese(KeyEvent e){
            racket1.keyRealese(e);
            racket2.keyRealese(e);
        }
    }
}
