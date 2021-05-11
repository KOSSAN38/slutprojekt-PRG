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

    }
    public void newRacket(){

    }
    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0, this);
    }
    public void draw(Graphics g){

    }
    public void move(){

    }
    public void checkCol(){

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

        }
        public void keyRealese(KeyEvent e){

        }
    }
}
