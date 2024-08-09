import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Ball extends Rectangle {
    Random random;
    int xSpeed;// how fast a ball is going to move on x axes
    int ySpeed;// how fast a ball is going to move on y axes
    int initialSpeed = 2;//speed of the ball
    Ball (int x, int y, int width, int height){//width and height both are a ball diameter
        super (x,y, width, height);
        random = new Random();
        int randomXDirection = random.nextInt(2);//generates a random integer (0 or 1) for the initial x-direction.
        if (randomXDirection == 0) //If the random value is 0, it sets randomXDirection to -1, indicating that the ball should move to the left. If it's 1, the direction remains 1, indicating movement to the right.
            randomXDirection--; //-1 ball goes to the right
        setXDirection(randomXDirection * initialSpeed);//sets the initial speed and direction along the x-axis for the ball

        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection * initialSpeed);

    }
    public void setXDirection (int randomXDirection){//because when I create a new ball it will be going on random direction
        xSpeed = randomXDirection;
    }
    public void setYDirection (int randomYDirection){
        ySpeed = randomYDirection;
    }
    public void move (){
        x += xSpeed;//increments the x-coordinate (x) by the current value of xSpeed
        y += ySpeed;//If ySpeed is positive, the ball moves downward; if negative, it moves upward
    }
    public void draw (Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x,y, height, width);

    }
}
