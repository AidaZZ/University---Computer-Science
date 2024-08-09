import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

public class Board {
    public static final int TILE_NUMBER = 16;
    public static final int GRID_WIDTH = 800 / TILE_NUMBER;
    public static final int GRID_HEIGHT = 800 / TILE_NUMBER;
    private final GamePanel gamePanel; // Reference to GamePanel

    private final int[][] grid;

    public Board(GamePanel gamePanel) {
        this.gamePanel = gamePanel; // Correct assignment
        // Initialize the grid...
        grid = new int[TILE_NUMBER][TILE_NUMBER];

        try {
            initializeMaze();
        } catch (FileNotFoundException e) {
            System.err.println("Maze file not found.");
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Pacman pacman = Pacman.getInstance();
        pacman.changePosition( TILE_NUMBER / 2 * GRID_WIDTH, (TILE_NUMBER - 2) * GRID_HEIGHT);
    }

    public boolean isPath(double x, double y) {
        int leftCol = (int) x / GRID_WIDTH;
        int rightCol = (int) (x + GRID_WIDTH - 1) / GRID_WIDTH;
        int topRow = (int) y / GRID_HEIGHT;
        int bottomRow = (int) (y + GRID_HEIGHT - 1) / GRID_HEIGHT;

        // Check if any of the corners of the ghost's tile are not a path
        return leftCol >= 0 && rightCol < TILE_NUMBER &&
                topRow >= 0 && bottomRow < TILE_NUMBER &&
                grid[topRow][leftCol] == 0 &&
                grid[topRow][rightCol] == 0 &&
                grid[bottomRow][leftCol] == 0 &&
                grid[bottomRow][rightCol] == 0;
    }

    private void initializeMaze() throws IOException {
        File file = new File("");

        if(Settings.isMap1()){  //SE_New/test/
            file = new File("src/game/maps/maze1.txt");
        }
        if(Settings.isMap2()){
            file = new File("src/game/maps/maze2.txt"); //SE_New/test/src/game/maps/maze2.txt
        }
        if(Settings.isMap3()){
            file = new File("src/game/maps/maze3.txt");
        }else{
            file = new File("src/game/maps/default_maze.txt");
        }

        if (!file.exists()) {
            throw new IOException("File not found: src/game/maps/maze.txt");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < TILE_NUMBER) {
                for (int col = 0; col < TILE_NUMBER && col < line.length(); col++) {
                    grid[row][col] = Character.getNumericValue(line.charAt(col));
                }
                row++;
            }
        }
    }

    public void draw(Graphics2D graphics2D) throws IOException {
        // Define colors for Map 2
        Color darkGreen = new Color(0, 100, 0); // Dark green for walls
        Color lightGreen = new Color(144, 238, 144); // Light green for paths

        // Define colors for Map 3
        Color darkRed = new Color(139, 0, 0); // Dark red for walls
        Color lightRed = new Color(250, 128, 114); // Light coral for paths

        BufferedImage grassBlock = ImageIO.read(Objects.requireNonNull(getClass().getResource("/game/images/grass.png")));
        BufferedImage dirtBlock = ImageIO.read(Objects.requireNonNull(getClass().getResource("/game/images/dirt.png")));

        for (int row = 0; row < TILE_NUMBER; row++) {
            for (int col = 0; col < TILE_NUMBER; col++) {
                int tileX = col * GRID_WIDTH;
                int tileY = row * GRID_HEIGHT;

                if (grid[row][col] == 1) {
                    if (Settings.isMap2()) {
                        // Use dark green color for walls in Map 2
                        graphics2D.setColor(darkGreen);
                    } else if (Settings.isMap3()) {
                        // Use dark red color for walls in Map 3
                        graphics2D.drawImage(grassBlock, tileX, tileY, 50,50, null);
                    } else {
                        // Use default colors for other maps
                        Color darkBlueStart = new Color(10, 30, 70);
                        Color darkBlueEnd = new Color(25, 50, 100);
                        Paint wallPaint = new GradientPaint(tileX, tileY, darkBlueStart, tileX + GRID_WIDTH, tileY + GRID_HEIGHT, darkBlueEnd);
                        graphics2D.setPaint(wallPaint);
                    }
                    if (!Settings.isMap3())
                        graphics2D.fillRect(tileX, tileY, GRID_WIDTH, GRID_HEIGHT);
                } else {
                    if (Settings.isMap2()) {
                        // Use light green color for paths in Map 2
                        graphics2D.setColor(lightGreen);
                    } else if (Settings.isMap3()) {
                        // Use light coral color for paths in Map 3
                        //graphics2D.setColor(lightRed);
                        graphics2D.drawImage(dirtBlock, tileX, tileY, 50, 50, null);
                    } else {
                        // Use default colors for other maps
                        Color lightPathColor = new Color(180, 200, 220); // Light blue-gray
                        graphics2D.setColor(lightPathColor);
                    }
                    if (!Settings.isMap3())
                        graphics2D.fillRect(tileX, tileY, GRID_WIDTH, GRID_HEIGHT);
                }
            }
        }
    }




    // Getters and setters
    public int[][] getGrid() {
        return grid;
    }

    public boolean isRightFree(double x, double y) {
        int rightCol = (int) (x + GRID_WIDTH) / GRID_WIDTH;
        int topRow = (int) y / GRID_HEIGHT;
        int bottomRow = (int) (y + GRID_HEIGHT - 1) / GRID_HEIGHT;
        // Check if the tiles to the right are within bounds and are free paths
        return rightCol < TILE_NUMBER &&
                grid[topRow][rightCol] == 0 &&
                grid[bottomRow][rightCol] == 0;
    }

    public boolean isLeftFree(double x, double y) {
        // Calculate the grid indices for the tiles to the left of the pacman
        int leftCol = (int) (x - GRID_WIDTH) / GRID_WIDTH;
        int topRow = (int) y / GRID_HEIGHT;
        int bottomRow = (int) (y + GRID_HEIGHT - 1) / GRID_HEIGHT;
        // Check if the tiles to the left are within bounds and are free paths
        return leftCol >= 0 &&
                grid[topRow][leftCol] == 0 &&
                grid[bottomRow][leftCol] == 0;
    }

    public boolean isUpFree(double x, double y) {
        // Calculate the grid indices for the tiles above the pacman
        int leftCol = (int) x / GRID_WIDTH;
        int rightCol = (int) (x + GRID_WIDTH - 1) / GRID_WIDTH;
        int topRow = (int) (y - GRID_HEIGHT) / GRID_HEIGHT;
        // Check if the tiles above are within bounds and are free paths
        return topRow >= 0 &&
                grid[topRow][leftCol] == 0 &&
                grid[topRow][rightCol] == 0;
    }
    public boolean isDownFree(double x, double y) {
        // Calculate the grid indices for the tiles below the pacman
        int leftCol = (int) x / GRID_WIDTH;
        int rightCol = (int) (x + GRID_WIDTH - 1) / GRID_WIDTH;
        int bottomRow = (int) (y + GRID_HEIGHT) / GRID_HEIGHT;
        // Check if the tiles below are within bounds and are free paths
        return bottomRow < TILE_NUMBER &&
                grid[bottomRow][leftCol] == 0 &&
                grid[bottomRow][rightCol] == 0;
    }

    public void triggerGameOver() {
        // Call showGameOver on the GamePanel instance
        if (gamePanel != null) {
            gamePanel.showGameOver();
        }
    }


}
