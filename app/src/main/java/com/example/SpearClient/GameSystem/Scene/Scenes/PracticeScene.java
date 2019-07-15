package com.example.SpearClient.GameSystem.Scene.Scenes;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameIO.MediaPlayers.MediaPlayerHelper;
import com.example.SpearClient.GameIO.MediaPlayers.MediaPlayerHolder;
import com.example.SpearClient.GameIO.SoundPlayer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Background;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Cloud;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Blood;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Enemy.Enemy;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Enemy.EnemyHP;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Enemy.EnemyPointer;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player.MyHP;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player.Player;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player.PlayerPointer;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Start.BlackPanel;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Start.NicknameEnemy;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Start.NicknamePlayer;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Start.RankEnemy;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Start.RankPlayer;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Start.VS;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.UI.MoveLeft;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.UI.MoveRight;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.UI.Skill1;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.UI.Skill2;
import com.example.SpearClient.GameSystem.Other.GameManager;
import com.example.SpearClient.GameSystem.Scene.Scene;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Engine;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;
import com.example.SpearClient.Types.Vector;

public class PracticeScene extends Scene {
    Cloud cloud;
    Player player;

    MoveLeft moveLeft;
    MoveRight moveRight;

    Skill1 skill1;
    Skill2 skill2;

    public GameManager gameManager = new GameManager();;

    private MediaPlayerHolder mph;

    @Override
    public void start() {
        GameManager.getInstance().state = GameManager.STATE.GAMING;
        Engine.enemyNickname = "";
        mph = SoundPlayer.playBackgroundSound(Game.instance, R.raw.ingame, true);

        player = new Player();
        player.isPractice = true;
        moveLeft = new MoveLeft();
        moveRight = new MoveRight();
        skill1 = new Skill1();
        skill2 = new Skill2();
        cloud = new Cloud();
        cloud.getTransform().position.y = 14;
        cloud.getTransform().scale.x *= 1.5f;
        cloud.getTransform().scale.y *= 1.5f;

        objs.add(player);
        objs.add(moveLeft);
        objs.add(moveRight);
        objs.add(skill1);
        objs.add(skill2);
        objs.add(cloud);
        objs.add(new Background());

        createBackgrounds();
    }

    private void createBackgrounds() {
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("background_tree"));
                spriteRenderer.setZ_index(-8);
                spriteRenderer.lengthX = 4;

                Transform transform = new Transform();
                attachComponent(transform);
                transform.anchor.y = 1;
                transform.scale.x = 1.8f;
                transform.scale.y = 1.8f;
                transform.position.y = -2f;
            }
        });
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("background_house"));
                spriteRenderer.setZ_index(-7);
                spriteRenderer.lengthX = 5;

                Transform transform = new Transform();
                attachComponent(transform);
                transform.anchor.y = 1;
                transform.scale.x = 1.6f;
                transform.scale.y = 1.6f;
                transform.position.y = -2f;
            }
        });
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("background_wall"));
                spriteRenderer.setZ_index(-6);
                spriteRenderer.lengthX = 6;

                Transform transform = new Transform();
                attachComponent(transform);
                transform.anchor.y = 1;
                transform.scale.x = 0.7f;
                transform.scale.y = 0.7f;
                transform.position.y = -2f;
            }
        });
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("background_tunnel"));
                spriteRenderer.setZ_index(-5);
                spriteRenderer.lengthX = 6;

                Transform transform = new Transform();
                attachComponent(transform);
                transform.anchor.y = 1;
                transform.scale.x = 0.7f;
                transform.scale.y = 0.7f;
                transform.position.y = -2f;
            }
        });
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("background_people"));
                spriteRenderer.setZ_index(-4);
                spriteRenderer.lengthX = 6;

                Transform transform = new Transform();
                attachComponent(transform);
                transform.anchor.y = 1;
                transform.scale.x = 0.7f;
                transform.scale.y = 0.7f;
                transform.position.y = -2f;
            }
        });
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("background_front_wall"));
                spriteRenderer.setZ_index(-3);
                spriteRenderer.lengthX = 6;

                Transform transform = new Transform();
                attachComponent(transform);
                transform.anchor.y = 1;
                transform.scale.x = 0.7f;
                transform.scale.y = 0.7f;
                transform.position.y = -2f;
            }
        });
    }

    @Override
    public void update() {
        super.update();
        camUpdate();
        gameManager.update();

        if (Input.backkeyDown) {
            Game.engine.changeScene(new MainScene());
        }

    }

    private void camUpdate() {
        if (player != null) {
            float distance = Math.abs(player.getTransform().position.x);

            if (distance >= 28) {
                if (player.getTransform().position.x > 0) {
                    camera.setPosition(new Vector(player.getTransform().position.x - 14, (float) GLView.nowHeight - 6));
                } else {
                    camera.setPosition(new Vector(player.getTransform().position.x + 14, (float) GLView.nowHeight - 6));
                }

                distance = 28;
            } else {
                camera.setPosition(new Vector(player.getTransform().position.x / 2f, (float) GLView.nowHeight - 6));
            }

            float zoom = Math.min((float) Math.sqrt(GLView.defaultWidth / distance * 2) - 0.3f, 0.7f);

            camera.setZoomX(zoom);
            camera.setZoomY(zoom);
        }
    }

    @Override
    public void finish() {
        super.finish();

        MediaPlayerHelper.getInstance().delMedia(mph);
    }
}
