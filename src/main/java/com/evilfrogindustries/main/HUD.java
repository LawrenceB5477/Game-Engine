package com.evilfrogindustries.main;

import java.awt.*;

public class HUD {
    private static int hp = 100;
    private static int green = 255;

    private int score = 0;
    private int level = 1;

    public void tick() {
        hp = Game.clamp(hp, 0, 100);
        green = Game.clamp(green, 0, 255);

        green = hp * 2;
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(75, green, 0));
        g.fillRect(15, 15, hp * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);

        g.setColor(Color.yellow);
        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
    }

    public static int getHp() {
        return hp;
    }

    public static void setHp(int health) {
        hp = health;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
