import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class Settings extends JFrame {
    private final MapSelectionListener listener;
    protected static int newGhostSpeed;
    protected static int newPacmanSpeed;
    protected static boolean PacmanSpeedChanged = false;
    protected static boolean GhostSpeedChanged = false;
    protected static boolean default_map;
    protected static boolean map1;
    protected static boolean map2;
    protected static boolean map3;

    public Settings(MapSelectionListener listener) {
        this.listener = listener;
        this.initUI();
    }

    private void initUI() {
        setTitle("Select Map");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JButton map1Button = new JButton("Map 1");
        map1Button.addActionListener((e) -> this.listener.onMapSelected(1));

        JButton map2Button = new JButton("Map 2");
        map2Button.addActionListener((e) -> this.listener.onMapSelected(2));

        JButton map3Button = new JButton("Map 3");
        map3Button.addActionListener((e) -> this.listener.onMapSelected(3));

        this.add(map1Button);
        this.add(map2Button);
        this.add(map3Button);
    }

    interface MapSelectionListener {
        void onMapSelected(int var1);
    }

    public void openSettings() {
        // Create a new JFrame for settings
        JFrame settingsFrame = new JFrame("Settings");
        settingsFrame.setSize(400, 300);
        settingsFrame.setLocationRelativeTo(null);
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a panel for settings components
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridLayout(3, 2, 10, 10));

        // Add components for a Map option
        JLabel mapLabel = new JLabel("Map:");

        //This stuff adjusts the border between the screen edge and text (Gaping)
        mapLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        JComboBox<String> mapOptions = new JComboBox<>(new String[]{"Map 1", "Map 2", "Map 3"});
        settingsPanel.add(mapLabel);
        settingsPanel.add(mapOptions);

        // Add components for a Pacman Speed option
        JLabel pacmanSpeedLabel = new JLabel("Pacman Speed:");

        //This stuff adjusts the border between the screen edge and text (Gaping)
        pacmanSpeedLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        JSlider pacmanSpeedSlider = new JSlider(1, 10, 5);
        configureSlider(pacmanSpeedSlider);
        settingsPanel.add(pacmanSpeedLabel);
        settingsPanel.add(pacmanSpeedSlider);

        // Add components for a Ghost Speed option
        JLabel ghostSpeedLabel = new JLabel("Ghost Speed:");

        //This stuff adjusts the border between the screen edge and text (Gaping)
        ghostSpeedLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        JSlider ghostSpeedSlider = new JSlider(1, 10, 5);
        configureSlider(ghostSpeedSlider);
        settingsPanel.add(ghostSpeedLabel);
        settingsPanel.add(ghostSpeedSlider);

        settingsFrame.add(settingsPanel);
        settingsFrame.setVisible(true);

        // Update Pacman speed
        pacmanSpeedSlider.addChangeListener(e -> {
            PacmanSpeedChanged = true;
            newPacmanSpeed = ((JSlider) e.getSource()).getValue();
        });

        // Update Ghost speed
        ghostSpeedSlider.addChangeListener(e -> {
            GhostSpeedChanged = true;
            newGhostSpeed = ((JSlider) e.getSource()).getValue();
        });

        // Update the map
        mapOptions.addActionListener(e -> {
            String selectedMap = (String) mapOptions.getSelectedItem();
            System.out.println("Selected Map: " + selectedMap);

            if (mapOptions.getSelectedIndex() == 0) {
                map1 = true;
                map2 = false;
                map3 = false;
            } else if (mapOptions.getSelectedIndex() == 1) {
                map1 = false;
                map2 = true;
                map3 = false;
            } else if (mapOptions.getSelectedIndex() == 2) {
                map1 = false;
                map2 = false;
                map3 = true;
            } else {
                map1 = false;
                map2 = false;
                map3 = false;
            }
        });

    }

    private void configureSlider(JSlider slider) {
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        for (int i = slider.getMinimum(); i <= slider.getMaximum(); i++) {
            labelTable.put(i, new JLabel(Integer.toString(i)));
        }
        slider.setLabelTable(labelTable);
    }

    public static boolean isMap1() {
        return map1;
    }

    public static boolean isMap2() {
        return map2;
    }

    public static boolean isMap3() {
        return map3;
    }
    public static int getNewGhostSpeed(){
        return newGhostSpeed;
    }
    public static int getNewPacmanSpeed(){
        return newPacmanSpeed;
    }
    public static boolean isPacmanSpeedChanged(){
        return PacmanSpeedChanged;
    }
    public static boolean isGhostSpeedChanged(){
        return GhostSpeedChanged;
    }
}

