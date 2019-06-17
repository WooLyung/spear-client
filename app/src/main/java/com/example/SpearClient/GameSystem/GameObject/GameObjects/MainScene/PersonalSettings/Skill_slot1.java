package com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;

public class Skill_slot1 extends GameObject {
    @Override
    public void start() {
        setName("skill_slot1");

        SpriteRenderer spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("skill_shallow_stab"));
        spriteRenderer.setZ_index(-4);

        transform = new Transform();
        attachComponent(transform);
        transform.position.y = -20.48f - 2.7f;
        transform.position.x = (float) GLView.nowWidth - 5.5f;
        transform.scale.x = 1000/1470f;
        transform.scale.y = 1000/1470f;
    }
}