package com.example.SpearClient.GameSystem.Component.Components;

import android.util.Log;

import com.example.SpearClient.GameSystem.Component.Component;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.AnimationRenderer;
import com.example.SpearClient.GameSystem.GameObject.GameObjects.InGameScene.Enemy;
import com.example.SpearClient.GameSystem.Other.AnimationManager;
import com.example.SpearClient.Main.Game;

public class PlayerStateComponent extends Component {
    private AnimationRenderer animationRenderer;

    public enum ACTION {
        DEFAULT,      // 기본
        WALK,         // 걷기
        RUN,          // 달리기
        SHALLOW_STAB, // * 얕게 찌르기
        DEEP_STAB,    // * 깊게 찌르기
        RUSH_STAB,    // * 돌진하며 찌르기
        RUSH,         // * 돌진
        FALL,         // * 빠지기
        REST,         // * 휴식 (중간 텀)
        SKIM,         // * 걷어내기
        AVOID,        // * 흘리기
        DEFENSELESS,  // * 무방비 상태
        LOSE          // * 패배
    }

    // 동작
    public ACTION action = ACTION.REST;
    public float time = 0;

    // 값
    private boolean isAttacked = false;
    private Enemy enemy;
    private EnemyStateComponent enemyStateComponent;

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
                animationRenderer.setInterval(0.03f);
            }
        }
        else if (action == ACTION.RUSH) {
            if (this.action == ACTION.DEFAULT || this.action == ACTION.RUN) {
                isChanged = true;

                anim = AnimationManager.playerAnims.get(skinCode).get(4);
                animationRenderer.setInterval(0.06f);
            }
        }
        else if (action == ACTION.SKIM) {
            if (this.action == ACTION.DEFAULT || this.action == ACTION.RUN) {
                isChanged = true;

                anim = AnimationManager.playerAnims.get(skinCode).get(5);
                animationRenderer.setInterval(0.03f);
            }
        }
        else if (action == ACTION.FALL) {
            if (this.action == ACTION.DEFAULT || this.action == ACTION.RUN) {
                isChanged = true;

                anim = AnimationManager.playerAnims.get(skinCode).get(4);
                animationRenderer.setInterval(0.06f);
            }
        }
        else if (action == ACTION.AVOID) {
            if (this.action == ACTION.DEFAULT || this.action == ACTION.RUN) {
                isChanged = true;

                anim = AnimationManager.playerAnims.get(skinCode).get(6);
                animationRenderer.setInterval(0.03f);
            }
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
        time += Game.deltaTime;

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

        actionUpdate();
    }

    private void actionUpdate() {
        if (action == ACTION.DEEP_STAB) { // 작업중
            if (time >= 0.4f && !isAttacked) {
                isAttacked = true;

                if (enemyStateComponent.action != EnemyStateComponent.ACTION.AVOID) { // 회피중이 아닐 때
                    int dir = (object.getRenderer().getIsFlip() ? 1 : -1);
                    if (enemy.getTransform().position.x * dir <= object.getTransform().position.x * dir
                            && Math.abs(enemy.getTransform().position.x - object.getTransform().position.x) <= 5) { // 충돌 판정
                        Log.i("attack", "deep stab!");
                    }
                }
            }
            if (time >= 0.8f) {
                changeState(ACTION.REST);
            }
        }
        else if (action == ACTION.SHALLOW_STAB) {
            if (time >= 0.3f && !isAttacked) {
                isAttacked = true;

                if (enemyStateComponent.action != EnemyStateComponent.ACTION.AVOID) { // 회피중이 아닐 때
                    int dir = (object.getRenderer().getIsFlip() ? 1 : -1);
                    if (enemy.getTransform().position.x * dir <= object.getTransform().position.x * dir
                            && Math.abs(enemy.getTransform().position.x - object.getTransform().position.x) <= 5) { // 충돌 판정
                        Log.i("attack", "shallow stab!");
                    }
                }
            }
            if (time >= 0.6f) {
                changeState(ACTION.REST);
            }
        }
        else if (action == ACTION.SKIM) {
            if (time >= 1) {
                changeState(ACTION.REST);
            }
        }
        else if (action == ACTION.REST) { // 작업중
            if (time >= 0.5f) {
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
            case RUSH:
                return 5;
            case FALL:
                return 6;
            case REST:
                return 7;
            case SKIM:
                return 8;
            case AVOID:
                return 9;
            case DEFENSELESS:
                return 10;
            case LOSE:
                return 11;
        }

        return -1;
    }

    @Override
    public void finish() {
    }
}
