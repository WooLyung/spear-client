package com.example.SpearClient.GameSystem.Component.Components;

import android.util.Log;

import com.example.SpearClient.GameSystem.Component.Component;
import com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimSupportClasses.Animation;
import com.example.SpearClient.GameSystem.Component.Components.RendererComponent.Renderers.AnimationRenderer;
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

    // 이미지 코드
    public int imageCode_skin = 0;
    public int imageCode_action = 0;
    public int imageCode_frame = 0;

    // 동작
    public ACTION action = ACTION.REST;
    public float time = 0;
    public float invincible = 0;

    public void changeState(ACTION action) {
        int skinCode = AnimationManager.skinToCode();
        int[] anim = { 0 };
        boolean isChanged = false;

        if (action == ACTION.SHALLOW_STAB) {
            if (this.action == ACTION.DEFAULT || this.action == ACTION.RUN) {
                isChanged = true;

                anim = AnimationManager.playerAnims.get(skinCode).get(1);
                animationRenderer.setInterval(0.03f);
            }
        }
        else if (action == ACTION.DEEP_STAB) {
            if (this.action == ACTION.DEFAULT || this.action == ACTION.RUN) {
                isChanged = true;

                anim = AnimationManager.playerAnims.get(skinCode).get(2);
                animationRenderer.setInterval(0.03f);
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
            time = 0;

            animationRenderer.bindingImage(anim);
        }
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

        actionUpdate();
    }

    private void actionUpdate() {
        Log.i("playerAction", "" + action + "           " + time);

        if (action == ACTION.DEEP_STAB) {
            if (time >= 1) {
                changeState(ACTION.REST);
            }
        }
        else if (action == ACTION.SKIM) {
            if (time >= 1) {
                changeState(ACTION.REST);
            }
        }
        else if (action == ACTION.REST) {
            if (time >= 1) {
                changeState(ACTION.DEFAULT);
            }
        }
    }

    @Override
    public void finish() {
    }
}
