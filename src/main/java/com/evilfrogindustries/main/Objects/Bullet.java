package com.evilfrogindustries.main.Objects;

import com.evilfrogindustries.main.Game;
import com.evilfrogindustries.main.ObjectController;

import java.awt.*;

//Creates a bullet that follows the player
public class Bullet extends GameObject {
    private Player player;
    double cos;
    double sin;
    private ObjectController handler;

    public Bullet(int x, int y, ID id, Player player, ObjectController handler) {
        super(x, y, id);
        this.handler = handler;
        int playerX = player.x + 16;
        int playerY = player.y + 16;

        double xDis = (double)playerX - (double)x;
        double yDis = (double)playerY - (double)y;
        double hyp = Math.sqrt((xDis * xDis) + (yDis * yDis));

        cos = xDis / hyp;
        sin = yDis / hyp;
    }

    public void tick() {
        x += cos * 5.0;
        y += sin * 5.0;

        if (x >= Game.getRoomWidth() || x <= 0 || y >= Game.getRoomHeight() || y <= 0) {
            handler.removeObject(this);
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, 16, 16);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
}
