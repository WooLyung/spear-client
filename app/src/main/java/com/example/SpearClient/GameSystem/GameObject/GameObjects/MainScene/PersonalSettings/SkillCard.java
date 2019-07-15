package com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.TextRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;

public class SkillCard extends GameObject {
    public SpriteRenderer spriteRenderer;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("skillcard_none"));
        spriteRenderer.setZ_index(-9);

        transform = new Transform();
        attachComponent(transform);
        transform.position.y = -20.48f;
        transform.position.x = (float) GLView.nowWidth - 1.2f;
        transform.anchor.x = 0;
        transform.scale.x = 0.5f;
        transform.scale.y = 0.5f;

        GameObject name = new GameObject() {
            @Override
            public void start() {
                setName("name");

                TextRenderer textRenderer = new TextRenderer();
                attachComponent(textRenderer);
                textRenderer.getTextView().setTextColor(Game.instance.getResources().getColor(R.color.loginColor));
                textRenderer.getTextView().setText("");
                textRenderer.getTextView().setTextSize(25);

                transform = new GUITransform();
                transform.position.x = -(float) GLView.nowWidth + 4;
                transform.position.y = -1.83f;
                attachComponent(transform);
            }
        };

        appendChild(name);
    }
}
