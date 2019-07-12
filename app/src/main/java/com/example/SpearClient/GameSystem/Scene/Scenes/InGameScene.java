package com.example.SpearClient.GameSystem.Scene.Scenes;

import android.util.Log;

import com.example.SpearClient.GameIO.MediaPlayers.MediaPlayerHelper;
import com.example.SpearClient.GameIO.MediaPlayers.MediaPlayerHolder;
import com.example.SpearClient.GameIO.SoundPlayer;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.Component.Components.TransformComponent.Transforms.Transform;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Background;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Cloud;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.ResultBoard.ResultBoard;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Start.BlackPanel;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Blood;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Enemy.Enemy;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Enemy.EnemyHP;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Enemy.EnemyPointer;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player.PlayerPointer;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Start.NicknameEnemy;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Start.NicknamePlayer;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Start.RankEnemy;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Start.RankPlayer;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.UI.MoveLeft;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.UI.MoveRight;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player.MyHP;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player.Player;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.UI.Skill1;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.UI.Skill2;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Start.VS;
import com.example.SpearClient.GameSystem.Other.GameManager;
import com.example.SpearClient.GameSystem.Scene.Scene;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Engine;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;
import com.example.SpearClient.Types.Vector;

public class InGameScene extends Scene {
    Cloud cloud;
    Player player;
    Enemy enemy;

    MyHP myHP;
    EnemyHP enemyHP;

    MoveLeft moveLeft;
    MoveRight moveRight;

    Skill1 skill1;
    Skill2 skill2;

    EnemyPointer enemyPointer;
    PlayerPointer playerPointer;
    VS vs;
    BlackPanel blackPanel;
    NicknamePlayer nicknamePlayer;
    NicknameEnemy nicknameEnemy;
    RankPlayer rankPlayer;
    RankEnemy rankEnemy;

    Blood[] bloods = { new Blood(), new Blood(), new Blood(), new Blood()};

    public GameManager gameManager = new GameManager();
    public float time = 0;
    public float bloodTime = 0;
    private int bloodCode;

    private MediaPlayerHolder mph;

    @Override
    public void start() {
        GameManager.getInstance().state = GameManager.STATE.WAITING1;
        Engine.enemyNickname = "";
        mph = SoundPlayer.playBackgroundSound(Game.instance, R.raw.ingame, true);

        player = new Player();
        enemy = new Enemy();
        moveLeft = new MoveLeft();
        moveRight = new MoveRight();
        myHP = new MyHP();
        enemyHP = new EnemyHP();
        skill1 = new Skill1();
        skill2 = new Skill2();
        cloud = new Cloud();
        enemyPointer = new EnemyPointer();
        playerPointer = new PlayerPointer();
        vs = new VS();
        blackPanel = new BlackPanel();
        nicknamePlayer = new NicknamePlayer();
        nicknameEnemy = new NicknameEnemy();
        rankEnemy = new RankEnemy();
        rankPlayer = new RankPlayer();
        cloud.getTransform().position.y = 14;
        cloud.getTransform().scale.x *= 1.5f;
        cloud.getTransform().scale.y *= 1.5f;

        objs.add(player);
        objs.add(enemy);
        objs.add(moveLeft);
        objs.add(moveRight);
        objs.add(myHP);
        objs.add(enemyHP);
        objs.add(skill1);
        objs.add(skill2);
        objs.add(cloud);
        objs.add(enemyPointer);
        objs.add(playerPointer);
        objs.add(vs);
        objs.add(blackPanel);
        objs.add(nicknamePlayer);
        objs.add(nicknameEnemy);
        objs.add(rankEnemy);
        objs.add(rankPlayer);
        objs.add(new Background());

        bloodSetting();
        createBackgrounds();
    }

    private void createBackgrounds() {
        objs.add(new GameObject() {
            @Override
            public void start() {
                SpriteRenderer spriteRenderer = new SpriteRenderer();
                attachComponent(spriteRenderer);
                spriteRenderer.bindingImage(GLRenderer.findImage("background_wall"));
                spriteRenderer.setZ_index(-8);
                spriteRenderer.lengthX = 5;

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
                spriteRenderer.setZ_index(-7);
                spriteRenderer.lengthX = 5;

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
                spriteRenderer.setZ_index(-6);
                spriteRenderer.lengthX = 5;

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
                spriteRenderer.setZ_index(-5);
                spriteRenderer.lengthX = 5;

                Transform transform = new Transform();
                attachComponent(transform);
                transform.anchor.y = 1;
                transform.scale.x = 0.7f;
                transform.scale.y = 0.7f;
                transform.position.y = -2f;
            }
        });
    }

