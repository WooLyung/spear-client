package com.example.SpearClient.GameSystem.Scene.Scenes;

import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.LoginBoard.LoginBoard;
import com.example.SpearClient.GameSystem.Scene.Scene;

public class LoginScene extends Scene {
    LoginBoard loginBoard;

    @Override
    public void start() {
        loginBoard = new LoginBoard();

        objs.add(loginBoard);
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
