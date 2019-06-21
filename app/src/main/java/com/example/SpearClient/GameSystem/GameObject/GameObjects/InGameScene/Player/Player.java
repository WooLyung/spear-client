package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player;

import android.util.Log;

import com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimSupportClasses.Animation;
import com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimationComponent;
import com.example.SpearClient.GameSystem.Component.Components.PlayerMoveComponent;
import com.example.SpearClient.GameSystem.Component.Components.PlayerStateComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.AnimationRenderer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.DirtParticle;
import com.example.SpearClient.GameSystem.Other.AnimationManager;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;
import com.example.SpearClient.SocketIO.SocketIOBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.emitter.Emitter;

public class Player extends GameObject {
    float emitTime1 = 0;
    float emitTime2 = 0;

    SpriteRenderer spriteRenderer;
    AnimationComponent animationComponent;
    PlayerMoveComponent playerMoveComponent;
    PlayerStateComponent playerStateComponent;

    GameObject knight, horse;
    GameObject horse_head, horse_neck, horse_body,
                horse_leg_left_front_top, horse_leg_left_front_bottom,
                horse_leg_left_back_top, horse_leg_left_back_bottom,
                horse_leg_right_front_top, horse_leg_right_front_bottom,
                horse_leg_right_back_top, horse_leg_right_back_bottom;

    @Override
    public void start() {
        setName("player");

        knight = new GameObject() {
            public AnimationRenderer animationRenderer;

            @Override
            public void start() {
                setName("knight");

                animationRenderer = new AnimationRenderer();
                attachComponent(animationRenderer);
                animationRenderer.bindingImage(AnimationManager.playerAnims.get(0).get(0));
                animationRenderer.setLoop(false);
                animationRenderer.setZ_index(19);

                transform = new Transform();
                attachComponent(transform);
                transform.position.y = -0.3f;
                transform.anchor.x = 0.61f;
            }
        };
        createHorse();

        appendChild(knight);
        appendChild(horse);

        spriteRenderer = new SpriteRenderer();
        attachComponent(spriteRenderer);
        spriteRenderer.bindingImage(GLRenderer.findImage("empty"));

        transform = new Transform();
        attachComponent(transform);
        transform.position.x = -9f;
        transform.position.y = -0.1f;
        transform.scale.x = 1000/1470f;
        transform.scale.y = 1000/1470f;

        animationComponent = new AnimationComponent();
        attachComponent(animationComponent);
        animationComponent.addAnimation(Game.instance.getResources().getString(R.string.walk));
        animationComponent.addAnimation(Game.instance.getResources().getString(R.string.run));
        animationComponent.addAnimation(Game.instance.getResources().getString(R.string.idle));

        playerMoveComponent = new PlayerMoveComponent();
        attachComponent(playerMoveComponent);

        playerStateComponent = new PlayerStateComponent();
        attachComponent(playerStateComponent);
    }

