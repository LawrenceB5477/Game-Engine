package com.evilfrogindustries.main.Objects;

import java.awt.*;

//Displays the FPS
public class FPS extends GameObject {
    private int fps = 0;

    public FPS(int x, int y, ID id) {
        super(x, y, id);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.drawString("FPS: " + fps, x, y);
    }

    public Rectangle getBounds() {
        return null;
    }

    public void setfps(int fps) {
        this.fps = fps;
    }
}
