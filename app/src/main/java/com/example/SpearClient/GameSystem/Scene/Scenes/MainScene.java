package com.example.SpearClient.GameSystem.Scene.Scenes;

import com.example.SpearClient.GameSystem.GameObject.GameObjects.FastMatching;
import com.example.SpearClient.GameSystem.Scene.Scene;

public class MainScene extends Scene {
    FastMatching fastMatching;

    @Override
    public void start() {
        fastMatching = new FastMatching();
        objs.add(fastMatching);
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
