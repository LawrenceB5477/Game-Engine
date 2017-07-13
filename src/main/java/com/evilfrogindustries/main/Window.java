package com.evilfrogindustries.main;

import javax.swing.*;
import java.awt.*;

//TODO figure out why this extends canvas, and see if you can call game.start() in Game
//Creates the game window
public class Window extends Canvas {
    private static final long serialVersionUID = 8727205333393253394L;

    public Window(int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title);

        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setPreferredSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        //Adds the main canvas of the game
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}
