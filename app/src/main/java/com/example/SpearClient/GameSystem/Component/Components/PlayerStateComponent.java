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
        SHALLOW_STAB, // 얕게 찌르기
        DEEP_STAB,    // 깊게 찌르기
        RUSH_STAB,    // 돌진하며 찌르기
        RUSH,         // 돌진
        FALL,         // 빠지기
        REST,         // 휴식 (중간 텀)
        SKIM,         // 걷어내기
        AVOID,        // 흘리기
        DEFENSELESS,  // 무방비 상태
        LOSE          // 패배
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

        this.action = action;
        time = 0;

        if (action == ACTION.SHALLOW_STAB) {
            anim = AnimationManager.playerAnims.get(skinCode).get(1);
            animationRenderer.setInterval(0.03f);
        }
        else if (action == ACTION.DEEP_STAB) {
            anim = AnimationManager.playerAnims.get(skinCode).get(2);
            animationRenderer.setInterval(0.03f);
        }
        else if (action == ACTION.RUSH_STAB) {
            anim = AnimationManager.playerAnims.get(skinCode).get(3);
            animationRenderer.setInterval(0.03f);
        }
        else if (action == ACTION.RUSH) {
            anim = AnimationManager.playerAnims.get(skinCode).get(4);
            animationRenderer.setInterval(0.06f);
        }
        else if (action == ACTION.SKIM) {
            anim = AnimationManager.playerAnims.get(skinCode).get(5);
            animationRenderer.setInterval(0.03f);
        }
        else if (action == ACTION.FALL) {
            anim = AnimationManager.playerAnims.get(skinCode).get(4);
            animationRenderer.setInterval(0.06f);
        }
        else if (action == ACTION.AVOID) {
            anim = AnimationManager.playerAnims.get(skinCode).get(6);
            animationRenderer.setInterval(0.03f);
        }
        else if (action == ACTION.REST) {
            anim = AnimationManager.playerAnims.get(skinCode).get(0);
        }
        else if (action == ACTION.DEFAULT) {
            anim = AnimationManager.playerAnims.get(skinCode).get(0);
        }

        animationRenderer.bindingImage(anim);
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
    }

    @Override
    public void finish() {
    }
}
