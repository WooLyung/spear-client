package com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;

public class PersonalSettings extends GameObject {
    @Override
    public void start() {
        SpriteRenderer spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("board_skin"));
        spriteRenderer.setZ_index(-7);

        float[] color = {
                0.6f, 0.6f, 0.6f, 1,
                0.6f, 0.6f, 0.6f, 1,
                0.6f, 0.6f, 0.6f, 1,
                0.6f, 0.6f, 0.6f, 1
        };
        GLRenderer.imageDatas.get(GLRenderer.findImage("board_skin")).setColors(color);

        transform = new Transform();
        attachComponent(transform);
        transform.position.y = -20.48f - 0.5f;
        transform.position.x = (float) GLView.nowWidth - 5.5f;
        transform.scale.x = 1000/1470f;
        transform.scale.y = 1000/1470f;

        appendChild(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("board_gray"));
                spriteRenderer.setZ_index(-5);

                transform = new Transform();
                attachComponent(transform);
                transform.position.y = -3;
            }
        });

        GameObject skill = new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("board_skills"));
                spriteRenderer.setZ_index(-6);

                transform = new Transform();
                attachComponent(transform);
            }
        };

        appendChild(skill);
    }
}
