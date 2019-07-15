package com.example.SpearClient.Main;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.util.Log;

import com.example.SpearClient.GameIO.MediaPlayers.MediaPlayerHelper;
import com.example.SpearClient.GameIO.MediaPlayers.MediaPlayerHolder;
import com.example.SpearClient.GameSystem.Scene.*;
import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Scene.Scenes.InGameScene;
import com.example.SpearClient.GameSystem.Scene.Scenes.IntroScene;
import com.example.SpearClient.GameSystem.Scene.Scenes.LoginScene;
import com.example.SpearClient.GameSystem.Scene.Scenes.MachingScene;
import com.example.SpearClient.GameSystem.Scene.Scenes.MainScene;

import javax.microedition.khronos.opengles.GL10;

public class Engine {

    public Scene nowScene;
    public MediaPlayerHelper mediaPlayerHelper = new MediaPlayerHelper();
    public static String id = "";
    public static String nickname = "";
    public static String enemyNickname = "";
    public static float sceneChangeTime = 5;

    public Engine() {
        changeScene(new IntroScene());
        start();
    }

    public void start() {

    }

    public void update() {
        nowScene.update();
        Input.update();
        mediaPlayerHelper.update();
        sceneChangeTime += Game.getNoneDeltaTime();
    }

    public void render(GL10 gl) {
        nowScene.render(gl);
    }

    public void finish() {

    }

    public void changeScene(Scene newScene) {
        if (sceneChangeTime > 0.7f) {
            Log.i("Scene", "Scene Changed");

            sceneChangeTime = 0;
            if (nowScene != null)
                nowScene.finish();
            nowScene = newScene;
            newScene.start();
        }
    }
}
