package com.example.SpearClient.GameSystem.Other;

import java.util.ArrayList;

public class AnimationManager {
    public enum KNIGHT_SKIN {
        DEFAULT
    }

    public enum HORSE_SKIN {
        DEFAULT
    }

    public ArrayList<ArrayList<int[]>> playerAnims = new ArrayList<>(); // 플레이어의 [애니메이션 번호][애니메이션 종류]
    public static KNIGHT_SKIN knight_skin = KNIGHT_SKIN.DEFAULT;
    public static HORSE_SKIN horse_skin = HORSE_SKIN.DEFAULT;
}
