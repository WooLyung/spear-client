package com.example.SpearClient.GameSystem.Scene.Scenes;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Background;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Cloud;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.EnemyHP;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.MoveLeft;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.MoveRight;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.MyHP;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player;
import com.example.SpearClient.GameSystem.Scene.Scene;
import com.example.SpearClient.Main.Game;

public class InGameScene extends Scene {
    public enum INGAME_SCENE_STATE {
        WAIT, GAME, FINISH, RESULT
    }

    Player player;
    MoveLeft moveLeft;
    MoveRight moveRight;
    MyHP myHP;
    EnemyHP enemyHP;

    public INGAME_SCENE_STATE state = INGAME_SCENE_STATE.WAIT;
    public float time = 0;

    @Override
    public void start() {
        player = new Player();
        moveLeft = new MoveLeft();
        moveRight = new MoveRight();
        myHP = new MyHP();
        enemyHP = new EnemyHP();

        objs.add(player);
        objs.add(moveLeft);
        objs.add(moveRight);
        objs.add(myHP);
        objs.add(enemyHP);
        objs.add(new Background());
        objs.add(new Cloud());

        camera.setZoomX(0.7f);
        camera.setZoomY(0.7f);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
