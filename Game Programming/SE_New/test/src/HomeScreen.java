import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class HomeScreen extends JFrame {
    private Settings settings;
    private AudioInputStream audioInputStream;
    private Clip clip;

    public HomeScreen() {
        initUI();
        settings = new Settings(new Settings.MapSelectionListener() {
            @Override
            public void onMapSelected(int mapNumber) {
                // Handle map selection logic here
                System.out.println("Selected Map in HomeScreen: " + mapNumber);
            }
        });
    }
    public void closeWindow() {
        this.dispose();
    }

    private void initUI() {
        setTitle("PACMAN REMASTERED");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        setResizable(false);
        setLocationRelativeTo(null);

        try {
            startIntroMusic();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textLabel = new JLabel("PAC-MAN!");
        textLabel.setFont(new Font("Arial", Font.BOLD, 40));
        textLabel.setForeground(Color.YELLOW);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 20, 0);
        panel.add(textLabel, gbc);

        //SE_New/test/src/game/images/Main_Window_Wallpaper.jpg
        ImageIcon icon = new ImageIcon("src/game/images/Main_Window_Wallpaper.jpg");
        JLabel imageLabel = new JLabel(icon);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 50, 0);
        panel.add(imageLabel, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(Box.createHorizontalStrut(200), gbc);

        JButton playBtn = new JButton("Play");
        playBtn.setPreferredSize(new Dimension(100, 40));
        playBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeScreen.this.closeWindow();
                LoadingScreen loadingScreen = new LoadingScreen(HomeScreen.this);
                loadingScreen.startLoading();

                try {
                    stopMusic();
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        gbc.gridx = 1;
        panel.add(playBtn, gbc);

        JButton settingsBtn = new JButton("Settings");
        settingsBtn.setPreferredSize(new Dimension(100, 40));
        settingsBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        settingsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSettings();
            }
        });
        gbc.gridx = 2;
        panel.add(settingsBtn, gbc);

        JButton quitBtn = new JButton("Quit");
        quitBtn.setPreferredSize(new Dimension(100, 40));
        quitBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        gbc.gridx = 3;
        panel.add(quitBtn, gbc);

        gbc.gridx = 4;
        panel.add(Box.createHorizontalStrut(200), gbc);

        add(panel);
    }
                                            //SE_New/test/
    public void startIntroMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File audioFile = new File("src/game/music/Pac-ManWorld.wav");
        audioInputStream = AudioSystem.getAudioInputStream(audioFile);

        clip = AudioSystem.getClip();
        //clip.stop();
        // Open the audio stream and start playing
        clip.open(audioInputStream);
        clip.start();
    }

    public void stopMusic() throws LineUnavailableException {
        clip.stop();
        clip = AudioSystem.getClip();
    }

    private void openSettings() {
        settings.openSettings();
    }
}

