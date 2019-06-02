package com.example.SpearClient.GameSystem.Component.Components;

import com.example.SpearClient.GameSystem.Component.Component;

public class EnemyStateComponent extends Component {
    public enum ACTION {
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

    // 동작
    public ACTION action = ACTION.REST;
    public float time = 0;
    public float invincible = 0;

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
}
