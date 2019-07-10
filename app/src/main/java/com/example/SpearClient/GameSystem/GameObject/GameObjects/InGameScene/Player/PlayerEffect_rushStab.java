package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player;

import com.example.SpearClient.GameSystem.Component.Components.EffectComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;

public class PlayerEffect_rushStab extends GameObject {
    SpriteRenderer spriteRenderer;
    Transform transform;
    public EffectComponent effectComponent;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("effect_rush_stab"));
        spriteRenderer.setZ_index(25);

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
        transform.position.x = 4.5f;
    }
}
