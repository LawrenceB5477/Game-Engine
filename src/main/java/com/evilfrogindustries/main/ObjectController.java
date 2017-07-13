package com.evilfrogindustries.main;

import com.evilfrogindustries.main.Objects.GameObject;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

//Update and render all of the objects
public class ObjectController {
    private static List<GameObject> objects = new LinkedList<GameObject>();

    //Call the tick method for each object
    public void tick() {

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).tick();
        }
    }

    //Call the render method for each object. Passes in the graphics object for the back buffer.
    public void render(Graphics g) {
        for(GameObject object : objects) {
            object.render(g);
        }
    }

    public void addObject(GameObject object) {
        objects.add(object);
    }

    public void removeObject(GameObject object) {
        objects.remove(object);
    }

    public List<GameObject> getObjects() {
        return objects;
    }
}
