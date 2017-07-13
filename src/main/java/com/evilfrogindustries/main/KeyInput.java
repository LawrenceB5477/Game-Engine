package com.evilfrogindustries.main;

import com.evilfrogindustries.main.Objects.GameObject;
import com.evilfrogindustries.main.Objects.ID;
import com.evilfrogindustries.main.Objects.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Listener class for player input
public class KeyInput extends KeyAdapter {
    private ObjectController handler;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(ObjectController handler) {
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        //Player Movement
        for (GameObject object : handler.getObjects()) {
            if (object.getID() == ID.PLAYER) {
                int speed = ((Player) object).getSpeed();

                if (key == KeyEvent.VK_UP) {
                    object.setVely(-speed);
                    keyDown[0] = true;
                }
                if (key == KeyEvent.VK_DOWN) {
                    object.setVely(speed);
                    keyDown[1] = true;
                }
                if (key == KeyEvent.VK_RIGHT) {
                    object.setVelx(speed);
                    keyDown[2] = true;
                }
                if (key == KeyEvent.VK_LEFT) {
                    object.setVelx(-speed);
                    keyDown[3] = true;
                }
            }
        }
        if (key == KeyEvent.VK_ESCAPE) System.exit(0);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        //Stop Player Movement
        for (GameObject object : handler.getObjects()) {
            if (object.getID() == ID.PLAYER) {

                if (key == KeyEvent.VK_UP) keyDown[0] = false; //object.setVely(0);
                if (key == KeyEvent.VK_DOWN) keyDown[1] = false; //object.setVely(0);
                if (key == KeyEvent.VK_RIGHT) keyDown[2] = false; //object.setVelx(0);
                if (key == KeyEvent.VK_LEFT) keyDown[3] = false; //object.setVelx(0);

                //Stop Movement
                if (!keyDown[0] && !keyDown[1]) object.setVely(0);
                if (!keyDown[2] && !keyDown[3]) object.setVelx(0);
            }
        }
    }
}
