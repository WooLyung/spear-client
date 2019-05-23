package com.example.SpearClient.GameSystem.Scene.Scenes;

import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.Input_ID;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.Input_name;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.Input_password;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.LoginButton;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.RegisterButton;
import com.example.SpearClient.GameSystem.Scene.Scene;

public class LoginScene extends Scene {
    Input_ID input_id;
    Input_name input_name;
    Input_password input_password;
    LoginButton loginButton;
    RegisterButton registerButton;

    @Override
    public void start() {
        input_id = new Input_ID();
        input_password = new Input_password();
        input_name = new Input_name();
        loginButton = new LoginButton();
        registerButton = new RegisterButton();

        objs.add(input_id);
        objs.add(input_name);
        objs.add(input_password);
        objs.add(loginButton);
        objs.add(registerButton);
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
