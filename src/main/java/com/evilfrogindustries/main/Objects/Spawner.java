package com.evilfrogindustries.main.Objects;

import com.evilfrogindustries.main.Game;
import com.evilfrogindustries.main.HUD;
import com.evilfrogindustries.main.ObjectController;

import java.util.Random;

//Controls the level logic of the game
public class Spawner {
    private ObjectController handler;
    private HUD hud;
    private int scoreKeep = 0;
    private Random ran = new Random();
    Player player;

    public Spawner(ObjectController handler, HUD hud, Player player) {
        this.handler = handler;
        this.hud = hud;
        this.player = player;
        handler.addObject(new BasicEnemy(ran.nextInt(Game.getRoomWidth() - 20), ran.nextInt(Game.getRoomHeight() - 20), ID.BASICENEMY, handler));
    }

    public void tick() {
        scoreKeep++;
        hud.setScore(scoreKeep);
        if (scoreKeep >= 1000) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            if (hud.getLevel() == 2) {
                handler.addObject(new BasicEnemy(ran.nextInt(Game.getRoomWidth() - 20), ran.nextInt(Game.getRoomHeight() - 20), ID.BASICENEMY, handler));
                handler.addObject(new BasicEnemy(ran.nextInt(Game.getRoomWidth() - 20), ran.nextInt(Game.getRoomHeight() - 20), ID.BASICENEMY, handler));
            } else if (hud.getLevel() == 3) {
                handler.addObject(new Turret(300, 20, ID.TURRET, player, handler));
            }
        }
    }
}
