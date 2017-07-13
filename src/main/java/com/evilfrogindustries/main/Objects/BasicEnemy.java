package com.evilfrogindustries.main.Objects;

import com.evilfrogindustries.main.Game;
import com.evilfrogindustries.main.ObjectController;

import java.awt.*;

public class BasicEnemy extends GameObject {
    private ObjectController handler;
    public BasicEnemy(int x, int y, ID id, ObjectController handler) {
        super(x, y, id);
        this.handler = handler;
        setVelx(5);
        setVely(5);
    }

    public void tick() {
        x += velx;
        y += vely;

        if (x <= 0 || x >= Game.getRoomWidth() - 30) {
            velx *= -1;
        }

        if (y <= 0 || y >= Game.getRoomHeight() - 30) {
            vely *= -1;
        }
        handler.addObject(new Trail(x, y, ID.TRAIL, handler, 16, 16, 0.01f, Color.green));
    }

    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, 16, 16);
    }

    public Rectangle getBounds() {
        return new Rectangle(this.getX(), this.getY(), 16, 16);
    }


}
