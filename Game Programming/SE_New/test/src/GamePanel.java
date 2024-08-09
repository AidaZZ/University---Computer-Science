import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
public class GamePanel extends JComponent {
    private BufferedImage offScreenBuffer;

    private List<Ghost> ghosts;
    private Graphics2D g2;
    private BufferedImage panel;
    private Board board;
    private Food food; // Add this line for the Food object
    private Clip clip;

    private int width;
    private int height;
    private boolean start = true;
    private Pacman pacman;
    private UserInput userInput;
    private final int FPS = 30;
    private final int TARGET_TIME = 1000000000 / FPS;
    boolean introPlayed = false;

    public void start() {
        width = 800;
        height = 800;
        panel = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        g2 = panel.createGraphics();

        // Update game state
        // Add this line here
        // Render the game
        // Manage frame timing
        Thread thread = new Thread(() -> {
            long startTime = 0;
            while (start) {
                startTime = System.nanoTime();
                /*
                if (clip == null || !introPlayed) {
                    if (!introPlayed) { //SE_New/test/
                        playAudio("src/game/music/RuinsArea(Maze-Intro).wav");
                        introPlayed = true;
                    }
                }
                if (!clip.isRunning()) {
                    playAudio("test/src/game/music/AnubisRex(Re-Pac).wav");

                }

                */
                // Update game state
                pacman.move(board);
                for (Ghost ghost : ghosts) {
                    ghost.move(pacman);
                }
                food.checkCollisionWithPacman(pacman); // Add this line here

                // Render the game
                drawBackground();
                drawGame();
                render();

                // Manage frame timing
                long time = System.nanoTime() - startTime;
                if (time < TARGET_TIME) {
                    long sleep = (TARGET_TIME - time) / 1000000;
                    sleep(sleep);
                }
            }
        });
        initObjGame();
        initUserInput();
        thread.start();
    }

    private void playAudio(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            if (clip!=null)
                clip.stop();
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void initObjGame() {
        pacman = Pacman.getInstance();
        board = new Board(this);// Pass 'this' reference
        ghosts = new ArrayList<>();
        food = new Food(board); // Initialize the food object


        int ghostCount = 3; // Number of ghosts we want to create
        int attempts = 0; // To prevent an infinite loop

        while (ghosts.size() < ghostCount && attempts < 100) {
            int x = (int) (Math.random() * Board.TILE_NUMBER) * Board.GRID_WIDTH;
            int y = (int) (Math.random() * Board.TILE_NUMBER) * Board.GRID_HEIGHT;

            if (board.isPath(x, y) && (x != pacman.getX() || y != pacman.getY())) {
                ghosts.add(new Ghost(board, x, y));
            }

            attempts++;
        }
    }
    public void initUserInput() {
        userInput = new UserInput();
        requestFocus();
        addKeyListener(new KeyAdapter() {
            private long lastPressTime = 0;
            private static final long DOUBLE_PRESS_INTERVAL = 400;
            boolean isSpaceKeyPressed = false;

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    userInput.setKey_a(true);
                } else if (e.getKeyCode() == KeyEvent.VK_W) {
                    userInput.setKey_w(true);
                } else if (e.getKeyCode() == KeyEvent.VK_D) {
                    userInput.setKey_d(true);
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    userInput.setKey_s(true);
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    userInput.setKey_space(true);
                    long currentTime = System.currentTimeMillis();
                    if (!isSpaceKeyPressed && currentTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {
                        new Thread(() -> pacman.speedUp()).start();
                    }
                    lastPressTime = currentTime;
                    isSpaceKeyPressed = true;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    userInput.setKey_a(false);
                } else if (e.getKeyCode() == KeyEvent.VK_W) {
                    userInput.setKey_w(false);
                } else if (e.getKeyCode() == KeyEvent.VK_D) {
                    userInput.setKey_d(false);
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    userInput.setKey_s(false);
                } else if ( e.getKeyCode() == KeyEvent.VK_SPACE){
                    isSpaceKeyPressed = false;
                    userInput.setKey_space(false);
                }
            }
        });
        new Thread(() -> {
            while (start) {
                float angle = pacman.getAngle();
                if (UserInput.isKey_a()) {
                    if (board.isLeftFree(pacman.getX(), pacman.getY())) {
                        angle = 180;
                    } else {
                        pacman.changeAngle(angle);
                    }
                } else if (UserInput.isKey_d()) {
                    if (board.isRightFree(pacman.getX(), pacman.getY())) {
                        angle = 0;
                        pacman.changeAngle(angle);
                    } else {
                        pacman.changeAngle(angle);
                    }
                } else if (UserInput.isKey_w()) {
                    if (board.isUpFree(pacman.getX(), pacman.getY())) {
                        angle = 270;
                        pacman.changeAngle(angle);
                    } else {
                        pacman.changeAngle(angle);
                    }
                } else if (UserInput.isKey_s()) {
                    if (board.isDownFree(pacman.getX(), pacman.getY())) {
                        angle = 90;
                        pacman.changeAngle(angle);
                    } else {
                        pacman.changeAngle(angle);
                    }
                }
                pacman.changeAngle(angle);
                sleep(1);
            }
        }).start();
    }
    public void drawBackground() {
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0, 0, width, height);
    }
    public void drawGame() {
        try {
            board.draw(g2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pacman.draw(g2);

        for (Ghost ghost : ghosts) {
            ghost.draw(g2);  // Draw each ghost
        }

        food.draw(g2); // Add this line to draw food
        food.drawScore(g2); // Add this line to draw score
    }
    public void render() {
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Initialize off-screen buffer if not already initialized
        if (offScreenBuffer == null) {
            offScreenBuffer = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        }

        // Use the buffer to draw
        Graphics bufferGraphics = offScreenBuffer.getGraphics();

        try {
            // Render everything to the off-screen buffer
            if (panel != null) {
                bufferGraphics.drawImage(panel, 0, 0, this);
            }

            // Draw the off-screen buffer to the screen
            g.drawImage(offScreenBuffer, 0, 0, this);
        } finally {
            // Ensure the Graphics object is always disposed
            bufferGraphics.dispose();
        }
    }
    private void sleep(long speed) {
        try {
            Thread.sleep(speed);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    public void showGameOver() {
        // Stop the game loop
        start = false;
        playAudio("src/game/music/pacman_death.wav");

        // Create and display the game over window
        JDialog gameOverDialog = new JDialog();
        gameOverDialog.setTitle("Game Over");
        gameOverDialog.setSize(300, 200);
        gameOverDialog.setLayout(new BorderLayout());
        gameOverDialog.setLocationRelativeTo(null);
        gameOverDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Ensure the dialog closes on exit

        JLabel scoreLabel = new JLabel("Your Score: " + food.getScore());
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverDialog.add(scoreLabel, BorderLayout.CENTER);

        JButton homeButton = new JButton("Back to Home");
        homeButton.addActionListener(e -> {
            gameOverDialog.dispose();
            // Get the top-level window ancestor of this GamePanel to close it
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose(); // Close the main game window
            }
            // Optionally, you can show the home screen here if needed

            new HomeScreen().setVisible(true);

        });
        gameOverDialog.add(homeButton, BorderLayout.SOUTH);

        gameOverDialog.setVisible(true);
    }



}
