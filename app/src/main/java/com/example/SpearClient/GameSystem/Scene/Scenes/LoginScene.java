package com.example.SpearClient.GameSystem.Scene.Scenes;

import com.example.SpearClient.GameSystem.GameObject.GameObjects.Background;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.LoginBoard.LoginBoard;
import com.example.SpearClient.GameSystem.Scene.Scene;

public class LoginScene extends Scene {
    @Override
    public void start() {
        objs.add(new Background());
        objs.add(new LoginBoard());
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
