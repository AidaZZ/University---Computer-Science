import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Food {
    private final Board board;
    private int score;
    private BufferedImage foodImage1;
    private BufferedImage foodImage2;
    private List<FoodItem> foodItems; // This is the list we will use

    // Adjust these constants to scale the food size
    private static final int FOOD_WIDTH = Board.GRID_WIDTH / 2;
    private static final int FOOD_HEIGHT = Board.GRID_HEIGHT / 2;

    public Food(Board board) {
        this.board = board;
        score = 0;
        loadImages();
        initializeFoodPositions();
    }

    private static class FoodItem {
        Point position;
        int imageType;

        FoodItem(Point position, int imageType) {
            this.position = position;
            this.imageType = imageType;
        }
    }

    private void loadImages() {
        try {
            foodImage1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/game/images/food/food1.png")));
            foodImage2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/game/images/food/food2.png")));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the error
        }
    }

    private void initializeFoodPositions() {
        foodItems = new ArrayList<>();
        int[][] grid = board.getGrid();
        for (int row = 0; row < Board.TILE_NUMBER; row++) {
            for (int col = 0; col < Board.TILE_NUMBER; col++) {
                if (grid[row][col] == 0) {
                    int xCenter = (col * Board.GRID_WIDTH) + (Board.GRID_WIDTH / 2);
                    int yCenter = (row * Board.GRID_HEIGHT) + (Board.GRID_HEIGHT / 2);
                    int imageType = (row + col) % 2;
                    foodItems.add(new FoodItem(new Point(xCenter, yCenter), imageType));
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        for (FoodItem foodItem : foodItems) {
            int drawX = foodItem.position.x - FOOD_WIDTH / 2;
            int drawY = foodItem.position.y - FOOD_HEIGHT / 2;
            BufferedImage foodImage = foodItem.imageType == 0 ? foodImage1 : foodImage2;
            g2.drawImage(foodImage, drawX, drawY, FOOD_WIDTH, FOOD_HEIGHT, null);
        }
    }

    public void checkCollisionWithPacman(Pacman pacman) {
        Rectangle2D pacmanBounds = new Rectangle2D.Double(pacman.getX(), pacman.getY(), Pacman.PLAYER_SIZE, Pacman.PLAYER_SIZE);
        foodItems.removeIf(foodItem -> {
            Rectangle2D foodBounds = new Rectangle2D.Double(foodItem.position.x - (double) FOOD_WIDTH / 2, foodItem.position.y - (double) FOOD_HEIGHT / 2, FOOD_WIDTH, FOOD_HEIGHT);
            if (pacmanBounds.intersects(foodBounds)) {
                score += 1;
                return true;
            }
            return false;
        });
    }
    public void drawScore(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.drawString("Score: " + score, 650, 30);
    }

    // Getters and setters
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
