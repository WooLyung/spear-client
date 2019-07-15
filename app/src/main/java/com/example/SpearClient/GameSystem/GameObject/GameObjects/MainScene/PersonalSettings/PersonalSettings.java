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
        spriteRenderer.bindingImage(GLRenderer.findImage("board_skills"));
        spriteRenderer.setZ_index(-7);

        transform = new Transform();
        attachComponent(transform);
        transform.position.y = -20.48f;
        transform.position.x = -(float) GLView.nowWidth + 0.9f;
        transform.anchor.x = 1;
        transform.scale.x = 0.45f;
        transform.scale.y = 0.45f;
    }
}
