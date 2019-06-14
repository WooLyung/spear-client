package com.example.SpearClient.GameSystem.Scene.Scenes;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.TextRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Background;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Cloud;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.FastMatching;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.GameStart;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.Place;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.Settings;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.Tutorial;
import com.example.SpearClient.GameSystem.Other.Camera;
import com.example.SpearClient.GameSystem.Scene.Scene;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.SocketIO.SocketIOBuilder;
import com.example.SpearClient.Types.Vector;

public class MainScene extends Scene {
    public enum MAIN_SCENE_STATE {
        UP, MOVE_DOWN, MOVE_UP, DOWN
    }

    FastMatching fastMatching;
    Settings settings;
    Tutorial tutorial;
    GameStart gameStart;
    Place place;
    PersonalSettings personalSettings;
    GameObject black;

    public String selectedGame = "fast";
    public MAIN_SCENE_STATE state = MAIN_SCENE_STATE.UP;
    public float time = 0;

    @Override
    public void start() {
        fastMatching = new FastMatching();
        settings = new Settings();
        tutorial = new Tutorial();
        gameStart = new GameStart();
        place = new Place();
        personalSettings = new PersonalSettings();
        black = new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("background_black"));
                spriteRenderer.setZ_index(10);

                transform = new GUITransform();
                attachComponent(transform);

                float[] color = {
                        1, 1, 1, 0,
                        1, 1, 1, 0,
                        1, 1, 1, 0,
                        1, 1, 1, 0
                };
                GLRenderer.imageDatas.get(GLRenderer.findImage("background_black")).setColors(color);
            }
        };

        objs.add(fastMatching);
        objs.add(settings);
        objs.add(tutorial);
        objs.add(gameStart);
        objs.add(place);
        objs.add(personalSettings);
        objs.add(black);
        objs.add(new Background());
        objs.add(new Cloud());
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("background_soil"));
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
                spriteRenderer.bindingImage(GLRenderer.findImage("background_under"));
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
    }

    @Override
    public void update() {
        super.update();

        camera.setZoomY(1);
        camera.setZoomX(1);

        if (state == MAIN_SCENE_STATE.MOVE_DOWN) {
            time += Game.getDeltaTime();
            camera.setPosition(new Vector(camera.getPosition().x, -time * 20.48f));
            float[] color = {
                    1, 1, 1, (time <= 0.5f) ? time * 2 : (1 - (time - 0.5f) * 2),
                    1, 1, 1, (time <= 0.5f) ? time * 2 : (1 - (time - 0.5f) * 2),
                    1, 1, 1, (time <= 0.5f) ? time * 2 : (1 - (time - 0.5f) * 2),
                    1, 1, 1, (time <= 0.5f) ? time * 2 : (1 - (time - 0.5f) * 2)
            };
            GLRenderer.imageDatas.get(GLRenderer.findImage("background_black")).setColors(color);

            if (time >= 1) {
                time = 0;
                state = MAIN_SCENE_STATE.DOWN;
                camera.setPosition(new Vector(camera.getPosition().x, -20.48f));
                ((TextRenderer)findObjectByName("name").getComponent("textRenderer")).setText(SocketIOBuilder.id);

                float[] color2 = {
                        1, 1, 1, 0,
                        1, 1, 1, 0,
                        1, 1, 1, 0,
                        1, 1, 1, 0
                };
                GLRenderer.imageDatas.get(GLRenderer.findImage("background_black")).setColors(color2);
            }
        }
        else if (state == MAIN_SCENE_STATE.MOVE_UP) {
            ((TextRenderer)findObjectByName("name").getComponent("textRenderer")).getTextView().setText("");
            time += Game.getDeltaTime();
            camera.setPosition(new Vector(camera.getPosition().x, 20.48f -time * 20.48f));
            float[] color = {
                    1, 1, 1, (time <= 0.5f) ? time * 2 : (1 - (time - 0.5f) * 2),
                    1, 1, 1, (time <= 0.5f) ? time * 2 : (1 - (time - 0.5f) * 2),
                    1, 1, 1, (time <= 0.5f) ? time * 2 : (1 - (time - 0.5f) * 2),
                    1, 1, 1, (time <= 0.5f) ? time * 2 : (1 - (time - 0.5f) * 2)
            };
            GLRenderer.imageDatas.get(GLRenderer.findImage("background_black")).setColors(color);

            if (time >= 1) {
                time = 0;
                state = MAIN_SCENE_STATE.UP;
                camera.setPosition(new Vector(camera.getPosition().x, 0));


                float[] color2 = {
                        1, 1, 1, 0,
                        1, 1, 1, 0,
                        1, 1, 1, 0,
                        1, 1, 1, 0
                };
                GLRenderer.imageDatas.get(GLRenderer.findImage("background_black")).setColors(color2);
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
