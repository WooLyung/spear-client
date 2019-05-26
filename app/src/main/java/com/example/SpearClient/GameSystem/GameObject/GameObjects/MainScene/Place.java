package com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.TextRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;

public class Place extends GameObject {
    @Override
    public void start() {
        SpriteRenderer spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("place"));
        spriteRenderer.setZ_index(-9);

        transform = new Transform();
        attachComponent(transform);
        transform.position.y = -20.48f - 2.7f;
        transform.position.x = -(float) GLView.nowWidth + 4;
        transform.scale.x = 1000/1470f;
        transform.scale.y = 1000/1470f;

        transform.anchor.y = 1;

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

        GameObject knight = new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("knight_purple"));
                spriteRenderer.setZ_index(-8);

                transform = new Transform();
                attachComponent(transform);
                transform.position.x = 0.5f;
                transform.position.y = 4f;
                transform.scale.x = 0.8f;
                transform.scale.y = 0.8f;
            }
        };

        appendChild(knight);
        appendChild(name);
    }
}
