package com.example.SpearClient.GameSystem.Other;

import android.util.Log;

import com.example.SpearClient.GameSystem.Component.Components.PlayerStateComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Enemy.EnemyHP;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player.MyHP;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player.Player;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.ResultBoard.ResultBoard;
import com.example.SpearClient.GameSystem.Scene.Scenes.InGameScene;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.SocketIO.SocketIOBuilder;

import org.json.JSONObject;

import io.socket.emitter.Emitter;

public class GameManager {
    public enum STATE {
        WAITING,
        GAMING,
        RESULT
    }

    public static boolean isWin = false;
    public STATE state = STATE.GAMING;

    private static GameManager instance;
    private MyHP myHP;
    private EnemyHP enemyHP;
    private Player player;

    private PlayerStateComponent playerStateComponent;

    public GameManager() {
        instance = this;

        SocketIOBuilder.getInstance().skill_on(new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    Log.i("skill_on", args[0].toString() + "*");
                    JSONObject jsonObject = new JSONObject(args[0].toString());
                    String subject = jsonObject.getString("subject");
                    String event = jsonObject.getString("event");

                    if (event.equals("damage")) { // 누군가가 피해를 받았을 때
                        boolean isCrit = jsonObject.getBoolean("isCrit");

                        if (subject.equals(SocketIOBuilder.id)) { // 피해를 줌
                            Game.engine.nowScene.camera.vibrateLight();
                        }
                        else { // 피해를 받음
                            ((InGameScene)Game.engine.nowScene).bloodTime = 1;

                            if (isCrit) {
                                Game.engine.nowScene.camera.vibrateHeavy();
                            }
                            else {
                                Game.engine.nowScene.camera.vibrateMiddle();
                            }
                        }
                    }
                    else if (event.equals("skim")) { // 누군가가 공격을 튕겨냈을 때
                        Game.slowTime = 1;
                        Game.engine.nowScene.camera.vibrateLight();

                        if (subject.equals(SocketIOBuilder.id)) { // 공격을 튕겨냄
                            playerStateComponent.isSkim = true;
                            playerStateComponent.time = 0.2f;
                        }
                        else { // 무방비 상태가 됨
                            playerStateComponent.changeState(PlayerStateComponent.ACTION.DEFENCELESS);
                        }
                    }
                }
                catch (Exception e) {

                }
            }
        });

        SocketIOBuilder.getInstance().gameover(new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    Log.i("gameover", args[0].toString());
                    JSONObject jsonObject = new JSONObject(args[0].toString());

                    JSONObject winner = jsonObject.getJSONObject("winner");
                    GameManager.isWin = winner.getString("username").equals(SocketIOBuilder.id);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                state = STATE.RESULT;
                Game.engine.nowScene.objs.add(new ResultBoard());
            }
        });
    }

    public void update() {
        if (myHP == null) {
            if (Game.engine.nowScene.findObjectByName("myHP") != null) {
                myHP = (MyHP) Game.engine.nowScene.findObjectByName("myHP");
            }
        }
        if (enemyHP == null) {
            if (Game.engine.nowScene.findObjectByName("enemyHP") != null) {
                enemyHP = (EnemyHP) Game.engine.nowScene.findObjectByName("enemyHP");

            }
        }
        if (player == null) {
            if (Game.engine.nowScene.findObjectByName("player") != null) {
                player = (Player) Game.engine.nowScene.findObjectByName("player");
            }
        }
        else if (playerStateComponent == null) {
            playerStateComponent = (PlayerStateComponent) player.getComponent("playerStateComponent");
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

    public static GameManager getInstance() {
        return instance;
    }
}
