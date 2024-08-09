import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadingScreen extends JDialog {
    private JProgressBar progressBar;
    private Timer timer;
    private int count = 90;
    private JLabel loadingLabel;

    public LoadingScreen(JFrame parent) {
        super(parent, "Loading", true);
        initUI();
    }

    private void initUI() {
        setSize(400, 200);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setValue(0);
        progressBar.setPreferredSize(new Dimension(350, 30));
        progressBar.setForeground(new Color(0, 150, 136));  // Modern teal color
        progressBar.setBackground(Color.WHITE);

        loadingLabel = new JLabel("Loading, please wait...");
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 16));
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(loadingLabel, gbc);

        gbc.gridy = 1;
        centerPanel.add(progressBar, gbc);

        add(centerPanel, BorderLayout.CENTER);

        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                progressBar.setValue(count);
                if (count >= 100) {
                    timer.stop();
                    LoadingScreen.this.dispose();
                    // Transition to the game screen here, for example:
                    new GameFrame().setVisible(true);
                }
            }
        });
    }

    public void startLoading() {
        timer.start();
        setVisible(true);
    }
}



//Test