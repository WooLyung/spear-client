package com.example.SpearClient.GameSystem.Scene.Scenes;

import android.util.Log;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Background;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Cloud;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Enemy;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.EnemyHP;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.MoveLeft;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.MoveRight;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.MyHP;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player;
import com.example.SpearClient.GameSystem.Scene.Scene;
import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;

public class InGameScene extends Scene {
    public enum INGAME_SCENE_STATE {
        WAIT, GAME, FINISH, RESULT
    }

    Player player;
    Enemy enemy;
    MoveLeft moveLeft;
    MoveRight moveRight;
    MyHP myHP;
    EnemyHP enemyHP;

    public INGAME_SCENE_STATE state = INGAME_SCENE_STATE.WAIT;
    public float time = 0;

    @Override
    public void start() {
        player = new Player();
        enemy = new Enemy();
        moveLeft = new MoveLeft();
        moveRight = new MoveRight();
        myHP = new MyHP();
        enemyHP = new EnemyHP();

        objs.add(player);
        objs.add(enemy);
        objs.add(moveLeft);
        objs.add(moveRight);
        objs.add(myHP);
        objs.add(enemyHP);
        objs.add(new Background());
        objs.add(new Cloud());
    }

    @Override
    public void update() {
        super.update();

        time += Game.deltaTime;

        camera.position.x = (player.getTransform().position.x + enemy.getTransform().position.x) / 2f;
        float distance = Math.abs(player.getTransform().position.x - enemy.getTransform().position.x);
        float zoom = Math.min((float)Math.sqrt(GLView.defaultWidth / distance * 2) - 0.3f, 0.7f);

        camera.setZoomX(zoom);
        camera.setZoomY(zoom);
    }

    @Override
    public void finish() {
        super.finish();
    }
}
