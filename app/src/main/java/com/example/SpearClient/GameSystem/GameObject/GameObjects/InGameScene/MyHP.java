package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.RendererComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;

public class MyHP extends GameObject {
    SpriteRenderer spriteRenderer, front_spriteRenderer, smooth_spriteRenderer;
    GUITransform transform;
    public GameObject front, smooth;

    @Override
    public void start() {
        setName("myHP");

        front = new GameObject() {
            @Override
            public void start() {
                spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("hpbar_front"));
                spriteRenderer.setZ_index(52);

                transform = new Transform();
                attachComponent(transform);
                transform.anchor.x = 1;
                transform.anchor.y = 0;
            }
        };
        smooth = new GameObject() {
            @Override
            public void start() {
                spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("hpbar_smooth"));
                spriteRenderer.setZ_index(51);

                transform = new Transform();
                attachComponent(transform);
                transform.anchor.x = 1;
                transform.anchor.y = 0;
            }
        };
        appendChild(front);
        appendChild(smooth);

        front_spriteRenderer = (SpriteRenderer) front.getComponent("spriteRenderer");
        smooth_spriteRenderer = (SpriteRenderer) smooth.getComponent("spriteRenderer");

        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("hpbar_back"));
        spriteRenderer.setZ_index(50);

        transform = new GUITransform();
        attachComponent(transform);
        transform.position.x = -(float) GLView.defaultWidth + 0.3f;
        transform.position.y = (float) GLView.defaultHeight - 0.3f;
        transform.scale.x = 1000/1470f;
        transform.scale.y = 1000/1470f;
        transform.anchor.x = 1;
        transform.anchor.y = 0;

        front_spriteRenderer.setDir(RendererComponent.DIRECTION.LEFT);
        smooth_spriteRenderer.setDir(RendererComponent.DIRECTION.LEFT);
    }

    @Override
    public void update() {
        super.update();

        if (front_spriteRenderer.getFill() != smooth_spriteRenderer.getFill()) {
            if (front_spriteRenderer.getFill() > smooth_spriteRenderer.getFill()) {
                smooth_spriteRenderer.setFill(smooth_spriteRenderer.getFill() + Game.getDeltaTime() / 2);
                if (front_spriteRenderer.getFill() < smooth_spriteRenderer.getFill()) {
                    smooth_spriteRenderer.setFill(front_spriteRenderer.getFill());
                }
            }
            else {
                smooth_spriteRenderer.setFill(smooth_spriteRenderer.getFill() - Game.getDeltaTime() / 2);
                if (front_spriteRenderer.getFill() > smooth_spriteRenderer.getFill()) {
                    smooth_spriteRenderer.setFill(front_spriteRenderer.getFill());
                }
            }
        }
    }
}
