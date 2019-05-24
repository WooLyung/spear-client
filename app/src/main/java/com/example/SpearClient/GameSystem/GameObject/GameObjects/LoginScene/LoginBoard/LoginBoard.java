package com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.LoginBoard;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.Input_ID;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.Input_password;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;

public class LoginBoard extends GameObject {
    private SpriteRenderer spriteRenderer;
    private GUITransform transform;

    Input_ID input_id;
    Input_password input_password;
    LoginButton loginButton;
    ToRegisterBoard toRegisterBoard;
    LoginTitle loginTitle;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("login_board"));
        spriteRenderer.setZ_index(-1);

        transform = new GUITransform();
        attachComponent(transform);
        transform.scale.x = 1000/1470f;
        transform.scale.y = 1000/1470f;

        input_id = new Input_ID();
        input_password = new Input_password();
        loginButton = new LoginButton();
        toRegisterBoard = new ToRegisterBoard();
        loginTitle = new LoginTitle();

        appendChild(input_id);
        appendChild(input_password);
        appendChild(loginButton);
        appendChild(toRegisterBoard);
        appendChild(loginTitle);
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
