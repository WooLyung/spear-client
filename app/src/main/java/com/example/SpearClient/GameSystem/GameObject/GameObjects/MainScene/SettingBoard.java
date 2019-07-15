package com.example.SpearClient.GameSystem.GameObject.GameObjects.MainScene;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Component.Components.EffectComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.Scene.Scenes.MainScene;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.Main.Game;

public class SettingBoard extends GameObject {
    SpriteRenderer spriteRenderer;
    GUITransform transform;

    @Override
    public void start() {
        setName("setting");

        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("setting_board"));
        spriteRenderer.setZ_index(90);

        transform = new GUITransform();
        attachComponent(transform);
        transform.scale.x = 0.6f;
        transform.scale.y = 0.6f;

        appendChild(new GameObject() {
            SpriteRenderer spriteRenderer;

            @Override
            public void start() {
                spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("background_black2"));
                spriteRenderer.setZ_index(89);

                GUITransform transform = new GUITransform();
                attachComponent(transform);
                transform.scale.x = 2f;
                transform.scale.y = 2f;

                EffectComponent effectComponent = new EffectComponent();
                attachComponent(effectComponent);
                float[] color = {
                        1, 1, 1, 0.7f,
                        1, 1, 1, 0.7f,
                        1, 1, 1, 0.7f,
                        1, 1, 1, 0.7f
                };
                effectComponent.setColors(color);
            }
        });

        appendChild(new GameObject() {
            SpriteRenderer spriteRenderer1;

            @Override
            public void start() {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Game.instance);
                boolean isChecked = pref.getBoolean("setting1", false);

                spriteRenderer1 = new SpriteRenderer();
                attachComponent(spriteRenderer1);
                if (isChecked)
                    spriteRenderer1.bindingImage(GLRenderer.findImage("setting_on"));
                else
                    spriteRenderer1.bindingImage(GLRenderer.findImage("setting_off"));
                spriteRenderer1.setZ_index(91);

                GUITransform transform = new GUITransform();
                attachComponent(transform);
                transform.scale.x = 0.7f;
                transform.scale.y = 0.7f;
                transform.position.x = 2.7f;
                transform.position.y = 1.2f;
            }

            @Override
            public void update() {
                super.update();

                for (int i = 0; i < 5; i++) {
                    if (Input.getTouchState(i) == Input.TOUCH_STATE.DOWN) {
                        if (Math.abs(Input.getTouchWorldPos(i).x - transform.position.x) <= 200 / 100f
                                && Math.abs(Input.getTouchWorldPos(i).y - transform.position.y) <= 60 / 100f) { // 버튼을 클릭했을 경우
                            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Game.instance);
                            boolean isChecked = pref.getBoolean("setting1", true);

                            SharedPreferences.Editor editor = pref.edit();
                            editor.putBoolean("setting1", !isChecked);
                            editor.commit();

                            if (isChecked)
                                spriteRenderer1.bindingImage(GLRenderer.findImage("setting_off"));
                            else
                                spriteRenderer1.bindingImage(GLRenderer.findImage("setting_on"));
                        }
                    }
                }
            }
        });

        appendChild(new GameObject() {
            SpriteRenderer spriteRenderer2;

            @Override
            public void start() {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Game.instance);
                boolean isChecked = pref.getBoolean("setting2", true);

                spriteRenderer2 = new SpriteRenderer();
                attachComponent(spriteRenderer2);
                if (isChecked)
                    spriteRenderer2.bindingImage(GLRenderer.findImage("setting_on"));
                else
                    spriteRenderer2.bindingImage(GLRenderer.findImage("setting_off"));
                spriteRenderer2.setZ_index(91);

                GUITransform transform = new GUITransform();
                attachComponent(transform);
                transform.scale.x = 0.7f;
                transform.scale.y = 0.7f;
                transform.position.x = 2.7f;
            }

            @Override
            public void update() {
                super.update();

                for (int i = 0; i < 5; i++) {
                    if (Input.getTouchState(i) == Input.TOUCH_STATE.DOWN) {
                        if (Math.abs(Input.getTouchWorldPos(i).x - transform.position.x) <= 200 / 100f
                                && Math.abs(Input.getTouchWorldPos(i).y - transform.position.y) <= 60 / 100f) { // 버튼을 클릭했을 경우
                            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Game.instance);
                            boolean isChecked = pref.getBoolean("setting2", true);

                            SharedPreferences.Editor editor = pref.edit();
                            editor.putBoolean("setting2", !isChecked);
                            editor.commit();

                            if (isChecked)
                                spriteRenderer2.bindingImage(GLRenderer.findImage("setting_off"));
                            else
                                spriteRenderer2.bindingImage(GLRenderer.findImage("setting_on"));
                        }
                    }
                }
            }
        });

        appendChild(new GameObject() {
            SpriteRenderer spriteRenderer3;

            @Override
            public void start() {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Game.instance);
                boolean isChecked = pref.getBoolean("setting3", true);

                spriteRenderer3 = new SpriteRenderer();
                attachComponent(spriteRenderer3);
                if (isChecked)
                    spriteRenderer3.bindingImage(GLRenderer.findImage("setting_on"));
                else
                    spriteRenderer3.bindingImage(GLRenderer.findImage("setting_off"));
                spriteRenderer3.setZ_index(91);

                GUITransform transform = new GUITransform();
                attachComponent(transform);
                transform.scale.x = 0.7f;
                transform.scale.y = 0.7f;
                transform.position.x = 2.7f;
                transform.position.y = -1.2f;
            }

            @Override
            public void update() {
                super.update();

                for (int i = 0; i < 5; i++) {
                    if (Input.getTouchState(i) == Input.TOUCH_STATE.DOWN) {
                        if (Math.abs(Input.getTouchWorldPos(i).x - transform.position.x) <= 200 / 100f
                                && Math.abs(Input.getTouchWorldPos(i).y - transform.position.y) <= 60 / 100f) { // 버튼을 클릭했을 경우
                            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Game.instance);
                            boolean isChecked = pref.getBoolean("setting3", true);

                            SharedPreferences.Editor editor = pref.edit();
                            editor.putBoolean("setting3", !isChecked);
                            editor.commit();

                            if (isChecked)
                                spriteRenderer3.bindingImage(GLRenderer.findImage("setting_off"));
                            else
                                spriteRenderer3.bindingImage(GLRenderer.findImage("setting_on"));
                        }
                    }
                }
            }
        });
    }

    @Override
    public void update() {
        super.update();

        if (Input.backkeyDown) {
            destroy();
        }
    }
}
