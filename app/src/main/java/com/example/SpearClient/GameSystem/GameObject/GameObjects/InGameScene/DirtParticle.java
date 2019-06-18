package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene;

import android.util.Log;

import com.example.SpearClient.GameSystem.Component.Components.ParticleComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;

import java.util.Random;

public class DirtParticle extends GameObject {
    SpriteRenderer spriteRenderer;
    Transform transform;
    ParticleComponent particleComponent;
    Random random;

    @Override
    public void start() {
        random = new Random();
        spriteRenderer = new SpriteRenderer();
        spriteRenderer.bindingImage(GLRenderer.findImage("particle_dirt"));
        spriteRenderer.setZ_index(20);
        attachComponent(spriteRenderer);

        transform = new Transform();
        attachComponent(transform);

        particleComponent = new ParticleComponent();
        particleComponent.speed = random.nextInt(200) / 100 + 0.2f;
        particleComponent.airRegistance = 0.3f;
        particleComponent.lifeTime = 1f;
        particleComponent.angle = random.nextInt(360);
        attachComponent(particleComponent);
    }
}
