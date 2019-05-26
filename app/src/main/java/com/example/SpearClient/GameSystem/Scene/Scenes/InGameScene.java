package com.example.SpearClient.GameSystem.Scene.Scenes;

import com.example.SpearClient.GameSystem.GameObject.GameObjects.Background;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.Cloud;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.MoveLeft;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.MoveRight;
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

    public INGAME_SCENE_STATE state = INGAME_SCENE_STATE.WAIT;
    public float time = 0;

    @Override
    public void start() {
        player = new Player();
        moveLeft = new MoveLeft();
        moveRight = new MoveRight();

        objs.add(player);
        objs.add(moveLeft);
        objs.add(moveRight);
        objs.add(new Background());
        objs.add(new Cloud());
    }

    @Override
    public void update() {
        super.update();

        time += Game.deltaTime;

        camera.angle += Game.deltaTime * 45;
        camera.position.x = 2 * (float)Math.cos((double)time);
        camera.position.y = 2 * (float)Math.sin((double)time);
        camera.setZoomX(2 * ((float)Math.cos((double)time) + 1.1f));
        camera.setZoomY(2 * ((float)Math.cos((double)time) + 1.1f));
    }

    @Override
    public void finish() {
        super.finish();
    }
}
