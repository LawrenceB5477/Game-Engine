package com.evilfrogindustries.main.Objects;

import java.awt.*;

//Abstract class that represents a game object
public abstract class GameObject {
    protected int x, y;
    protected ID id;
    protected int velx, vely;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    //Getters and setters
    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setID(ID id) {
        this.id = id;
    }

    public ID getID() {
        return id;
    }

    public void setVelx(int velx) {
        this.velx = velx;
    }

    public int getVelx() {
        return velx;
    }

    public void setVely(int vely) {
        this.vely = vely;
    }

    public int getVely() {
        return vely;
    }
}
