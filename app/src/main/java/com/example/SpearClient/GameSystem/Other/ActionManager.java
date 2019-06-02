package com.example.SpearClient.GameSystem.Other;

public class ActionManager {
    public enum ACTION {
        SHALLOW_STAB, // 얕게 찌르기
        DEEP_STAB,    // 깊게 찌르기
        RUSH_STAB,    // 돌진하며 찌르기
        RUSH,         // 돌진
        FALL,         // 빠지기
        SKIM,         // 걷어내기
        AVOID         // 흘리기
    }

    public static ACTION[] skill1 = { ACTION.SHALLOW_STAB, ACTION.SHALLOW_STAB, ACTION.SHALLOW_STAB, ACTION.SHALLOW_STAB };
    public static ACTION[] skill2 = { ACTION.SHALLOW_STAB, ACTION.SHALLOW_STAB, ACTION.SHALLOW_STAB, ACTION.SHALLOW_STAB };
    private static int nowAction1 = 0;
    private static int nowAction2 = 0;
    private float cool1 = 0;
    private float cool2 = 0;

    public static int getNowAction1() {
        return nowAction1;
    }

    public static int getNowAction2() {
        return nowAction2;
    }

    public static void setNowAction1(int nowAction1) {
        ActionManager.nowAction1 = nowAction1;
    }

    public static void setNowAction2(int nowAction2) {
        ActionManager.nowAction2 = nowAction2;
    }

    public float getCool1() {
        return cool1;
    }

    public float getCool2() {
        return cool2;
    }
}
