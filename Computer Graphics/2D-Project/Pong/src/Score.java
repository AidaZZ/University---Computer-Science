import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score extends Rectangle {
    static int GAME_WIDTH;//we will pass a copy using a constructor
    static int GAME_HEIGHT;
    int player1;//holds the current score of the player1
    int player2;
    Score (int GAME_WIDTH, int GAME_HEIGHT){
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }
    public void draw (Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.PLAIN,60));
        g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);//draws a vertical line in the middle of the game area
        g.drawString(String.valueOf(player1/10) + (String.valueOf(player1%10)), (GAME_WIDTH/2) - 85, 50);//draws the score for player 1. It converts the player 1 score into a string,
                                                                                                                        // splits the tens and units digits, and then draws them at a specific position
        g.drawString(String.valueOf(player2/10) + (String.valueOf(player2%10)), (GAME_WIDTH/2) + 20, 50);

    }
}
