package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene;

import android.util.Log;

import com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimationComponent;
import com.example.SpearClient.GameSystem.Component.Components.EnemyStateComponent;
import com.example.SpearClient.GameSystem.Component.Components.PlayerMoveComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.Main.Engine;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;
import com.example.SpearClient.SocketIO.SocketIOBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.socket.emitter.Emitter;

public class Enemy extends GameObject {
    SpriteRenderer spriteRenderer;
    EnemyStateComponent enemyStateComponent;

    GameObject knight, horse;
    GameObject horse_body, horse_head, horse_neck,
            horse_leg_left_front_top, horse_leg_left_front_bottom,
            horse_leg_left_back_top, horse_leg_left_back_bottom,
            horse_leg_right_front_top, horse_leg_right_front_bottom,
            horse_leg_right_back_top, horse_leg_right_back_bottom;

    @Override
    public void start() {
        setName("enemy");

        knight = new GameObject() {
            @Override
            public void start() {
                setName("knight");

                spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("knight_purple_default"));
                spriteRenderer.setZ_index(9);

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
        spriteRenderer.setIsFlip(true);

        transform = new Transform();
        attachComponent(transform);
        transform.position.x = 7f;
        transform.position.y = -0.1f;
        transform.scale.x = 1000/1470f;
        transform.scale.y = 1000/1470f;

        enemyStateComponent = new EnemyStateComponent();
        attachComponent(enemyStateComponent);

        SocketIOBuilder.getInstance().update_player(new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    Log.i("update", SocketIOBuilder.id + " : " + args[0].toString());
                    JSONArray array = new JSONArray(args[0].toString());
                    JSONObject jsonObject;

                    if (array.getJSONObject(0).getString("id") == SocketIOBuilder.id) {
                        jsonObject = array.getJSONObject(1);
                    }
                    else {
                        jsonObject = array.getJSONObject(0);
                    }

                    SpriteRenderer enemySprite = ((SpriteRenderer)knight.getComponent("spriteRenderer"));
                    enemySprite.bindingImage(jsonObject.getInt("player_image"));
                    enemySprite.setIsFlip(!jsonObject.getBoolean("player_direction"));
                    enemyStateComponent.setAction(jsonObject.getInt("player_action"));
                    enemyStateComponent.time = jsonObject.getLong("player_action_time");

                    JSONObject player_pos = jsonObject.getJSONObject("player_pos");
                    JSONObject objects = jsonObject.getJSONObject("object");
                    JSONObject horse_head_t = objects.getJSONObject("horse_head");
                    JSONObject horse_neck_t = objects.getJSONObject("horse_neck");
                    JSONObject horse_body_t = objects.getJSONObject("horse_body");
                    JSONObject horse_leg_right_front_top_t = objects.getJSONObject("horse_leg_right_front_top");
                    JSONObject horse_leg_right_front_bottom_t = objects.getJSONObject("horse_leg_right_front_bottom");
                    JSONObject horse_leg_right_back_top_t = objects.getJSONObject("horse_leg_right_back_top");
                    JSONObject horse_leg_right_back_bottom_t = objects.getJSONObject("horse_leg_right_back_bottom");
                    JSONObject horse_leg_left_front_top_t = objects.getJSONObject("horse_leg_left_front_top");
                    JSONObject horse_leg_left_front_bottom_t = objects.getJSONObject("horse_leg_left_front_bottom");
                    JSONObject horse_leg_left_back_top_t = objects.getJSONObject("horse_leg_left_back_top");
                    JSONObject horse_leg_left_back_bottom_t = objects.getJSONObject("horse_leg_left_back_bottom");

                    transform.position.x = player_pos.getLong("x");
                    transform.position.y = player_pos.getLong("y");

                    horse_head.getTransform().position.x = horse_head_t.getLong("x");
                    horse_head.getTransform().position.y = horse_head_t.getLong("y");
                    horse_head.getTransform().angle = horse_head_t.getLong("angle");
                    horse_neck.getTransform().position.x = horse_neck_t.getLong("x");
                    horse_neck.getTransform().position.y = horse_neck_t.getLong("y");
                    horse_neck.getTransform().angle = horse_neck_t.getLong("angle");
                    horse_body.getTransform().position.x = horse_body_t.getLong("x");
                    horse_body.getTransform().position.y = horse_body_t.getLong("y");
                    horse_body.getTransform().angle = horse_body_t.getLong("angle");

                    horse_leg_right_front_top.getTransform().position.x = horse_leg_right_front_top_t.getLong("x");
                    horse_leg_right_front_top.getTransform().position.y = horse_leg_right_front_top_t.getLong("y");
                    horse_leg_right_front_top.getTransform().angle = horse_leg_right_front_top_t.getLong("angle");
                    horse_leg_right_front_bottom.getTransform().position.x = horse_leg_right_front_bottom_t.getLong("x");
                    horse_leg_right_front_bottom.getTransform().position.y = horse_leg_right_front_bottom_t.getLong("y");
                    horse_leg_right_front_bottom.getTransform().angle = horse_leg_right_front_bottom_t.getLong("angle");
                    horse_leg_right_back_top.getTransform().position.x = horse_leg_right_back_top_t.getLong("x");
                    horse_leg_right_back_top.getTransform().position.y = horse_leg_right_back_top_t.getLong("y");
                    horse_leg_right_back_top.getTransform().angle = horse_leg_right_back_top_t.getLong("angle");
                    horse_leg_right_back_bottom.getTransform().position.x = horse_leg_right_back_bottom_t.getLong("x");
                    horse_leg_right_back_bottom.getTransform().position.y = horse_leg_right_back_bottom_t.getLong("y");
                    horse_leg_right_back_bottom.getTransform().angle = horse_leg_right_back_bottom_t.getLong("angle");

                    horse_leg_left_front_top.getTransform().position.x = horse_leg_left_front_top_t.getLong("x");
                    horse_leg_left_front_top.getTransform().position.y = horse_leg_left_front_top_t.getLong("y");
                    horse_leg_left_front_top.getTransform().angle = horse_leg_left_front_top_t.getLong("angle");
                    horse_leg_left_front_bottom.getTransform().position.x = horse_leg_left_front_bottom_t.getLong("x");
                    horse_leg_left_front_bottom.getTransform().position.y = horse_leg_left_front_bottom_t.getLong("y");
                    horse_leg_left_front_bottom.getTransform().angle = horse_leg_left_front_bottom_t.getLong("angle");
                    horse_leg_left_back_top.getTransform().position.x = horse_leg_left_back_top_t.getLong("x");
                    horse_leg_left_back_top.getTransform().position.y = horse_leg_left_back_top_t.getLong("y");
                    horse_leg_left_back_top.getTransform().angle = horse_leg_left_back_top_t.getLong("angle");
                    horse_leg_left_back_bottom.getTransform().position.x = horse_leg_left_back_bottom_t.getLong("x");
                    horse_leg_left_back_bottom.getTransform().position.y = horse_leg_left_back_bottom_t.getLong("y");
                    horse_leg_left_back_bottom.getTransform().angle = horse_leg_left_back_bottom_t.getLong("angle");
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void update() {
        super.update();
    }

    private void createHorse() {
        horse = new GameObject() {
            @Override
            public void start() {
                setName("horse_body");
                horse_body = this;

                spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("horse_body"));
                spriteRenderer.setZ_index(6);

                transform = new Transform();
                attachComponent(transform);
                transform.position.y = -0.9f;
                transform.position.x = 0.3f;

                appendChild(new GameObject() {
                    @Override
                    public void start() {
                        setName("horse_neck");
                        horse_neck = this;

                        spriteRenderer = new SpriteRenderer();
                        attachComponent(spriteRenderer);
                        spriteRenderer.bindingImage(GLRenderer.findImage("horse_neck"));
                        spriteRenderer.setZ_index(7);

                        transform = new Transform();
                        attachComponent(transform);
                        transform.anchor.x = 0.7f;
                        transform.anchor.y = 0.9f;
                        transform.position.x = 1f;
                        transform.position.y = 0.5f;

                        appendChild(new GameObject() {
                            @Override
                            public void start() {
                                setName("horse_head");
                                horse_head = this;

                                spriteRenderer = new SpriteRenderer();
                                attachComponent(spriteRenderer);
                                spriteRenderer.bindingImage(GLRenderer.findImage("horse_head"));
                                spriteRenderer.setZ_index(8);

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
                        setName("horse_leg_right_front_top");
                        horse_leg_right_front_top = this;

                        spriteRenderer = new SpriteRenderer();
                        attachComponent(spriteRenderer);
                        spriteRenderer.bindingImage(GLRenderer.findImage("horse_leg_right"));
                        spriteRenderer.setZ_index(5);

                        transform = new Transform();
                        attachComponent(transform);
                        transform.anchor.x = 0.5f;
                        transform.anchor.y = 0.2f;
                        transform.position.x = 1.45f;
                        transform.position.y = -0.75f;

                        appendChild(new GameObject() {
                            @Override
                            public void start() {
                                setName("horse_leg_right_front_bottom");
                                horse_leg_right_front_bottom = this;

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
                        setName("horse_leg_left_front_top");
                        horse_leg_left_front_top = this;

                        spriteRenderer = new SpriteRenderer();
                        attachComponent(spriteRenderer);
                        spriteRenderer.bindingImage(GLRenderer.findImage("horse_leg_left"));
                        spriteRenderer.setZ_index(3);

                        transform = new Transform();
                        attachComponent(transform);
                        transform.anchor.x = 0.5f;
                        transform.anchor.y = 0.2f;
                        transform.position.x = 1.25f;
                        transform.position.y = -0.75f;

                        appendChild(new GameObject() {
                            @Override
                            public void start() {
                                setName("horse_leg_left_front_bottom");
                                horse_leg_left_front_bottom = this;

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
                appendChild(new GameObject() {
                    @Override
                    public void start() {
                        setName("horse_leg_right_back_top");
                        horse_leg_right_back_top = this;

                        spriteRenderer = new SpriteRenderer();
                        attachComponent(spriteRenderer);
                        spriteRenderer.bindingImage(GLRenderer.findImage("horse_leg_right"));
                        spriteRenderer.setZ_index(5);

                        transform = new Transform();
                        attachComponent(transform);
                        transform.anchor.x = 0.5f;
                        transform.anchor.y = 0.2f;
                        transform.position.x = 1.45f - 2.7f;
                        transform.position.y = -0.75f;

                        appendChild(new GameObject() {
                            @Override
                            public void start() {
                                setName("horse_leg_right_back_bottom");
                                horse_leg_right_back_bottom = this;

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
                        setName("horse_leg_left_back_top");
                        horse_leg_left_back_top = this;

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
                                setName("horse_leg_left_back_bottom");
                                horse_leg_left_back_bottom = this;

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
}
