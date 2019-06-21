package com.example.SpearClient.GameSystem.Component.Components;

import com.example.SpearClient.GameSystem.Component.Component;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.AnimationRenderer;
import com.example.SpearClient.GameSystem.GameObject.GameObject;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Enemy.Enemy;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Player.Player;
import com.example.SpearClient.GameSystem.Other.AnimationManager;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.SocketIO.SocketIOBuilder;

import org.json.JSONObject;

public class PlayerStateComponent extends Component {
    private AnimationRenderer animationRenderer;

    public enum ACTION {
        DEFAULT,      // 기본 (V)
        WALK,         // 걷기 (V)
        RUN,          // 달리기 (V)
        SHALLOW_STAB, // * 얕게 찌르기 (V)
        DEEP_STAB,    // * 깊게 찌르기 (V)
        RUSH_STAB,    // * 돌진하며 찌르기 (V)
        RUSH,         // * 돌진 (V)
        REST,         // * 휴식 (중간 텀) (V)
        SKIM,         // * 걷어내기 (V)
        AVOID,        // * 흘리기 (V)
        DEFENCELESS,  // * 무방비 상태
        LOSE          // * 패배
    }

    private GameObject knight;

    // 동작
    public ACTION action = ACTION.REST;
    public float time = 0;
    public boolean isSkim = false;

    // 값
    private boolean isAttacked = false;
    private Enemy enemy;
    private EnemyStateComponent enemyStateComponent;
    private PlayerMoveComponent playerMoveComponent;

