package com.example.SpearClient.GameSystem.Other;

import com.example.SpearClient.GameSystem.Component.Components.PlayerStateComponent;

import java.util.ArrayList;

public class AnimationManager {
    public enum KNIGHT_SKIN {
        DEFAULT
    }

    public enum HORSE_SKIN {
        DEFAULT
    }

    public static ArrayList<ArrayList<int[]>> playerAnims = new ArrayList<>(); // 플레이어의 [애니메이션 번호][애니메이션 종류]
    public static KNIGHT_SKIN knight_skin = KNIGHT_SKIN.DEFAULT;
    public static HORSE_SKIN horse_skin = HORSE_SKIN.DEFAULT;

    public static int skinToCode() {
        switch(knight_skin) {
            case DEFAULT:
                return 0;
        }

        return -1;
    }

    /*
    0 : default 기본
    1 : shallow stab 얕게 찌르기
    2 : deep stab 깊게 찌르기
    3 : rush stab 돌진하며 찌르기
    4 : rush 돌진
    5 : skim 걷어내기
    6 : fall 숙이기
    7 : defenceless 무방비 상태
    */
}