    @Override
    public void update() {
        super.update();

        emitTime1 += Game.getDeltaTime();
        emitTime2 += Game.getDeltaTime();

        if (transform.position.x > 40)
            transform.position.x = 40;
        else if (transform.position.x < -40)
            transform.position.x = -40;

        if (emitTime2 >= 0.01f) {
            emitTime2 = 0;

            try {
                SocketIOBuilder.getInstance().playerFastUpdate(new JSONObject("{" +
                        "\"player_action\":" + playerStateComponent.getActionCode() + ",\n" +
                        "\"player_action_time\":" + playerStateComponent.time + ",\n" +
                        "\"player_pos\":{\n" +
                        "\t\"x\":" + transform.position.x + ",\n" +
                        "\t\"y\":" + transform.position.y + "\n" +
                        "}}"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (emitTime1 >= 0.05f) {
            emitTime1 = 0;

            try {
                AnimationRenderer knightAnimationRenderer = (AnimationRenderer)knight.getComponent("animationRenderer");

                SocketIOBuilder.getInstance().playerUpdate(new JSONObject("\n" +
                        "\n" +
                        "{\n" +
                        "\"player_image\":" + knightAnimationRenderer.getImage()[knightAnimationRenderer.getNowFrame()] + ",\n" +
                        "\"player_direction\":" + spriteRenderer.getIsFlip() + ",\n" +
                        "\"object\":{\n" +
                        "\"horse_head\":{\n" +
                        "\"x\":" + horse_head.getTransform().position.x + ",\n" +
                        "\"y\":" + horse_head.getTransform().position.y + ",\n" +
                        "\"angle\":" + horse_head.getTransform().angle + "\n" +
                        "}, \"horse_neck\":{\n" +
                        "\"x\":" + horse_neck.getTransform().position.x + ",\n" +
                        "\"y\":" + horse_neck.getTransform().position.y + ",\n" +
                        "\"angle\":" + horse_neck.getTransform().angle + "\n" +
                        "}, \"horse_body\":{\n" +
                        "\"x\":" + horse_body.getTransform().position.x + ",\n" +
                        "\"y\":" + horse_body.getTransform().position.y + ",\n" +
                        "\"angle\":" + horse_body.getTransform().angle + "\n" +
                        "}, \"horse_leg_right_front_top\":{\n" +
                        "\"x\":" + horse_leg_right_front_top.getTransform().position.x + ",\n" +
                        "\"y\":" + horse_leg_right_front_top.getTransform().position.y + ",\n" +
                        "\"angle\":" + horse_leg_right_front_top.getTransform().angle + "\n" +
                        "}, \"horse_leg_right_front_bottom\":{\n" +
                        "\"x\":" + horse_leg_right_front_bottom.getTransform().position.x + ",\n" +
                        "\"y\":" + horse_leg_right_front_bottom.getTransform().position.y + ",\n" +
                        "\"angle\":" + horse_leg_right_front_bottom.getTransform().angle + "\n" +
                        "}, \"horse_leg_right_back_top\":{\n" +
                        "\"x\":" + horse_leg_right_back_top.getTransform().position.x + ",\n" +
                        "\"y\":" + horse_leg_right_back_top.getTransform().position.y + ",\n" +
                        "\"angle\":" + horse_leg_right_back_top.getTransform().angle + "\n" +
                        "}, \"horse_leg_right_back_bottom\":{\n" +
                        "\"x\":" + horse_leg_right_back_bottom.getTransform().position.x + ",\n" +
                        "\"y\":" + horse_leg_right_back_bottom.getTransform().position.y + ",\n" +
                        "\"angle\":" + horse_leg_right_back_bottom.getTransform().angle + "\n" +
                        "}, \"horse_leg_left_front_top\":{\n" +
                        "\"x\":" + horse_leg_left_front_top.getTransform().position.x + ",\n" +
                        "\"y\":" + horse_leg_left_front_top.getTransform().position.y + ",\n" +
                        "\"angle\":" + horse_leg_left_front_top.getTransform().angle + "\n" +
                        "}, \"horse_leg_left_front_bottom\":{\n" +
                        "\"x\":" + horse_leg_left_front_bottom.getTransform().position.x + ",\n" +
                        "\"y\":" + horse_leg_left_front_bottom.getTransform().position.y + ",\n" +
                        "\"angle\":" + horse_leg_left_front_bottom.getTransform().angle + "\n" +
                        "}, \"horse_leg_left_back_top\":{\n" +
                        "\"x\":" + horse_leg_left_back_top.getTransform().position.x + ",\n" +
                        "\"y\":" + horse_leg_left_back_top.getTransform().position.y + ",\n" +
                        "\"angle\":" + horse_leg_left_back_top.getTransform().angle + "\n" +
                        "}, \"horse_leg_left_back_bottom\":{\n" +
                        "\"x\":" + horse_leg_left_back_bottom.getTransform().position.x + ",\n" +
                        "\"y\":" + horse_leg_left_back_bottom.getTransform().position.y + ",\n" +
                        "\"angle\":" + horse_leg_left_back_bottom.getTransform().angle + "\n" +
                        "}, \"knight\":{\n" +
                        "\"x\":" + knight.getTransform().position.x + ",\n" +
                        "\"y\":" + knight.getTransform().position.y + ",\n" +
                        "\"angle\":" + knight.getTransform().angle + "\n" +
                        "}\n" +
                        "}\n" +
                        "}"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createHorse() {
        horse = new GameObject() {
            @Override
            public void start() {
                horse_body = this;
                setName("horse_body");

                spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("horse_body"));
                spriteRenderer.setZ_index(16);

                transform = new Transform();
                attachComponent(transform);
                transform.position.y = -0.9f;
                transform.position.x = 0.3f;

                appendChild(new GameObject() {
                    @Override
                    public void start() {
                        horse_neck = this;
                        setName("horse_neck");

                        spriteRenderer = new SpriteRenderer();
                        attachComponent(spriteRenderer);
                        spriteRenderer.bindingImage(GLRenderer.findImage("horse_neck"));
                        spriteRenderer.setZ_index(17);

                        transform = new Transform();
                        attachComponent(transform);
                        transform.anchor.x = 0.7f;
                        transform.anchor.y = 0.9f;
                        transform.position.x = 1f;
                        transform.position.y = 0.5f;

                        appendChild(new GameObject() {
                            @Override
                            public void start() {
                                horse_head = this;
                                setName("horse_head");

                                spriteRenderer = new SpriteRenderer();
                                attachComponent(spriteRenderer);
                                spriteRenderer.bindingImage(GLRenderer.findImage("horse_head"));
                                spriteRenderer.setZ_index(18);

                                transform = new Transform();
                                attachComponent(transform);
                                transform.anchor.x = 0.8f;
                                transform.anchor.y = 0.5f;
                                transform.position.x = 0.55f;
                                transform.position.y = 1.65f;
                            }
                        });
                    }
                });

                appendChild(new GameObject() {
                    @Override
                    public void start() {
                        horse_leg_right_front_top = this;
                        setName("horse_leg_right_front_top");

                        spriteRenderer = new SpriteRenderer();
                        attachComponent(spriteRenderer);
                        spriteRenderer.bindingImage(GLRenderer.findImage("horse_leg_right"));
                        spriteRenderer.setZ_index(16);

                        transform = new Transform();
                        attachComponent(transform);
                        transform.anchor.x = 0.5f;
                        transform.anchor.y = 0.2f;
                        transform.position.x = 1.45f;
                        transform.position.y = -0.75f;

                        appendChild(new GameObject() {
                            @Override
                            public void start() {
                                horse_leg_right_front_bottom = this;
                                setName("horse_leg_right_front_bottom");

                                spriteRenderer = new SpriteRenderer();
                                attachComponent(spriteRenderer);
                                spriteRenderer.bindingImage(GLRenderer.findImage("horse_leg_right"));
                                spriteRenderer.setZ_index(14);

                                transform = new Transform();
                                attachComponent(transform);
                                transform.anchor.x = 0.5f;
                                transform.anchor.y = 0.2f;
                                transform.position.y = -0.6f;
                            }
                        });
                    }
                });
                appendChild(new GameObject() {
                    @Override
                    public void start() {
                        horse_leg_left_front_top = this;
                        setName("horse_leg_left_front_top");

                        spriteRenderer = new SpriteRenderer();
                        attachComponent(spriteRenderer);
                        spriteRenderer.bindingImage(GLRenderer.findImage("horse_leg_left"));
                        spriteRenderer.setZ_index(13);

                        transform = new Transform();
                        attachComponent(transform);
                        transform.anchor.x = 0.5f;
                        transform.anchor.y = 0.2f;
                        transform.position.x = 1.25f;
                        transform.position.y = -0.75f;

                        appendChild(new GameObject() {
                            @Override
                            public void start() {
                                horse_leg_left_front_bottom = this;
                                setName("horse_leg_left_front_bottom");

                                spriteRenderer = new SpriteRenderer();
                                attachComponent(spriteRenderer);
                                spriteRenderer.bindingImage(GLRenderer.findImage("horse_leg_left"));
                                spriteRenderer.setZ_index(12);

                                transform = new Transform();
                                attachComponent(transform);
                                transform.anchor.x = 0.5f;
                                transform.anchor.y = 0.2f;
                                transform.position.y = -0.6f;
                            }
                        });
                    }
                });
                appendChild(new GameObject() {
                    @Override
                    public void start() {
                        horse_leg_right_back_top = this;
                        setName("horse_leg_right_back_top");

                        spriteRenderer = new SpriteRenderer();
                        attachComponent(spriteRenderer);
                        spriteRenderer.bindingImage(GLRenderer.findImage("horse_leg_right"));
                        spriteRenderer.setZ_index(15);

                        transform = new Transform();
                        attachComponent(transform);
                        transform.anchor.x = 0.5f;
                        transform.anchor.y = 0.2f;
                        transform.position.x = 1.45f - 2.7f;
                        transform.position.y = -0.75f;

                        appendChild(new GameObject() {
                            @Override
                            public void start() {
                                horse_leg_right_back_bottom = this;
                                setName("horse_leg_right_back_bottom");

                                spriteRenderer = new SpriteRenderer();
                                attachComponent(spriteRenderer);
                                spriteRenderer.bindingImage(GLRenderer.findImage("horse_leg_right"));
                                spriteRenderer.setZ_index(4);

                                transform = new Transform();
                                attachComponent(transform);
                                transform.anchor.x = 0.5f;
                                transform.anchor.y = 0.2f;
                                transform.position.y = -0.6f;
                            }
                        });
                    }
                });
                appendChild(new GameObject() {
                    @Override
                    public void start() {
                        horse_leg_left_back_top = this;
                        setName("horse_leg_left_back_top");

                        spriteRenderer = new SpriteRenderer();
                        attachComponent(spriteRenderer);
                        spriteRenderer.bindingImage(GLRenderer.findImage("horse_leg_left"));
                        spriteRenderer.setZ_index(3);

                        transform = new Transform();
                        attachComponent(transform);
                        transform.anchor.x = 0.5f;
                        transform.anchor.y = 0.2f;
                        transform.position.x = 1.25f - 2.7f;
                        transform.position.y = -0.75f;

                        appendChild(new GameObject() {
                            @Override
                            public void start() {
                                horse_leg_left_back_bottom = this;
                                setName("horse_leg_left_back_bottom");

                                spriteRenderer = new SpriteRenderer();
                                attachComponent(spriteRenderer);
                                spriteRenderer.bindingImage(GLRenderer.findImage("horse_leg_left"));
                                spriteRenderer.setZ_index(2);

                                transform = new Transform();
                                attachComponent(transform);
                                transform.anchor.x = 0.5f;
                                transform.anchor.y = 0.2f;
                                transform.position.y = -0.6f;
                            }
                        });
                    }
                });
            }
        };
    }

    public void summonDirtParticle() {
        DirtParticle dirtParticle;


        /*for (int i = 0; i < 1; i++) {
            dirtParticle = new DirtParticle();
            dirtParticle.transform.position.x = transform.position.x;
            dirtParticle.transform.position.y = transform.position.y;

            Game.engine.nowScene.objs.add(dirtParticle);
        }*/
    }
}
