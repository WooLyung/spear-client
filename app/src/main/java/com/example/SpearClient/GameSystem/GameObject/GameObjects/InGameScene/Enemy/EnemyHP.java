package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Enemy;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.RendererComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.TextRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Engine;
import com.example.SpearClient.Main.Game;

public class EnemyHP extends GameObject {
    SpriteRenderer spriteRenderer, front_spriteRenderer, smooth_spriteRenderer;
    GUITransform transform;
    public GameObject front, smooth, nickname;

    @Override
    public void start() {
        setName("enemyHP");

        nickname = new GameObject() {
            TextRenderer textRenderer;
            GUITransform transform;

            @Override
            public void start() {
                Game.instance.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textRenderer = new TextRenderer();
                        attachComponent(textRenderer);
                        textRenderer.getTextView().setText("기본값");
                        textRenderer.getTextView().setTextSize(14);
                        textRenderer.setHorizontal(2);
                    }
                });

                transform = new GUITransform();
                attachComponent(transform);
                transform.position.x = (float) GLView.defaultWidth - 0.5f;
                transform.position.y = (float) GLView.defaultHeight - 0.45f;
            }

            @Override
            public void update() {
                super.update();

                if (!Engine.enemyNickname.equals("") && textRenderer.getTextView().getText().equals("기본값")) {
                    Game.instance.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textRenderer.getTextView().setText(Engine.enemyNickname);
                        }
                    });
                }
            }
        };
        front = new GameObject() {
            @Override
            public void start() {
                spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("hpbar_front"));
                spriteRenderer.setZ_index(52);

                transform = new Transform();
                attachComponent(transform);
                transform.anchor.x = 0;
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
                transform.anchor.x = 0;
                transform.anchor.y = 0;
            }
        };
        appendChild(front);
        appendChild(smooth);
        appendChild(nickname);

        front_spriteRenderer = (SpriteRenderer) front.getComponent("spriteRenderer");
        smooth_spriteRenderer = (SpriteRenderer) smooth.getComponent("spriteRenderer");

        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("hpbar_back"));
        spriteRenderer.setZ_index(50);

        transform = new GUITransform();
        attachComponent(transform);
        transform.position.x = (float) GLView.defaultWidth - 0.3f;
        transform.position.y = (float) GLView.defaultHeight - 0.6f;
        transform.scale.x = 1000/1470f;
        transform.scale.y = 1000/1470f;
        transform.anchor.x = 0;
        transform.anchor.y = 0;

        front_spriteRenderer.setDir(RendererComponent.DIRECTION.RIGHT);
        smooth_spriteRenderer.setDir(RendererComponent.DIRECTION.RIGHT);
    }

    @Override
    public void update() {
        super.update();

        if (front_spriteRenderer.getFill() != smooth_spriteRenderer.getFill()) {
            if (front_spriteRenderer.getFill() > smooth_spriteRenderer.getFill()) {
                smooth_spriteRenderer.setFill(smooth_spriteRenderer.getFill() + Game.getDeltaTime() / 3);
                if (front_spriteRenderer.getFill() < smooth_spriteRenderer.getFill()) {
                    smooth_spriteRenderer.setFill(front_spriteRenderer.getFill());
                }
            }
            else {
                smooth_spriteRenderer.setFill(smooth_spriteRenderer.getFill() - Game.getDeltaTime() / 3);
                if (front_spriteRenderer.getFill() > smooth_spriteRenderer.getFill()) {
                    smooth_spriteRenderer.setFill(front_spriteRenderer.getFill());
                }
            }
        }
    }
}
