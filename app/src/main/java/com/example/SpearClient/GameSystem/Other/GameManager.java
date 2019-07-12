package com.example.SpearClient.GameSystem.Other;

import android.util.Log;

import com.example.SpearClient.GameIO.SoundPlayer;
import com.example.SpearClient.GameSystem.Component.Components.PlayerMoveComponent;
import com.example.SpearClient.GameSystem.Component.Components.PlayerStateComponent;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.SpriteRenderer;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Enemy.EnemyHP;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player.MyHP;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player.Player;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.ResultBoard.ResultBoard;
import com.example.SpearClient.GameSystem.Scene.Scenes.InGameScene;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.R;
import com.example.SpearClient.SocketIO.SocketIOBuilder;

import org.json.JSONObject;

import io.socket.emitter.Emitter;

public class GameManager {
    public enum STATE {
        WAITING1,
        WAITING2,
        GAMING,
        RESULT
    }

    public float playerHealth = 100;
    public static boolean isWin = true;
    public STATE state = STATE.GAMING;

    private static GameManager instance;
    private MyHP myHP;
    private EnemyHP enemyHP;
    private Player player;

    private PlayerStateComponent playerStateComponent;
    private PlayerMoveComponent playerMoveComponent;

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
                        SoundPlayer.playSound(Game.instance, R.raw.hit, 0, 1, 4);

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

                            if (playerStateComponent.action == PlayerStateComponent.ACTION.RUSH_STAB) {
                                playerMoveComponent.setCompulsionState(PlayerMoveComponent.STATE.WALK);
                                playerStateComponent.changeState(PlayerStateComponent.ACTION.REST);
                            }
                        }
                    }
                    else if (event.equals("skim")) { // 누군가가 공격을 튕겨냈을 때
                        Game.slowTime = 1;
                        Game.engine.nowScene.camera.vibrateLight();
                        SoundPlayer.playSound(Game.instance, R.raw.defenseless, 0, 1, 4);

                        if (subject.equals(SocketIOBuilder.id)) { // 공격을 튕겨냄
                            playerStateComponent.isSkim = true;
                            playerStateComponent.time = 0.2f;
                        }
                        else { // 무방비 상태가 됨
                            if (playerMoveComponent.state != PlayerMoveComponent.STATE.IDLE)
                                playerMoveComponent.setCompulsionState(PlayerMoveComponent.STATE.WALK);
                            playerStateComponent.changeState(PlayerStateComponent.ACTION.DEFENCELESS);
                        }
                    }
                    else if (event.equals("action")) { // 누군가가 동작을 실행했을 때
                        if (!subject.equals(SocketIOBuilder.id)) { // 다른 사람이 동작을 실행함
                            String action = jsonObject.getString("action");

                            if (action.equals("shallow_stab")) {
                                SoundPlayer.playSound(Game.instance, R.raw.shallow_stab, 0, 1, 8);
                            }
                            else if (action.equals("deep_stab")) {
                                SoundPlayer.playSound(Game.instance, R.raw.deep_stab, 0, 1.4f, 4);
                            }
                            else if (action.equals("skim")) {
                                SoundPlayer.playSound(Game.instance, R.raw.deep_stab, 0, 1, 4);
                            }
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
        else if (playerMoveComponent == null) {
            playerMoveComponent = (PlayerMoveComponent) player.getComponent("playerMoveComponent");
        }
    }

    public void setMyHP(float hp) {
        if (myHP != null) {
            ((SpriteRenderer) myHP.front.getRenderer()).setFill(hp / 100f);
            playerHealth = hp;
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
