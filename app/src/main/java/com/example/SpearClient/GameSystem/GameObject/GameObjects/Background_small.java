package com.example.SpearClient.GameSystem.GameObject.GameObjects;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;

public class Background_small extends GameObject {
    private SpriteRenderer spriteRenderer;
    private Transform transform;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("background_sky"));
        spriteRenderer.setZ_index(-10);

        transform = new Transform();
        attachComponent(transform);
        transform.position.y = 5.5f;
        transform.scale.x = 2;
        transform.scale.y = 2;

        appendChild(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("background_ground_small"));
                spriteRenderer.setZ_index(-2);

                transform = new Transform();
                transform.anchor.y = 0;
                transform.position.y = -7.5f / 2;
                transform.scale.x = 0.5f;
                transform.scale.y = 0.5f;
            }
        });
    }
}
