package com.example.SpearClient.GameSystem.Scene.Scenes;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Background;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Cloud;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.FastMatching;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.GameStart;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.Settings;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.Tutorial;
import com.example.SpearClient.GameSystem.Scene.Scene;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;

public class MainScene extends Scene {
    public enum MAIN_SCENE_STATE {
        UP, MOVE_DOWN, MOVE_UP, DOWN
    }

    FastMatching fastMatching;
    Settings settings;
    Tutorial tutorial;
    GameStart gameStart;

    public String selectedGame = "fast";
    public MAIN_SCENE_STATE state = MAIN_SCENE_STATE.UP;
    public float time = 0;

    @Override
    public void start() {
        fastMatching = new FastMatching();
        settings = new Settings();
        tutorial = new Tutorial();
        gameStart = new GameStart();

        objs.add(fastMatching);
        objs.add(settings);
        objs.add(tutorial);
        objs.add(gameStart);
        objs.add(new Background());
        objs.add(new Cloud());
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("soil"));
                spriteRenderer.setZ_index(-10);

                transform = new Transform();
                attachComponent(transform);
                transform.position.y = -10.24f;
            }
        });
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("under_background"));
                spriteRenderer.setZ_index(-10);

                transform = new Transform();
                attachComponent(transform);
                transform.position.y = -20.48f;
            }
        });
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("left_bricks"));
                spriteRenderer.setZ_index(-9);

                transform = new Transform();
                attachComponent(transform);
                transform.position.y = -20.48f;
                transform.position.x = -(float)GLView.nowWidth;
                transform.scale.x = 1000/1470f;
                transform.scale.y = 1000/1470f;

                transform.anchor.x = 1;
            }
        });
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("right_bricks"));
                spriteRenderer.setZ_index(-9);

                transform = new Transform();
                attachComponent(transform);
                transform.position.y = -20.48f;
                transform.position.x = (float)GLView.nowWidth;
                transform.scale.x = 1000/1470f;
                transform.scale.y = 1000/1470f;

                transform.anchor.x = 0;
            }
        });
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("place"));
                spriteRenderer.setZ_index(-9);

                transform = new Transform();
                attachComponent(transform);
                transform.position.y = -20.48f - 2.7f;
                transform.position.x = -(float)GLView.nowWidth + 4;
                transform.scale.x = 1000/1470f;
                transform.scale.y = 1000/1470f;

                transform.anchor.y = 1;
            }
        });
    }

    @Override
    public void update() {
        super.update();

        if (state == MAIN_SCENE_STATE.MOVE_DOWN) {
            time += Game.deltaTime;
            camera.position.y = -time * 20.48f;

            if (time >= 1) {
                time = 0;
                state = MAIN_SCENE_STATE.DOWN;
                camera.position.y = -20.48f;
            }
        }
        else if (state == MAIN_SCENE_STATE.MOVE_UP) {
            time += Game.deltaTime;
            camera.position.y = 20.48f -time * 20.48f;

            if (time >= 1) {
                time = 0;
                state = MAIN_SCENE_STATE.UP;
                camera.position.y = 0;
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
