package game_programming_project;

import java.awt.Color;

public class Map3 extends Board {

    public Map3() {
        super(); // Call the constructor of the parent class (Board)
        // Customize the mazeColor for the new map
        this.mazeColor = new Color(100, 100, 255); // Change the maze color
    }

    // You can override any specific methods here to customize the behavior of Map2
    // For example:
    // @Override
    // private void initLevel() {
    //    // Implement a different level initialization if needed
    // }
    //
    // @Override
    // private void loadImages() {
    //    // Load different images for Map2
    // }

    // You can create your own methods or modify existing ones to customize the behavior of Map2
}
