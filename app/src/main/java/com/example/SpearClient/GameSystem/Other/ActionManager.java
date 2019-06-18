package com.example.SpearClient.GameSystem.Other;

public class ActionManager {
    /*
    * 0 : 얕게 찌르기
    * 1 : 깊게 찌르기
    * 2 : 돌진하며 찌르기
    * 3 : 돌진
    * 4 : 흘리기
    * 5 : 걷어내기
    */

    public static int[] skill1 = { 0, 1, 2, 3 };
    public static int[] skill2 = { 4, 5, 0, 1 };
    public static int selectedSkill = -1;

    private static int nowAction1 = 0;
    private static int nowAction2 = 0;
    private static float cool1 = 0;
    private static float cool2 = 0;

    public static int getNowAction1() {
        return nowAction1;
    }

    public static int getNowAction2() {
        return nowAction2;
    }

    public static void setNowAction1(int nowAction) {
        ActionManager.nowAction1 = nowAction;
    }

    public static void setNowAction2(int nowAction) {
        ActionManager.nowAction2 = nowAction;
    }

    public static float getCool1() {
        return cool1;
    }

    public static float getCool2() {
        return cool2;
    }

    public static void setCool1(float cool1) {
        ActionManager.cool1 = cool1;
    }

    public static void setCool2(float cool2) {
        ActionManager.cool2 = cool2;
    }
}
