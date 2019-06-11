package com.example.SpearClient.GameSystem.Component.Components;

import com.example.SpearClient.GameSystem.Component.Component;

public class EnemyStateComponent extends Component {
    public enum ACTION {
        DEFAULT,      // 기본
        WALK,         // 걷기
        RUN,          // 달리기
        SHALLOW_STAB, // * 얕게 찌르기
        DEEP_STAB,    // * 깊게 찌르기
        RUSH_STAB,    // * 돌진하며 찌르기
        RUSH,         // * 돌진
        REST,         // * 휴식 (중간 텀)
        SKIM,         // * 걷어내기
        AVOID,        // * 흘리기
        DEFENSELESS,  // * 무방비 상태
        LOSE          // * 패배
    }

    // 동작
    public ACTION action = ACTION.REST;
    public float time = 0;

    @Override
    public void start() {
        setName("enemyStateComponent");
    }

    @Override
    public void update() {
    }

    @Override
    public void finish() {
    }

    public void setAction(int code) {
        switch (code) {
            case 0:
                action = ACTION.DEFAULT;
                break;
            case 1:
                action = ACTION.WALK;
                break;
            case 2:
                action = ACTION.RUN;
                break;
            case 3:
                action = ACTION.SHALLOW_STAB;
                break;
            case 4:
                action = ACTION.DEEP_STAB;
                break;
            case 5:
                action = ACTION.RUSH_STAB;
                break;
            case 6:
                action = ACTION.RUSH;
                break;
            case 7:
                action = ACTION.SKIM;
                break;
            case 8:
                action = ACTION.AVOID;
                break;
            case 9:
                action = ACTION.DEFENSELESS;
                break;
            case 10:
                action = ACTION.LOSE;
                break;
            case 11:
                action = ACTION.REST;
                break;
        }
    }
}
