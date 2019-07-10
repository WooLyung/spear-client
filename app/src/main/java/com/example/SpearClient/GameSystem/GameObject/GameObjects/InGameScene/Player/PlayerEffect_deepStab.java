package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player;

import com.example.SpearClient.GameSystem.Component.Components.EffectComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.Main.Game;

public class PlayerEffect_deepStab extends GameObject {
    SpriteRenderer spriteRenderer;
    Transform transform;
    public EffectComponent effectComponent;
    private float time = 0;
    private boolean isPlay = false;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("effect_deep_stab"));
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
        transform.position.x = 3f;
    }

    @Override
    public void update() {
        super.update();

        if (isPlay) {
            time += Game.getDeltaTime();

            if (time > 0.75f) {
                time = 0;
                isPlay = false;
            }
            else if (time > 0.25f) {
                transform.position.x = 3 + time * 8;
                effectComponent.setColors(new float[]{
                        1, 1, 1, (0.75f - time) * 2,
                        1, 1, 1, (0.75f - time) * 2,
                        1, 1, 1, (0.75f - time) * 2,
                        1, 1, 1, (0.75f - time) * 2
                });
            }
        }
    }

    public void play() {
        isPlay = true;
        time = 0;
        transform.position.x = 3;
    }
}
