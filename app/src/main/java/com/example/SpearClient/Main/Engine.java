package com.example.SpearClient.Main;

import com.example.SpearClient.GameSystem.Scene.*;
import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Scene.Scenes.IntroScene;
import com.example.SpearClient.GameSystem.Scene.Scenes.LoginScene;
import com.example.SpearClient.GameSystem.Scene.Scenes.MainScene;

import javax.microedition.khronos.opengles.GL10;

public class Engine {

    public Scene nowScene;

    public Engine() {
        changeScene(new IntroScene());
        start();
    }

    public void start() {

    }

    public void update() {
        nowScene.update();
        Input.update();
    }

    public void render(GL10 gl) {
        nowScene.render(gl);
    }

    public void finish() {

    }

    public void changeScene(Scene newScene) {
        if (nowScene != null)
            nowScene.finish();
        nowScene = newScene;
        newScene.start();
    }
}
