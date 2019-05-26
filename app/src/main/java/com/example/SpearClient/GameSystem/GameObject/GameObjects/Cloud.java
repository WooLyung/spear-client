package com.example.SpearClient.GameSystem.GameObject.GameObjects;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.Main.Game;

public class Cloud extends GameObject {
    private SpriteRenderer spriteRenderer;
    private Transform transform;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("background_cloud"));
        spriteRenderer.setZ_index(-9);

        transform = new Transform();
        attachComponent(transform);
        transform.position.y = 1.5f;
        transform.scale.x = 0.5f;
        transform.scale.y = 0.5f;

        appendChild(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("background_cloud"));
                spriteRenderer.setZ_index(-9);

                transform = new Transform();
                transform.position.x = 40.96f;
            }
        });
        appendChild(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("background_cloud"));
                spriteRenderer.setZ_index(-9);

                transform = new Transform();
                transform.position.x = 81.92f;
            }
        });
    }

    @Override
    public void update() {
        super.update();

        transform.position.x -= Game.deltaTime;
        if (transform.position.x <= -40.96f) {
            transform.position.x = 0;
        }
    }
}
