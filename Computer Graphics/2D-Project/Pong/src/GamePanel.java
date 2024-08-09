import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.*;
import javax.swing.*;

//GamePanel is like a canvas we are painting, and GameFrame is like a frame around painting
public class GamePanel extends JPanel implements Runnable{
    private AudioClip collisionSound;
    static final int ONE_BILLION=1000000000;
    static final int GAME_WIDTH = 1000; //we use static because if we have multiple instances of the
    //GamePanel class, they all share one variable GAME_WIDTH, they won't have their individual width
    //final - prohibits from accidentally modifying GAME_WIDTH
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555)) ;//a standard ping-pong table ratio 5ft to 9ft
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;//since we are implementing a runnable interface
    Image image;
    Graphics graphics;
    Random random;
    Board board1;
    Board board2;
    Ball ball;
    Score score;
    CountDown countDown;
    boolean isGameRunning; //timer
    String player1Name;
    String player2Name;
    private Image backgroundImage;

    GamePanel(String player1Name,String player2Name) {

        countDown = new CountDown(1); // Set the initial time to 1 minute
        isGameRunning = true;

        newPaddles();
        newBall();

        score = new Score(GAME_WIDTH, GAME_HEIGHT);

        this.setFocusable(true);// When a component is focusable, it means that it can receive keyboard focus, and thus,
        // it can respond to keyboard events such as key presses and releases.

        this.addKeyListener(new KeyEventListener());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();


        this.player1Name=player1Name;
        this.player2Name=player2Name;

        //player1Name = JOptionPane.showInputDialog(this, "Enter Player 1's Name:");
        //player2Name = JOptionPane.showInputDialog(this, "Enter Player 2's Name:");

        String imagePath = "C:\\Users\\User\\IdeaProjects\\Pong\\Staging_Environment\\1.jpg";
        backgroundImage = new ImageIcon(imagePath).getImage();


        try {
            File soundFile = new File("C:\\Users\\User\\Desktop\\Staging_Environment\\1.wav");
            collisionSound = Applet.newAudioClip(soundFile.toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
    public void newBall(){//we call this method to create a ball
        random = new Random();
        ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMETER/2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);//new ball - starts along the y-axes randomly
                                                                                                                                                //random.nextInt - determines the initial y-coordinate of the ball.
                                                                                                                                                 // It uses the nextInt method from the Random class to generate a random integer within the specified range, ensuring that the ball is positioned vertically within the playable area
    }

    public void newPaddles () {//we call this method and newBall when reset a level or a game
        board1 = new Board(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT,1);
        board2 = new Board(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT,2);
    }
    public void paint(Graphics g) {
        graphics = g;
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);

        // Call the draw method to draw other components
        draw(g);
    }
    public void draw(Graphics g) {

        board1.draw(g); //paints paddles
        board2.draw(g);
        ball.draw(g);
        score.draw(g);
        countDown.draw(g);

        // Draw player names at the bottom
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.BOLD, 30));
        int playerNameY = GAME_HEIGHT - 10;
        g.drawString(player1Name, 50, playerNameY);
        g.drawString(player2Name, GAME_WIDTH - 100, playerNameY);
    }
    public void move () {//move objects after the iteration of the game loop
        board1.move(); //when we apply this method the pedal starts to move more smooth
        board2.move();
        ball.move();//the ball starts to move more smooth
    }
    public void checkCollision () {
        // bounce ball off top & bottom window edges
        if (ball.y <= 0) { // checks if the top edge of the ball (ball.y) has reached or gone above the top edge of the game window
            ball.setYDirection(- ball.ySpeed);// ball goes on opposite direction. If the condition is true, it means the ball has collided with the top edge.
                                            // The setYDirection method is then called to reverse the direction of the ball along the y-axis, making it "bounce" back down
        }
        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {//checks if the bottom edge of the ball has reached or gone below the bottom edge of the game window (GAME_HEIGHT - BALL_DIAMETER).
                                                    // It considers the diameter of the ball to ensure the entire ball is within the game window
            ball.setYDirection(- ball.ySpeed);
        }

        //bounce ball off paddles

        if (ball.intersects(board1)) { //belong to the Rectangles superclass from the Ball class
            ball.xSpeed = Math.abs(ball.xSpeed);//instead of the absolute value multiplying by -1 works. This line ensures that the ball moves in the opposite direction along the x-axis.
                                                // Taking the absolute value guarantees that the ball will move to the right, regardless of its previous direction
            ball.xSpeed++; //optional for more difficulty, the ball speed increases
            if (ball.ySpeed > 0) // If ball.ySpeed > 0 (indicating that the ball is moving downward), the y-speed is optionally increased.
                ball.ySpeed++; //optional for more difficulty
            else
                ball.ySpeed--; //If ball.ySpeed is negative (indicating that the ball is moving upward), the y-speed is optionally decrea
            ball.setXDirection(ball.xSpeed);
            ball.setYDirection(ball.ySpeed);
        }

        if (ball.intersects(board2)) {
            ball.xSpeed = Math.abs(ball.xSpeed);
            ball.xSpeed++; //optional for more difficulty
            if (ball.ySpeed > 0)
                ball.ySpeed++; //optional for more difficulty
            else
                ball.ySpeed--; //if y velocity is negative  it means it is going upwards
            ball.setXDirection(-ball.xSpeed);
            ball.setYDirection(ball.ySpeed);
        }


        //stops paddles at window edges
        if (board1.y<=0)//reaches or goes above the top boundary of the game area
            board1.y=0;
        if (board1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) // reaches or goes below the bottom boundary of the game area
            board1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        if (board2.y<=0)
            board2.y=0;
        if (board2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            board2.y = GAME_HEIGHT - PADDLE_HEIGHT;

        //give a player 1 point and creates new paddles & ball
        if (ball.x <= 0) { //means player 2 scored the point (ball touched a left boundary)
            score.player2 ++;
            newPaddles();
            newBall();
            System.out.println("Player 2: " + score.player2);
        }
        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            score.player1 ++;
            newPaddles();
            newBall();
            System.out.println("Player 1: " + score.player1);
        }

        if (ball.intersects(board1)) {
            // ... (rest of your existing code)
            collisionSound.play();  // Play the collision sound
        }

        if (ball.intersects(board2)) {
            // ... (rest of your existing code)
            collisionSound.play();  // Play the collision sound
        }

    }


    private void showGameOverDialog(){
        String winner = (score.player1 > score.player2) ? player1Name : player2Name;
        JOptionPane.showMessageDialog(this, "Game Over! Winner: " + winner, "Game Over", JOptionPane.INFORMATION_MESSAGE);

    }
    public void run (){
        //game loop
        long lastTime = System.nanoTime();//System.nanoTime(): This method returns the current value of the most precise available system timer, in nanoseconds.
        // It's often used for high-precision timing.
        double framesPerSec = 60.0;
        double ns = ONE_BILLION/framesPerSec ;//result is 16.6666667 ms, every 16.6 ms frame changes to achieve 60.0 frames for second
        double delta = 0;//delta is typically used in game loops to account for the time passed between frames. In a game loop, you want to update the game state
        // and render frames at a consistent rate, regardless of the actual frame rate achieved by the system. The delta helps you achieve this by measuring the time elapsed since the last frame.

        while (true) {//instead of true i can use running
            long now = System.nanoTime();//This line records the current time in nanoseconds and stores it in the variable now. System.nanoTime() provides a high-resolution time value,
            // which is useful for measuring small time intervals.


            delta += (now - lastTime)/ns;//This line calculates the time that has passed since the last iteration of the loop and adds it to the delta variable. delta is typically used
            // to keep track of the time elapsed since the last update in the game loop.
            lastTime = now;

            if (delta>=1 && isGameRunning) {//game loop
                move();
                checkCollision();
                countDown.update();
                repaint();//triggers the repainting of the game panel. It tells the system that the appearance of the panel has changed and needs to be redrawn.
                // This ensures that any updates in the game logic are reflected in the graphical representation.
                delta--; //decrements the delta variable by 1, indicating that one iteration of the game loop has been processed.

                if(countDown.secondsLeft <= 0){
                    isGameRunning = false; //stop the game when the timer finishes
                    showGameOverDialog();
                }

            }

        }


    }
    public class KeyEventListener extends KeyAdapter {
        public void keyPressed (KeyEvent e){
            board1.keyPressed(e);//moves the paddle up and down
            board2.keyPressed(e);
        }
        public void keyReleased (KeyEvent e){
            board1.keyReleased(e);
            board2.keyReleased(e);

        }
    }


}
