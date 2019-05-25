package com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.RegisterBoard;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.Input_ID;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.Input_name;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.Input_password;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.LoginBoard.LoginTitle;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;

public class RegisterBoard extends GameObject {
    private SpriteRenderer spriteRenderer;
    private GUITransform transform;

    Input_ID input_id;
    Input_password input_password;
    Input_name input_name;
    LoginTitle loginTitle;
    RegisterButton registerButton;
    ToLoginBoard toLoginBoard;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("board"));
        spriteRenderer.setZ_index(-1);

        transform = new GUITransform();
        attachComponent(transform);
        transform.scale.x = 1000/1470f;
        transform.scale.y = 1000/1470f;

        input_id = new Input_ID();
        input_id.getTransform().position.y = 1.5f;
        input_password = new Input_password();
        input_password.getTransform().position.y = 0.5f;
        input_name = new Input_name();
        input_name.getTransform().position.y = -0.5f;
        registerButton = new RegisterButton();
        loginTitle = new LoginTitle();
        toLoginBoard = new ToLoginBoard();

        appendChild(loginTitle);
        appendChild(input_id);
        appendChild(input_password);
        appendChild(input_name);
        appendChild(registerButton);
        appendChild(toLoginBoard);
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
