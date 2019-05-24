package com.example.SpearClient.GameSystem.Scene;

import com.example.SpearClient.GameSystem.Camera;
import com.example.SpearClient.GameSystem.GameObject.GameObject;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

abstract public class Scene {

    public ArrayList<GameObject> objs = new ArrayList<GameObject>();
    public Camera camera = new Camera();

    abstract public void start();

    public void update() {
        for (GameObject gameObject : objs) {
            if (gameObject.getIsActive())
                gameObject.update();
        }
    }

    public void render(GL10 gl) {
        for (GameObject gameObject : objs) {
            if (gameObject.getIsActive() && (gameObject.getRenderer() == null || gameObject.getRenderer().getIsVisible()))
                gameObject.render(gl);
        }
    }

    public void finish() {
        for (GameObject gameObject : objs) {
            gameObject.finish();
        }
    }

    public GameObject findObjectByName(String name) {
        for (GameObject gameObject : objs) {
            if (gameObject.findOfName(name) != null)
                return gameObject.findOfName(name) ;
        }

        return null;
    }

    public ArrayList<GameObject> findObjectsByName(String name) {
        ArrayList<GameObject> returnObjects = new ArrayList<>();

        for (GameObject gameObject : objs) {
            if (gameObject.findOfName(name) != null)
                returnObjects.add(gameObject.findOfName(name) );
        }

        return returnObjects;
    }

    public GameObject findObjectByTag(String tag) {
        for (GameObject gameObject : objs) {
            if (gameObject.findOfTag(tag) != null)
                return gameObject.findOfTag(tag);
        }

        return null;
    }

    public ArrayList<GameObject> findObjectsByTag(String tag) {
        ArrayList<GameObject> returnObjects = new ArrayList<>();

        for (GameObject gameObject : objs) {
            if (gameObject.findOfTag(tag) != null)
                returnObjects.add(gameObject.findOfTag(tag));
        }

        return returnObjects;
    }
}