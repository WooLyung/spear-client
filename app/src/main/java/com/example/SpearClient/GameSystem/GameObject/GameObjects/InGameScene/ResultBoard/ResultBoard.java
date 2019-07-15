package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.ResultBoard;

import android.util.Log;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.Component.Components.EffectComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.TextRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.GUITransform;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.Input_ID;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.Input_password;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.LoginBoard.LoginButton;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.LoginBoard.LoginTitle;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.LoginScene.LoginBoard.ToRegisterBoard;
import com.example.SpearClient.GameSystem.Other.GameManager;
import com.example.SpearClient.GameSystem.Scene.Scenes.MainScene;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;

public class ResultBoard extends GameObject {
    private SpriteRenderer spriteRenderer;
    private GUITransform transform;
    private EffectComponent effectComponent1, effectComponent2;
    private TextRenderer textRenderer;
    private boolean buttonAppeared = false;
    private float time = 0;
    private GameObject rank;

    RegameButton regameButton;
    HomeButton homeButton;

    @Override
    public void start() {
        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);

        if (GameManager.isWin)
            spriteRenderer.bindingImage(GLRenderer.findImage("win"));
        else
            spriteRenderer.bindingImage(GLRenderer.findImage("lose"));
        spriteRenderer.setZ_index(60);

        transform = new GUITransform();
        attachComponent(transform);
        transform.scale.x = 0.5f;
        transform.scale.y = 0.5f;

        effectComponent1 = new EffectComponent();
        attachComponent(effectComponent1);

        regameButton = new RegameButton();
        homeButton = new HomeButton();

        if (MainScene.selectedGame.equals("fast")) {
            appendChild(regameButton);
            appendChild(homeButton);
            buttonAppeared = true;
        }
        else {
            rank = new GameObject() {
                @Override
                public void start() {
                    SpriteRenderer spriteRenderer = new SpriteRenderer();
                    attachComponent(spriteRenderer);
                    spriteRenderer.setZ_index(60);

                    if (GameManager.isWin) {
                        GameManager.ratings[GameManager.me]++;
                    } else {
                        GameManager.ratings[GameManager.me]--;
                        if (GameManager.ratings[GameManager.me] <= 0)
                            GameManager.ratings[GameManager.me] = 1;
                    }

                    if (GameManager.ratings[GameManager.me] <= 5) {
                        spriteRenderer.bindingImage(GLRenderer.findImage("rank_bronze"));
                    } else if (GameManager.ratings[GameManager.me] <= 10) {
                        spriteRenderer.bindingImage(GLRenderer.findImage("rank_silver"));
                    } else {
                        spriteRenderer.bindingImage(GLRenderer.findImage("rank_gold"));
                    }

                    GUITransform transform = new GUITransform();
                    attachComponent(transform);
                    transform.scale.x = 1.4f;
                    transform.scale.y = 1.4f;

                    textRenderer = new TextRenderer();
                    attachComponent(textRenderer);
                    textRenderer.setText("");
                    textRenderer.setHorizontal(1);
                    textRenderer.setVertical(1);
                    Game.instance.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textRenderer.getTextView().setTextColor(Game.instance.getResources().getColor(R.color.white));
                            textRenderer.getTextView().setTextSize(60);
                        }
                    });

                    effectComponent2 = new EffectComponent();
                    attachComponent(effectComponent2);
                    effectComponent2.setColors(new float[]{
                            1, 1, 1, 0,
                            1, 1, 1, 0,
                            1, 1, 1, 0,
                            1, 1, 1, 0
                    });
                }
            };
        }

        appendChild(rank);
        appendChild(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("background_black"));
                spriteRenderer.setZ_index(55);

                GUITransform transform = new GUITransform();
                attachComponent(transform);

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
    }

    @Override
    public void update() {
        super.update();

        if (!buttonAppeared) {
            for (int i = 0; i < 5; i++) {
                if (Input.getTouchState(i) == Input.TOUCH_STATE.DOWN) {
                    buttonAppeared = true;

                    appendChild(regameButton);
                    appendChild(homeButton);
                }
            }
        }
        else if (MainScene.selectedGame.equals("rank")) {
            time += Game.getNoneDeltaTime();
            float alpha = ((time > 0.2f) ? 0.2f : time) * 5;

            effectComponent1.setColors(new float[]{
                    1, 1, 1, 1 - alpha,
                    1, 1, 1, 1 - alpha,
                    1, 1, 1, 1 - alpha,
                    1, 1, 1, 1 - alpha
            });
            effectComponent2.setColors(new float[]{
                    1, 1, 1, alpha,
                    1, 1, 1, alpha,
                    1, 1, 1, alpha,
                    1, 1, 1, alpha
            });

            if (alpha >= 1) {
                textRenderer.setText((5 - (GameManager.ratings[GameManager.me]- 1) % 5) + "");
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
