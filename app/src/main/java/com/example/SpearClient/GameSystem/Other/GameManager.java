package com.example.SpearClient.GameSystem.Other;

import android.util.Log;

import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Enemy;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.EnemyHP;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.MyHP;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.SocketIO.SocketIOBuilder;

import org.json.JSONObject;

import io.socket.emitter.Emitter;

public class GameManager {
    enum STATE {
        WAITING,
        GAMING,
        RESULT
    }

    public STATE state = STATE.GAMING;
    public MyHP myHP;
    public EnemyHP enemyHP;

    public GameManager() {
        SocketIOBuilder.getInstance().skill_on(new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    Log.i("skill_on", args[0].toString() + "*");
                    JSONObject jsonObject = new JSONObject(args[0].toString());
                    String subject = jsonObject.getString("subject");
                    String event = jsonObject.getString("event");

                    if (event.equals("damage")) { // 누군가가 피해를 받았을 때
                        if (subject.equals(SocketIOBuilder.id)) {

                        }
                        else {
                            Game.engine.nowScene.camera.vibrate();
                        }
                    }
                }
                catch (Exception e) {

                }
            }
        });
    }

    public void update() {
        if (myHP == null) {
            myHP = (MyHP) Game.engine.nowScene.findObjectByName("myHP");
        }
        if (enemyHP == null) {
            enemyHP = (EnemyHP) Game.engine.nowScene.findObjectByName("enemyHP");
        }
    }

    public void setMyHP(float hp) {
        if (myHP != null) {
            ((SpriteRenderer) myHP.front.getRenderer()).setFill(hp / 100f);
        }
    }

    public void setEnemyHP(float hp) {
        if (enemyHP != null) {
            ((SpriteRenderer) enemyHP.front.getRenderer()).setFill(hp / 100f);
        }
    }
}
