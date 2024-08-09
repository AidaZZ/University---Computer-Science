import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class CountDown {
    int secondsLeft;
    long roundStartTime;
    final int COUNTDOWN_DURATION; // Add this constant

    CountDown(int minutes) {
        this.secondsLeft = minutes * 60;
        this.COUNTDOWN_DURATION = minutes * 60; // Initialize the constant
        this.roundStartTime = System.currentTimeMillis();
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - roundStartTime;
        secondsLeft = (int) Math.max(0, (COUNTDOWN_DURATION * 1000 - elapsedTime) / 1000);//Calculates the remaining seconds by subtracting the elapsed time from the total duration.
        // The result is divided by 1000 to convert from milliseconds to seconds. The Math.max(0, ...) ensures that the remaining seconds do not go below 0.

        if (secondsLeft <= 0) {
            secondsLeft = 0; // Set to 0 when the time is up.Sets secondsLeft to 0 if it becomes negative. This step is an additional safety check to ensure that the countdown does not show negative value
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 30));

        int minutes = secondsLeft / 60;
        int seconds = secondsLeft % 60;

        String timeString = String.format("Time: %02d:%02d", minutes, seconds);
        g.drawString(timeString, 20, 30);
    }
}
