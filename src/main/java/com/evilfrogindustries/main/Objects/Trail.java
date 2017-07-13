package com.evilfrogindustries.main.Objects;

import com.evilfrogindustries.main.ObjectController;

import java.awt.*;

public class Trail extends GameObject {
    private float alpha = 1.0f;
    private ObjectController handler;
    private Color color;
    private int width, height;
    private float life;

    //Smaller the life number, longer the life
    public Trail(int x, int y, ID id, ObjectController handler, int width, int height, float life,  Color color) {
        super(x, y, id);
        this.handler = handler;
        this.width = width;
        this.height = height;
        this.life = life;
        this.color = color;
    }

    public void tick() {
        if (alpha > life) {
            alpha -= (life - 0.001f);
        } else handler.removeObject(this);
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setComposite(makeTransparent(alpha));
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g2d.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha) {
        int compositeType = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(compositeType, alpha);
    }

    public Rectangle getBounds() {
        return null;
    }
}
