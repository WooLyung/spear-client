package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;

public class Player extends GameObject {
    SpriteRenderer spriteRenderer;

    @Override
    public void start() {
        setName("player");

        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("knight_purple"));
        spriteRenderer.setZ_index(0);

        transform = new Transform();
        attachComponent(transform);
        transform.position.y = -0.3f;
        transform.scale.x = 1000/1470f;
        transform.scale.y = 1000/1470f;
        transform.anchor.x = 0.61f;
    }
}
