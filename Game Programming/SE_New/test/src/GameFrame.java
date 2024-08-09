import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class GameFrame extends JFrame {

    private void init() {
        setTitle("Project PacMan");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel panel = new GamePanel();
        add(panel);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                panel.start();
            }
        });
    }
    public GameFrame(){
        init();
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        System.setProperty("java.awt.headless", "false");
        System.setProperty("apple.awt.UIElement", "true"); // For Mac users, whom are "Cool"
        EventQueue.invokeLater(() -> {
            var ex = new HomeScreen();
            ex.setVisible(true);
        });
    }
}
