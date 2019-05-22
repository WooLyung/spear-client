package com.example.SpearClient.GameSystem.GameObject.GameObjects;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.AnimationRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GameSystem.GameObject.GameObject;

public class TestObject2 extends GameObject {

    @Override
    public void start() {
        AnimationRenderer animationRenderer = new AnimationRenderer();
        animationRenderer.bindingImage(new int[]{ GLRenderer.findImage("circle"), GLRenderer.findImage("circle2") });

        attachComponent(new GUITransform());
        attachComponent(animationRenderer);
        renderer.image[0] = GLRenderer.findImage("circle");
        transform.position.x = 2;
        transform.position.y = 4;

        getRenderer().setZ_index(-1);

        renderer.setIsVisible(false);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
