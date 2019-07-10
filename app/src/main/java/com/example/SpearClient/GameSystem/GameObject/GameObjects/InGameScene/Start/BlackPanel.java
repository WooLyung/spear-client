package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Start;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;

public class BlackPanel extends GameObject {
    SpriteRenderer spriteRenderer;
    GUITransform transform;

    int imageCode;

    @Override
    public void start() {
        imageCode = GLRenderer.findImage("background_black");

        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(imageCode);
        spriteRenderer.setZ_index(55);

        transform = new GUITransform();
        attachComponent(transform);

        float[] color = {
                1, 1, 1, 0.7f,
                1, 1, 1, 0.7f,
                1, 1, 1, 0.7f,
                1, 1, 1, 0.7f
        };
        GLRenderer.imageDatas.get(imageCode).setColors(color);
    }

    public void updateImage(float time) {
        float[] color = {
                1, 1, 1, (1 - time / 2) * 0.7f,
                1, 1, 1, (1 - time / 2) * 0.7f,
                1, 1, 1, (1 - time / 2) * 0.7f,
                1, 1, 1, (1 - time / 2) * 0.7f
        };
        GLRenderer.imageDatas.get(imageCode).setColors(color);
    }
}
