import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Boll extends Rectangle{


    Random random;
    int xSpeed;
    int ySpeed;
    int speedBoll = 2;

    Boll(int x, int y, int width, int height){
        super(x,y,width,height);
        random = new Random();
        int randomXDir = random.nextInt(2);
        if (randomXDir== 0)
            randomXDir--;
        setXDir(randomXDir*speedBoll);

        int randomYDir = random.nextInt(2);
        if (randomYDir== 0)
            randomYDir--;
        setYDir(randomYDir*speedBoll);
    }
    public void setXDir(int randomXDir){
        xSpeed = randomXDir;
    }
    public void setYDir(int randomYDir){
        ySpeed = randomYDir;
    }
    public void move(){
        x+=xSpeed;
        y+=ySpeed;
    }
    public void draw(Graphics g){
        g.setColor(Color.black);
        g.fillOval(x,y,height,width);
    }


}
