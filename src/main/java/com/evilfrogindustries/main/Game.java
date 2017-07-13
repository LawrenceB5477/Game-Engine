package com.evilfrogindustries.main;

import com.evilfrogindustries.main.Objects.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

//Creates the canvas of the game and controls the game's logic.
//TODO improve the threads
public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = -5933468800831977525L;
    private static final int WIDTH = 640;
    private static final int HEIGHT = WIDTH / 12 * 9;

    private static ObjectController handler;
    private Thread thread;
    private boolean running = false;
    private FPS fps;
    private HUD hud;
    private Spawner spawner;
    private Player player;

    public Game() {
        handler = new ObjectController();
        this.addKeyListener(new KeyInput(handler));
        new Window(WIDTH, HEIGHT, "Game", this);
        diag(this);

        //Add objects to the main handler
        hud = new HUD();
        player = new Player(20, 200, ID.PLAYER, handler);
        handler.addObject(player);
        spawner = new Spawner(handler, hud, player);
        handler.addObject(fps = new FPS(Game.getRoomWidth() - 65, 10, ID.FPS));

    }

    //Main Method
    public static void main(String[] args) {
        new Game();
    }

    //When a new Window class is created, a new thread is created from this class.
    public void start() {
        thread = new Thread(this, "Main Game Thread.");
        thread.start();
        running = true;
    }

    //Stops the thread
    public void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Game loop. Tries to render objects ASAP, and provides a constant tick time
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        //60 Ticks per second
        double amountOfTicks = 60.0;
        //How many nanoseconds one tick is
        double ns = 1000000000 / amountOfTicks;
        //How many ticks have occurred
        double deltaTicks = 0;
        //How many frames we're getting per second
        int framesPerSecond = 0;

        //the main game loop
        while (running) {
            //Current time
            long now = System.nanoTime();
            //How many ticks have occurred since the last iteration
            deltaTicks += (now - lastTime) / ns;
            lastTime = now;

            //If a tick has passed, call the tick function
            while (deltaTicks >= 1) {
                tick();
                deltaTicks--;
            }
            //CPU tries to render game as fast as possible
            if (running) {
                render();
            }
            //CPU calls this loop as fast as it can. Depending on speed, different amount of frames called.
            framesPerSecond++;

            /*If a second has passed, print out the number of frames that have occurred the last second,
             and resets the frame counter */
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                fps.setfps(framesPerSecond);
                System.out.println("FPS: " + framesPerSecond);
                framesPerSecond = 0;
            }
        }
        stop();
    }

    //Tick 60 times a second
    private void tick() {
        //Ticks all of the objects
        handler.tick();
        hud.tick();
        spawner.tick();
    }

    //Renders as fast as possible
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            //Create a buffer strategy with three buffers. One front buffer, one back buffer, one intermediate buffer
            //Probably blit buffering
            this.createBufferStrategy(3);
            return;
        }

        //Gets the off screen buffer so it can be drawn to
        Graphics g = bs.getDrawGraphics();
        //START OF RENDERING

        //Sets the background to a black rectangle
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //Lets all objects write to the back buffer
        handler.render(g);
        hud.render(g);
        //END OF RENDERING
        g.dispose();
        //Switches to the next available buffer either through page flipping or bliting
        bs.show();
    }

    //Diagnostics on buffering
    private void diag(Canvas c) {
        //See if page flipping and multiple buffers are enabled
        GraphicsConfiguration gf = c.getGraphicsConfiguration();
        BufferCapabilities bc = gf.getBufferCapabilities();
        System.out.println("Page flipping allowed: " + bc.isPageFlipping());
        System.out.println("Full Screen required for page flipping: " + bc.isFullScreenRequired());
        System.out.println("Support for multiple back buffers: " + bc.isMultiBufferAvailable());
        System.out.println("Type of page flipping: " + bc.getFlipContents());
    }

    //General function for a value to stay inside a range
    public static int clamp(int var, int min, int max) {
        if (var >= max) {
            return max;
        } else if (var <= min) {
            return min;
        } else {
            return var;
        }
    }

    //Getters
    public static int getRoomWidth() {
        return WIDTH;
    }

    public static int getRoomHeight() {
        return HEIGHT;
    }
}
