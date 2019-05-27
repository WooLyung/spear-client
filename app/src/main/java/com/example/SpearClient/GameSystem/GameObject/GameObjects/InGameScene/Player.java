package com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene;

import android.util.Log;

import com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimationComponent;
import com.example.SpearClient.GameSystem.Component.Components.PlayerMoveComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;

public class Player extends GameObject {
    SpriteRenderer spriteRenderer;
    AnimationComponent animationComponent;
    PlayerMoveComponent playerMoveComponent;

    GameObject knight, horse;

    @Override
    public void start() {
        setName("player");

        knight = new GameObject() {
            @Override
            public void start() {
                setName("knight");

                spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("knight_purple_ingame"));
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

        transform = new Transform();
        attachComponent(transform);
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
    }

    @Override
    public void update() {
        super.update();

        if (animationComponent.getNowAnim() == -1) {
            animationComponent.play(2);
        }
    }

    private void createHorse() {
        horse = new GameObject() {
            @Override
            public void start() {
                setName("horse_body");

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
