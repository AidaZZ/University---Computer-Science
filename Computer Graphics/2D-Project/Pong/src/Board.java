import java.awt.*;
import java.awt.event.*;

public class Board extends Rectangle {
    int id;//for paddles
    int ySpeed;//how fast paddle is moving up and down when we press the button
    int speed = 10;
    Board(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super (x,y,PADDLE_WIDTH, PADDLE_HEIGHT);//super is a constructor of Rectangle superclass
        this.id=id;
    }
    public void keyPressed (KeyEvent e) {
        switch (id) {
            case 1: //for the Paddle 1
                if (e.getKeyCode() == KeyEvent.VK_W) {//w button pressed, paddle goes up
                    setYDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed);
                    move();
                }
                break;

            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                    move();
                }
                break;
        }
    }
    public void keyReleased (KeyEvent e) {
            switch (id) {
                case 1:
                    if(e.getKeyCode()==KeyEvent.VK_W) {
                        setYDirection(0);
                        move();
                    }
                    if(e.getKeyCode()==KeyEvent.VK_S) {
                        setYDirection(0);
                        move();
                    }
                    break;

                case 2:
                    if(e.getKeyCode()==KeyEvent.VK_UP) {
                        setYDirection(0);
                        move();
                    }
                    if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                        setYDirection(0);
                        move();
                    }
                    break;
            }
        }

    public void setYDirection (int yDirection) {//my paddles move vertically, only up and down
        ySpeed = yDirection;
    }
    public void move (){
        y = y + ySpeed;

    }
    public void draw (Graphics g) {
        if (id == 1)
            g.setColor(Color.magenta);
        else
            g.setColor(Color.blue);
        g.fillRect(x, y, width, height);

    }

}
