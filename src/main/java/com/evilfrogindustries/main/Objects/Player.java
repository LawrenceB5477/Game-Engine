package com.evilfrogindustries.main.Objects;

import com.evilfrogindustries.main.Game;
import com.evilfrogindustries.main.HUD;
import com.evilfrogindustries.main.ObjectController;

import java.awt.*;

//Represents the main player
public class Player extends GameObject {
    private int speed;
    private ObjectController handler;

    public Player(int x, int y, ID id, ObjectController handler) {
        super(x, y, id);
        speed = 3;
        this.handler = handler;
    }

    public void tick() {
        collision();
        x += velx;
        y += vely;

        x = Game.clamp(x, 0, Game.getRoomWidth() - 36);
        y = Game.clamp(y, 0, Game.getRoomHeight() - 62);

        handler.addObject(new Trail(x, y, ID.TRAIL, handler, 32, 32, 0.1f, Color.white));
    }

    private void collision() {
        for(int i = 0; i < handler.getObjects().size(); i++) {
            if(handler.getObjects().get(i).getID() == ID.BASICENEMY || handler.getObjects().get(i).getID() == ID.BULLET) {
                if (handler.getObjects().get(i).getBounds().intersects(this.getBounds())) {
                    HUD.setHp(HUD.getHp() - 1);
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);
    }

    public int getSpeed() {
        return speed;
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), 32, 32);
    }
}
