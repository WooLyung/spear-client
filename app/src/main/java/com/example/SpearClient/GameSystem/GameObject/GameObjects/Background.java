package com.example.SpearClient.GameSystem.GameObject.GameObjects;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;

public class Background extends GameObject {
    private SpriteRenderer spriteRenderer;
    private Transform transform;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("sky"));
        spriteRenderer.setZ_index(-3);

        transform = new Transform();
        attachComponent(transform);

        GameObject ground = new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("ground"));
                spriteRenderer.setZ_index(-2);

                transform = new Transform();
                transform.anchor.y = 0;
                transform.position.y = -2;
            }
        };

        appendChild(ground);
    }
}
