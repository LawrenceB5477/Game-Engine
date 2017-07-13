package com.evilfrogindustries.main.Objects;

import com.evilfrogindustries.main.ObjectController;

import java.awt.*;

//A strafing turret that fires bullets
public class Turret extends GameObject{
    private Player player;
    private ObjectController handler;
    private int strafeTimer;
    private int bulletTimer;

    public Turret(int x, int y, ID id, Player player, ObjectController handler) {
        super(x, y, id);
        this.player = player;
        this.handler = handler;
        strafeTimer = 0;
        bulletTimer = 0;
        setVelx(4);
    }

    public void tick() {
        x += getVelx();
        strafeTimer ++;
        bulletTimer ++;
        if (strafeTimer >= 50) {
            strafeTimer = 0;
            velx *= -1;
        }

        if (bulletTimer >= 40) {
            bulletTimer = 0;
            handler.addObject(new Bullet(x + 16, y + 16, ID.BULLET, player, handler));
        }

        handler.addObject(new Trail(x, y, ID.TRAIL, handler, 32, 32, 0.08f, Color.cyan));
    }

    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(x, y, 32, 32);
    }

    public Rectangle getBounds() {
        return null;
    }
}
