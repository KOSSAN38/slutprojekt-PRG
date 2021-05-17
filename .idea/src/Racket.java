import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Racket extends Rectangle{

    int id;
    int ySpeed;
    int fast = 10;


    Racket(int x, int y, int RACKET_WIDTH, int RACKET_HEIGHT,int id){
        super(x,y,RACKET_WIDTH, RACKET_HEIGHT);
        this.id = id;
    }
    public void keyPress(KeyEvent e){
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDir(-fast);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDir(fast);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDir(-fast);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDir(fast);
                    move();
                }
                break;
        }
    }
    public void keyRealese(KeyEvent e){
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDir(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDir(0);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDir(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDir(0);
                    move();
                }
                break;
        }
    }
    public void setYDir(int yDir){
        ySpeed = yDir;
    }
    public void move(){
        y= y+ySpeed;
    }
    public void draw(Graphics g){
        if(id == 1)
            g.setColor(Color.red);
        else
            g.setColor(Color.green);
        g.fillRect(x,y, width,height);
    }
}