    private void bloodSetting() {
        objs.add(bloods[0]);
        objs.add(bloods[1]);
        objs.add(bloods[2]);
        objs.add(bloods[3]);

        bloods[0].getTransform().position.x = (float)GLView.defaultWidth;
        bloods[0].getTransform().position.y = (float)GLView.defaultHeight;
        ((SpriteRenderer)bloods[0].getRenderer()).setIsFlip(true);

        bloods[1].getTransform().position.x = -(float)GLView.defaultWidth;
        bloods[1].getTransform().position.y = (float)GLView.defaultHeight;

        bloods[2].getTransform().position.x = (float)GLView.defaultWidth;
        bloods[2].getTransform().position.y = -(float)GLView.defaultHeight;
        bloods[2].getTransform().angle = 180;

        bloods[3].getTransform().position.x = -(float)GLView.defaultWidth;
        bloods[3].getTransform().position.y = -(float)GLView.defaultHeight;
        bloods[3].getTransform().angle = 180;
        ((SpriteRenderer)bloods[3].getRenderer()).setIsFlip(true);

        bloodCode = GLRenderer.findImage("blood");

        float[] color = {
                1, 1, 1, 0,
                1, 1, 1, 0,
                1, 1, 1, 0,
                1, 1, 1, 0
        };
        GLRenderer.imageDatas.get(bloodCode).setColors(color);
    }

    @Override
    public void update() {
        super.update();
        gameManager.update();

        time += Game.getDeltaTime();

        camUpdate();

        bloodTime -= Game.getDeltaTime();
        if (bloodTime < 0) {
            bloodTime = 0;
        }

        float alpha = bloodTime;
        if (gameManager.playerHealth < 25f) {
            float hurt = ((float) Math.cos(3 * time) + 2) / 4;
            alpha = Math.max(alpha, hurt);
        } else if (gameManager.playerHealth < 50f) {
            float hurt = ((float) Math.cos(3 * time) + 1) / 5;
            alpha = Math.max(alpha, hurt);
        }

        float[] color = {
                1, 1, 1, alpha,
                1, 1, 1, alpha,
                1, 1, 1, alpha,
                1, 1, 1, alpha
        };
        GLRenderer.imageDatas.get(bloodCode).setColors(color);
    }

    private void camUpdate() {
        if (player != null && enemy != null) {
            float distance = Math.abs(player.getTransform().position.x - enemy.getTransform().position.x);

            if (distance >= 28) {
                if (player.getTransform().position.x > enemy.getTransform().position.x) {
                    camera.setPosition(new Vector(player.getTransform().position.x - 14, (float) GLView.nowHeight - 6));
                } else {
                    camera.setPosition(new Vector(player.getTransform().position.x + 14, (float) GLView.nowHeight - 6));
                }

                distance = 28;
            } else {
                camera.setPosition(new Vector((player.getTransform().position.x + enemy.getTransform().position.x) / 2f, (float) GLView.nowHeight - 6));
            }

            if (GameManager.getInstance().state == GameManager.STATE.GAMING) {
                float zoom = Math.min((float) Math.sqrt(GLView.defaultWidth / distance * 2) - 0.3f, 0.7f);

                camera.setZoomX(zoom);
                camera.setZoomY(zoom);
            } else if (GameManager.getInstance().state == GameManager.STATE.WAITING1) {
                if (vs != null) vs.updateImage(time, true);
                float zoom = Math.min((float) Math.sqrt(GLView.defaultWidth / distance * 2) - 0.3f, 0.7f);

                camera.setZoomX(zoom * 0.6f);
                camera.setZoomY(zoom * 0.6f);

                if (time >= 3) {
                    time = 0;
                    GameManager.getInstance().state = GameManager.STATE.WAITING2;
                }
            } else if (GameManager.getInstance().state == GameManager.STATE.WAITING2) {
                vs.updateImage(time, false);
                blackPanel.updateImage(time);
                float zoom = Math.min((float) Math.sqrt(GLView.defaultWidth / distance * 2) - 0.3f, 0.7f);

                camera.setZoomX(zoom * (0.6f + time * 0.2f));
                camera.setZoomY(zoom * (0.6f + time * 0.2f));

                if (time >= 2) {
                    time = 0;
                    GameManager.getInstance().state = GameManager.STATE.GAMING;
                }
            }
        }
    }

    @Override
    public void finish() {
        super.finish();

        MediaPlayerHelper.getInstance().delMedia(mph);
    }
}
