package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.ResultBoard;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.Input_ID;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.Input_password;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.LoginBoard.LoginButton;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.LoginBoard.LoginTitle;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.LoginBoard.ToRegisterBoard;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;

public class ResultBoard extends GameObject {
    private SpriteRenderer spriteRenderer;
    private GUITransform transform;

    ResultTitle resultTitle;
    RegameButton regameButton;
    HomeButton homeButton;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("board"));
        spriteRenderer.setZ_index(60);

        transform = new GUITransform();
        attachComponent(transform);
        transform.scale.x = 1000/1470f;
        transform.scale.y = 1000/1470f;

        resultTitle = new ResultTitle();
        regameButton = new RegameButton();
        homeButton = new HomeButton();

        appendChild(resultTitle);
        appendChild(regameButton);
        appendChild(homeButton);
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
