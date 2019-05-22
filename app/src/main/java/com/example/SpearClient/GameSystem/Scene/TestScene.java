package com.example.SpearClient.GameSystem.Scene;

import com.example.SpearClient.GameSystem.GameObject.GameObjects.Body;
import com.example.SpearClient.Main.Game;

import javax.microedition.khronos.opengles.GL10;

public class TestScene extends Scene {

    @Override
    public void start() {
        objs.add(new Body());
        camera.position.x = 0;
    }

    @Override
    public void update() {
        super.update();

        camera.angle += Game.deltaTime * 90;
        camera.setZoomY(0.25f);
        camera.setZoomX(0.25f);
    }

    @Override
    public void finish() {

    }

    @Override
    public void render(GL10 gl) {
        super.render(gl);
    }
}
