package com.example.SpearClient.GameSystem.Scene.Scenes;

import android.util.Log;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Background;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Cloud;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.BlackPanel;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Blood;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Enemy.Enemy;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Enemy.EnemyHP;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Enemy.EnemyPointer;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player.PlayerPointer;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.UI.MoveLeft;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.UI.MoveRight;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player.MyHP;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player.Player;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.UI.Skill1;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.UI.Skill2;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.UI.VS;
import com.example.SpearClient.GameSystem.Other.GameManager;
import com.example.SpearClient.GameSystem.Scene.Scene;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;
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

    Blood[] bloods = { new Blood(), new Blood(), new Blood(), new Blood()};

    public GameManager gameManager = new GameManager();
    public float time = 0;
    public float bloodTime = 0;
    private int bloodCode;

    @Override
    public void start() {
        GameManager.getInstance().state = GameManager.STATE.WAITING1;

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
        objs.add(new Background());

        bloodSetting();
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

        if (bloodTime > 0) {
            bloodTime -= Game.getDeltaTime();
            float[] color = {
                    1, 1, 1, bloodTime,
                    1, 1, 1, bloodTime,
                    1, 1, 1, bloodTime,
                    1, 1, 1, bloodTime
            };
            GLRenderer.imageDatas.get(bloodCode).setColors(color);

            if (bloodTime < 0) {
                bloodTime = 0;

                float[] color2 = {
                        1, 1, 1, 0,
                        1, 1, 1, 0,
                        1, 1, 1, 0,
                        1, 1, 1, 0
                };
                GLRenderer.imageDatas.get(bloodCode).setColors(color2);
            }
        }
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
                vs.updateImage(time, true);
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
    }
}
