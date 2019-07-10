package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player;

import com.example.SpearClient.GameSystem.Component.Components.EffectComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;

public class PlayerEffect_rush extends GameObject {
    SpriteRenderer spriteRenderer;
    Transform transform;
    public EffectComponent effectComponent;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("effect_rush"));
        spriteRenderer.setZ_index(8);

        effectComponent = new EffectComponent();
        attachComponent(effectComponent);
        effectComponent.setColors(new float[]{
                1, 1, 1, 0,
                1, 1, 1, 0,
                1, 1, 1, 0,
                1, 1, 1, 0
        });

        transform = new Transform();
        attachComponent(transform);
        transform.scale.x = 1f;
        transform.scale.y = 0.7f;
        transform.anchor.x = 0;
        transform.position.x = 0.2f;
    }
}
