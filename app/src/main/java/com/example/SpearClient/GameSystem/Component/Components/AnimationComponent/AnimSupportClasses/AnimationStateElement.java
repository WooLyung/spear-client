package com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimSupportClasses;

public class AnimationStateElement {
    private float time = 0;
    private int nowMotion = -1;

    public float getTime() {
        return time;
    }

    public int getNowMotion() {
        return nowMotion;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setNowMotion(int nowMotion) {
        this.nowMotion = nowMotion;
    }
}