    public boolean changeState(ACTION action) {
        int skinCode = AnimationManager.skinToCode();
        int[] anim = { 0 };
        boolean isChanged = false;

        if (action == ACTION.SHALLOW_STAB) {
            if (this.action == ACTION.DEFAULT || this.action == ACTION.RUN) {
                isChanged = true;

                anim = AnimationManager.playerAnims.get(skinCode).get(1);
                animationRenderer.setInterval(0.6f / 16f);
            }
        }
        else if (action == ACTION.DEEP_STAB) {
            if (this.action == ACTION.DEFAULT || this.action == ACTION.RUN) {
                isChanged = true;

                anim = AnimationManager.playerAnims.get(skinCode).get(2);
                animationRenderer.setInterval(0.8f / 15f);
            }
        }
        else if (action == ACTION.RUSH_STAB) {
            if (this.action == ACTION.DEFAULT || this.action == ACTION.RUN) {
                isChanged = true;

                anim = AnimationManager.playerAnims.get(skinCode).get(3);
                animationRenderer.setInterval(1.3f / 30f);

                playerMoveComponent.setState(PlayerMoveComponent.STATE.RUSH);
            }
        }
        else if (action == ACTION.RUSH) {
            if (this.action == ACTION.DEFAULT || this.action == ACTION.RUN) {
                isChanged = true;

                anim = AnimationManager.playerAnims.get(skinCode).get(4);
                animationRenderer.setInterval(0.06f);

                playerMoveComponent.setState(PlayerMoveComponent.STATE.RUSH);
            }
        }
        else if (action == ACTION.SKIM) {
            if (this.action == ACTION.DEFAULT || this.action == ACTION.RUN) {
                isChanged = true;

                anim = AnimationManager.playerAnims.get(skinCode).get(5);
                animationRenderer.setInterval(0.8f / 17);

                if (enemyStateComponent.action == EnemyStateComponent.ACTION.DEEP_STAB
                    && enemyStateComponent.time <= 0.4f) {

                    try {
                        SocketIOBuilder.getInstance().skill_emit(new JSONObject("{\n" +
                                "\t\"event\":\"skim\"\n" +
                                "}"));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if (enemyStateComponent.action == EnemyStateComponent.ACTION.SHALLOW_STAB
                        && enemyStateComponent.time <= 0.3f) {

                    try {
                        SocketIOBuilder.getInstance().skill_emit(new JSONObject("{\n" +
                                "\t\"event\":\"skim\"\n" +
                                "}"));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else if (action == ACTION.AVOID) {
            if (this.action == ACTION.DEFAULT || this.action == ACTION.RUN) {
                isChanged = true;

                anim = AnimationManager.playerAnims.get(skinCode).get(6);
                animationRenderer.setInterval(0.03f);
            }
        }
        else if (action == ACTION.DEFENCELESS) {
            isChanged = true;

            anim = AnimationManager.playerAnims.get(skinCode).get(7);
            animationRenderer.setInterval(1.4f / 16f);
            if (playerMoveComponent.state != PlayerMoveComponent.STATE.IDLE)
                playerMoveComponent.setState(PlayerMoveComponent.STATE.WALK);
        }
        else if (action == ACTION.REST) {
            isChanged = true;

            anim = AnimationManager.playerAnims.get(skinCode).get(0);
        }
        else if (action == ACTION.DEFAULT) {
            isChanged = true;

            anim = AnimationManager.playerAnims.get(skinCode).get(0);
        }
        else if (action == ACTION.RUN) {
            if (this.action == ACTION.DEFAULT || this.action == ACTION.WALK) {
                isChanged = true;

                anim = AnimationManager.playerAnims.get(skinCode).get(4);
                animationRenderer.setInterval(0.06f);
            }
        }
        else if (action == ACTION.WALK) {
            if (this.action == ACTION.DEFAULT || this.action == ACTION.RUN) {
                isChanged = true;

                anim = AnimationManager.playerAnims.get(skinCode).get(0);
            }
        }

        if (isChanged) {
            this.action = action;
            this.isAttacked = false;
            time = 0;

            if (action == ACTION.DEFENCELESS) {
                knight.getTransform().position.y = 2.26f;
            }
            else {
                knight.getTransform().position.y = -0.3f;
            }

            animationRenderer.bindingImage(anim);
        }

        return isChanged;
    }

    @Override
    public void start() {
        setName("playerStateComponent");
    }

    @Override
    public void update() {
        time += Game.getDeltaTime();

        if (animationRenderer == null) {
            if (object != null) {
                if (object.findOfName("knight") != null) {
                    if (object.findOfName("knight").getComponent("animationRenderer") != null) {
                        animationRenderer = (AnimationRenderer) object.findOfName("knight").getComponent("animationRenderer");
                    }
                }
            }
        }
        if (enemy == null) {
            if (Game.engine.nowScene.findObjectByName("enemy") != null) {
                enemy = (Enemy) Game.engine.nowScene.findObjectByName("enemy");
                enemyStateComponent = (EnemyStateComponent) enemy.getComponent("enemyStateComponent");
            }
        }
        if (playerMoveComponent == null) {
            playerMoveComponent = (PlayerMoveComponent) object.getComponent("playerMoveComponent");
        }
        if (knight == null) {
            knight = ((Player)object).findOfName("knight");
        }

        actionUpdate();
    }

    private void actionUpdate() {
        if (action == ACTION.DEEP_STAB) {
            if (time >= 0.5f && !isAttacked) {
                isAttacked = true;

                if (enemyStateComponent.action != EnemyStateComponent.ACTION.AVOID) { // 회피중이 아닐 때
                    int dir = (object.getRenderer().getIsFlip() ? 1 : -1);
                    if (enemy.getTransform().position.x * dir <= object.getTransform().position.x * dir
                            && Math.abs(enemy.getTransform().position.x - object.getTransform().position.x) <= 5) { // 충돌 판정

                        try {
                            float damage = 20;
                            if (enemyStateComponent.action == EnemyStateComponent.ACTION.DEFENCELESS) {
                                damage *= 1.5f;
                            }

                            SocketIOBuilder.getInstance().skill_emit(new JSONObject("{\n" +
                                    "\t\"event\":\"damage\",\n" +
                                    "\t\"isCrit\":" + (enemyStateComponent.action == EnemyStateComponent.ACTION.DEFENCELESS) + ",\n" +
                                    "\t\"damage\":" + damage + "\n" +
                                    "}"));
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (time >= 0.8f) {
                changeState(ACTION.REST);
            }
        }
        else if (action == ACTION.SHALLOW_STAB) {
            if (time >= 0.35f && !isAttacked) {
                isAttacked = true;

                if (enemyStateComponent.action != EnemyStateComponent.ACTION.AVOID) { // 회피중이 아닐 때
                    int dir = (object.getRenderer().getIsFlip() ? 1 : -1);
                    if (enemy.getTransform().position.x * dir <= object.getTransform().position.x * dir
                            && Math.abs(enemy.getTransform().position.x - object.getTransform().position.x) <= 5) { // 충돌 판정

                        try {
                            float damage = 10;
                            if (enemyStateComponent.action == EnemyStateComponent.ACTION.DEFENCELESS) {
                                damage *= 1.5f;
                            }

                            SocketIOBuilder.getInstance().skill_emit(new JSONObject("{\n" +
                                    "\t\"event\":\"damage\",\n" +
                                    "\t\"isCrit\":" + (enemyStateComponent.action == EnemyStateComponent.ACTION.DEFENCELESS) + ",\n" +
                                    "\t\"damage\":" + damage + "\n" +
                                    "}"));
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (time >= 0.6f) {
                changeState(ACTION.REST);
            }
        }
        else if (action == ACTION.RUSH) {
            if (time >= 1.3f) {
                changeState(ACTION.REST);
                playerMoveComponent.setState(PlayerMoveComponent.STATE.RUN);
                playerMoveComponent.setTime(1.7f);
            }
        }
        else if (action == ACTION.RUSH_STAB) {
            if (!isAttacked) {
                if (enemyStateComponent.action != EnemyStateComponent.ACTION.AVOID) { // 회피중이 아닐 때
                    int dir = (object.getRenderer().getIsFlip() ? 1 : -1);
                    if (enemy.getTransform().position.x * dir <= object.getTransform().position.x * dir
                            && Math.abs(enemy.getTransform().position.x - object.getTransform().position.x) <= 5) { // 충돌 판정
                        isAttacked = true;

                        try {
                            float damage = 15;
                            if (enemyStateComponent.action == EnemyStateComponent.ACTION.DEFENCELESS) {
                                damage *= 1.5f;
                            }

                            SocketIOBuilder.getInstance().skill_emit(new JSONObject("{\n" +
                                    "\t\"event\":\"damage\",\n" +
                                    "\t\"isCrit\":" + (enemyStateComponent.action == EnemyStateComponent.ACTION.DEFENCELESS) + ",\n" +
                                    "\t\"damage\":" + damage + "\n" +
                                    "}"));
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (time >= 1.3f) {
                changeState(ACTION.REST);
                playerMoveComponent.setState(PlayerMoveComponent.STATE.RUN);
                playerMoveComponent.setTime(1.7f);
            }
        }
        else if (action == ACTION.AVOID) {
            if (time >= 0.8f) {
                changeState(ACTION.REST);
            }
        }
        else if (action == ACTION.SKIM) {
            if (time >= 0.8f) {
                changeState(ACTION.REST);
            }
        }
        else if (action == ACTION.DEFENCELESS) {
            if (time >= 1.4f) {
                changeState(ACTION.REST);
            }
        }
        else if (action == ACTION.REST) {
            if (time >= 0.5f || isSkim) {
                isSkim = false;
                changeState(ACTION.DEFAULT);
            }
        }
    }

    public int getActionCode() {
        switch(action) {
            case DEFAULT:
                return 0;
            case WALK:
                return 1;
            case RUN:
                return 2;
            case SHALLOW_STAB:
                return 3;
            case DEEP_STAB:
                return 4;
            case RUSH_STAB:
                return 5;
            case RUSH:
                return 6;
            case SKIM:
                return 7;
            case AVOID:
                return 8;
            case DEFENCELESS:
                return 9;
            case LOSE:
                return 10;
            case REST:
                return 11;
        }

        return -1;
    }

    @Override
    public void finish() {
    }
}
