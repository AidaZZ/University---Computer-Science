import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerNameInputFrame extends JFrame {
    private JTextField player1TextField;
    private JTextField player2TextField;

    public PlayerNameInputFrame() {
        // Set up the frame
        setTitle("Enter Player Names");
        setSize(700, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Create text fields and Start button
        player1TextField = new JTextField(15);
        player2TextField = new JTextField(15);
        JButton startButton = new JButton("Start");


        Font labelFont=new Font("Consolas", Font.PLAIN,20);
        Font textFont=new Font("Consolas", Font.BOLD,20);

        JLabel player1Label = new JLabel("Player 1 Name:");
        player1Label.setFont(labelFont);

        JLabel player2Label = new JLabel("Player 2 Name:");
        player2Label.setFont(labelFont);

        player1TextField.setFont(textFont);
        player2TextField.setFont(textFont);

        startButton.setFont(textFont);

        // Create layout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        //Player1 Panel with text field
        JPanel player1Panel = new JPanel();
        player1Panel.setLayout(new FlowLayout());
        player1Panel.add(player1Label);
        player1Panel.add(player1TextField);

        //Player2 Panel with text field
        JPanel player2Panel = new JPanel();
        player2Panel.setLayout(new FlowLayout());
        player2Panel.add(player2Label);
        player2Panel.add(player2TextField);

        // Add components to the frame
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.add(player1Panel);
        inputPanel.add(player2Panel);

        add(inputPanel);
        add(startButton);

        // Add action listener to the start button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get player names from text fields
                String player1Name = player1TextField.getText();
                String player2Name = player2TextField.getText();

                // Start the game with the provided names
                startGame(player1Name, player2Name);

                // Close the input frame
                dispose();
            }
        });
        setVisible(true);
    }

    private void startGame(String player1Name, String player2Name) {
        // Create an instance of GamePanel with the provided names
        GamePanel gamePanel;
        gamePanel = new GamePanel(player1Name, player2Name);

        // Set up the main frame for the game
        JFrame mainFrame = new JFrame("Pong Game");
        mainFrame.add(gamePanel);
        //mainFrame.setSize(GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(gamePanel);
        mainFrame.setResizable(false);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }
}
