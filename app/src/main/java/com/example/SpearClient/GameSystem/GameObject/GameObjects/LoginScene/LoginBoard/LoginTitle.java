package com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.LoginBoard;

import android.graphics.Typeface;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.TextRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;

public class LoginTitle extends GameObject {
    private TextRenderer textRenderer;
    private GUITransform transform;

    @Override
    public void start() {
        transform = new GUITransform();
        attachComponent(transform);

        textRenderer = new TextRenderer();
        attachComponent(textRenderer);

        textRenderer.getTextView().setTextColor(Game.instance.getResources().getColor(R.color.loginColor));
        textRenderer.getTextView().setText("결투 지원서");
        textRenderer.getTextView().setTextSize(33);
        transform.position.x = 0;
        transform.position.y = 2.6f;
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
