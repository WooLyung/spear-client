package com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.LoginBoard;

import android.text.InputType;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.EditTextRenderer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.TextRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;

public class ToRegisterBoard extends GameObject {
    private TextRenderer textRenderer;
    private GUITransform transform;

    @Override
    public void start() {
        transform = new GUITransform();
        attachComponent(transform);

        textRenderer = new TextRenderer();
        attachComponent(textRenderer);

        textRenderer.getTextView().setTextColor(Game.instance.getResources().getColor(R.color.loginColor));
        textRenderer.getTextView().setText("회원가입");
        textRenderer.getTextView().setTextSize(25000 / Game.screenWidth);
        transform.position.x = 2.1f;
        transform.position.y = -1.2f;
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
