package com.example.SpearClient.GameSystem.Scene.Scenes;

import com.example.SpearClient.GameSystem.GameObject.GameObjects.Background;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.FastMatching;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.Settings;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.Tutorial;
import com.example.SpearClient.GameSystem.Scene.Scene;

public class MainScene extends Scene {
    FastMatching fastMatching;
    Settings settings;
    Tutorial tutorial;

    @Override
    public void start() {
        fastMatching = new FastMatching();
        settings = new Settings();
        tutorial = new Tutorial();

        objs.add(fastMatching);
        objs.add(settings);
        objs.add(tutorial);
        objs.add(new Background());
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
