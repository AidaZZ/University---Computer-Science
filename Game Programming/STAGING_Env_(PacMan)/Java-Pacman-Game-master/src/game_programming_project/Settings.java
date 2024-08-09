package game_programming_project;

import javax.swing.*;
import java.awt.*;

public class Settings extends JFrame {

    interface MapSelectionListener {
        void onMapSelected(int mapNumber);
    }

    private MapSelectionListener listener;

    public Settings(Pacman pacman) {
        this.listener = pacman; // Change 'listener' to 'pacman'
        initUI();
    }


    private void initUI() {
        setTitle("Select Map");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JButton map1Button = new JButton("Map 1");
        map1Button.addActionListener(e -> listener.onMapSelected(1));

        JButton map2Button = new JButton("Map 2");
        map2Button.addActionListener(e -> listener.onMapSelected(2));

        JButton map3Button = new JButton("Map 3");
        map3Button.addActionListener(e -> listener.onMapSelected(3));

        add(map1Button);
        add(map2Button);
        add(map3Button);
    }
}
