package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.RendererComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;

public class Blood extends GameObject {
    SpriteRenderer spriteRenderer;
    GUITransform transform;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("blood"));
        spriteRenderer.setZ_index(60);

        transform = new GUITransform();
        attachComponent(transform);
        transform.scale.x = 0.7f;
        transform.scale.y = 0.4f;
        transform.anchor.x = 1;
        transform.anchor.y = 0;
    }

    @Override
    public void update() {
        super.update();
    }
}
