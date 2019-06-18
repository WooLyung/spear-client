package com.example.SpearClient.GameSystem.Scene.Scenes;

import android.util.Log;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.TextRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Background_small;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Cloud;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.FastMatching;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.GameStart;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings.PersonalSettings;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings.Selected_skill1_1;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings.Selected_skill1_2;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings.Selected_skill1_3;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings.Selected_skill1_4;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings.Selected_skill2_1;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings.Selected_skill2_2;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings.Selected_skill2_3;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings.Selected_skill2_4;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings.Skill_slot1;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings.Skill_slot2;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings.Skill_slot3;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings.Skill_slot4;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings.Skill_slot5;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.PersonalSettings.Skill_slot6;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.Place;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.Settings;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene.Tutorial;
import com.example.SpearClient.GameSystem.Other.ActionManager;
import com.example.SpearClient.GameSystem.Scene.Scene;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Engine;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.SocketIO.SocketIOBuilder;
import com.example.SpearClient.Types.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import io.socket.emitter.Emitter;

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

    Skill_slot1 skill_slot1;
    Skill_slot2 skill_slot2;
    Skill_slot3 skill_slot3;
    Skill_slot4 skill_slot4;
    Skill_slot5 skill_slot5;
    Skill_slot6 skill_slot6;
    Selected_skill1_1 selected_skill1_1;
    Selected_skill1_2 selected_skill1_2;
    Selected_skill1_3 selected_skill1_3;
    Selected_skill1_4 selected_skill1_4;
    Selected_skill2_1 selected_skill2_1;
    Selected_skill2_2 selected_skill2_2;
    Selected_skill2_3 selected_skill2_3;
    Selected_skill2_4 selected_skill2_4;

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
        objs.add(new Background_small());
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

        skillSet();


        try {
            SocketIOBuilder.getInstance().getSkill(new JSONObject("{\"username\":" + SocketIOBuilder.id + "}"), new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    try {
                        JSONObject jsonObject = new JSONObject(args[0].toString());
                        JSONArray skillArray1 = jsonObject.getJSONArray("skill1Array");
                        JSONArray skillArray2 = jsonObject.getJSONArray("skill2Array");

                        ActionManager.skill1[0] = skillArray1.getInt(0);
                        ActionManager.skill1[1] = skillArray1.getInt(1);
                        ActionManager.skill1[2] = skillArray1.getInt(2);
                        ActionManager.skill1[3] = skillArray1.getInt(3);

                        ActionManager.skill2[0] = skillArray2.getInt(0);
                        ActionManager.skill2[1] = skillArray2.getInt(1);
                        ActionManager.skill2[2] = skillArray2.getInt(2);
                        ActionManager.skill2[3] = skillArray2.getInt(3);

                        updateImages();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateImages() {
        selected_skill1_1.updateImage();
        selected_skill1_2.updateImage();
        selected_skill1_3.updateImage();
        selected_skill1_4.updateImage();
        selected_skill2_1.updateImage();
        selected_skill2_2.updateImage();
        selected_skill2_3.updateImage();
        selected_skill2_4.updateImage();
    }

    private void skillSet() {
        skill_slot1 = new Skill_slot1();
        skill_slot2 = new Skill_slot2();
        skill_slot3 = new Skill_slot3();
        skill_slot4 = new Skill_slot4();
        skill_slot5 = new Skill_slot5();
        skill_slot6 = new Skill_slot6();
        selected_skill1_1 = new Selected_skill1_1();
        selected_skill1_2 = new Selected_skill1_2();
        selected_skill1_3 = new Selected_skill1_3();
        selected_skill1_4 = new Selected_skill1_4();
        selected_skill2_1 = new Selected_skill2_1();
        selected_skill2_2 = new Selected_skill2_2();
        selected_skill2_3 = new Selected_skill2_3();
        selected_skill2_4 = new Selected_skill2_4();

        objs.add(skill_slot1);
        objs.add(skill_slot2);
        objs.add(skill_slot3);
        objs.add(skill_slot4);
        objs.add(skill_slot5);
        objs.add(skill_slot6);
        objs.add(selected_skill1_1);
        objs.add(selected_skill1_2);
        objs.add(selected_skill1_3);
        objs.add(selected_skill1_4);
        objs.add(selected_skill2_1);
        objs.add(selected_skill2_2);
        objs.add(selected_skill2_3);
        objs.add(selected_skill2_4);
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("line"));
                spriteRenderer.setZ_index(-5);

                transform = new Transform();
                attachComponent(transform);
                transform.position.y = -20.48f + 2.15f;
                transform.position.x = (float) GLView.nowWidth - 5.5f;
                transform.scale.x = 1000/1470f;
                transform.scale.y = 1000/1470f;
            }
        });
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("line"));
                spriteRenderer.setZ_index(-5);

                transform = new Transform();
                attachComponent(transform);
                transform.position.y = -20.48f + 0.25f;
                transform.position.x = (float) GLView.nowWidth - 5.5f;
                transform.scale.x = 1000/1470f;
                transform.scale.y = 1000/1470f;
            }
        });
    }

    @Override
    public void update() {
        super.update();

        if (Input.backkeyDown) {
            if (state == MAIN_SCENE_STATE.DOWN) {
                state = MAIN_SCENE_STATE.MOVE_UP;
            }
            else if (state == MAIN_SCENE_STATE.UP) {
                Game.instance.finish();
            }
        }

        camera.setZoomY(1);
        camera.setZoomX(1);

        if (state == MAIN_SCENE_STATE.MOVE_DOWN) {

            time += Game.getDeltaTime();
            float time2 = (time > 1) ? 1 : time;

            camera.setPosition(new Vector(camera.getPosition().x, -time2 * 20.48f));
            float alpha = 0;
            if (time <= 0.5f) {
                alpha = time * 2;
            }
            else if (time <= 1) {
                alpha = 1;
            }
            else {
                alpha = 1 - (time - 1) * 2;
            }

            float[] color = {
                    1, 1, 1, alpha,
                    1, 1, 1, alpha,
                    1, 1, 1, alpha,
                    1, 1, 1, alpha
            };
            GLRenderer.imageDatas.get(GLRenderer.findImage("background_black")).setColors(color);

            if (time >= 1.5f) {
                time = 0;
                state = MAIN_SCENE_STATE.DOWN;
                camera.setPosition(new Vector(camera.getPosition().x, -20.48f));
                Game.instance.runOnUiThread(new Runnable() {
                    public void run() {
                        ((TextRenderer)findObjectByName("name").getComponent("textRenderer")).setText(Engine.nickname);
                    }
                });

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
            Game.instance.runOnUiThread(new Runnable() {
                public void run() {
                    ((TextRenderer)findObjectByName("name").getComponent("textRenderer")).getTextView().setText("");
                }
            });

            time += Game.getDeltaTime();
            float time2 = (time < 0.5f) ? 0 : time - 0.5f;

            camera.setPosition(new Vector(camera.getPosition().x, -20.48f + time2 * 20.48f));
            float alpha = 0;
            if (time <= 0.5f) {
                alpha = time * 2;
            }
            else if (time <= 1) {
                alpha = 1;
            }
            else {
                alpha = 1 - (time - 1) * 2;
            }

            float[] color = {
                    1, 1, 1, alpha,
                    1, 1, 1, alpha,
                    1, 1, 1, alpha,
                    1, 1, 1, alpha
            };
            GLRenderer.imageDatas.get(GLRenderer.findImage("background_black")).setColors(color);

            if (time >= 1.5f) {
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
