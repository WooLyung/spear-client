package com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimSupportClasses;

import java.util.ArrayList;

public class Animation {
    private ArrayList<AnimationData> animationDatas;
    private ArrayList<AnimationState> animationStates;
    private boolean isPlay = false;

    public ArrayList<AnimationData> getAnimationDatas() {
        return animationDatas;
    }

    public ArrayList<AnimationState> getAnimationStates() {
        return animationStates;
    }

    public boolean getIsPlay() {
        return isPlay;
    }

    public void setAnimationDatas(ArrayList<AnimationData> animationDatas) {
        this.animationDatas = animationDatas;
    }

    public void setAnimationStates(ArrayList<AnimationState> animationStates) {
        this.animationStates = animationStates;
    }

    public void play() {
        isPlay = true;
        for (int i = 0; i < animationStates.size(); i++) {
            if (animationStates.get(i).getRot() != null) {
                animationStates.get(i).getRot().setNowMotion(0);
                animationStates.get(i).getRot().setTime(0);
            }
            if (animationStates.get(i).getPosX() != null) {
                animationStates.get(i).getPosX().setNowMotion(0);
                animationStates.get(i).getPosX().setTime(0);
            }
            if (animationStates.get(i).getPosY() != null) {
                animationStates.get(i).getPosY().setNowMotion(0);
                animationStates.get(i).getPosY().setTime(0);
            }
            if (animationStates.get(i).getScaleX() != null) {
                animationStates.get(i).getScaleX().setNowMotion(0);
                animationStates.get(i).getScaleX().setTime(0);
            }
            if (animationStates.get(i).getScaleY() != null) {
                animationStates.get(i).getScaleY().setNowMotion(0);
                animationStates.get(i).getScaleY().setTime(0);
            }
        }
    }

    public void stop() {
        isPlay = false;
        for (int i = 0; i < animationStates.size(); i++) {
            if (animationStates.get(i).getRot() != null) {
                animationStates.get(i).getRot().setNowMotion(-1);
                animationStates.get(i).getRot().setTime(0);
            }
            if (animationStates.get(i).getPosX() != null) {
                animationStates.get(i).getPosX().setNowMotion(-1);
                animationStates.get(i).getPosX().setTime(0);
            }
            if (animationStates.get(i).getPosY() != null) {
                animationStates.get(i).getPosY().setNowMotion(-1);
                animationStates.get(i).getPosY().setTime(0);
            }
            if (animationStates.get(i).getScaleX() != null) {
                animationStates.get(i).getScaleX().setNowMotion(-1);
                animationStates.get(i).getScaleX().setTime(0);
            }
            if (animationStates.get(i).getScaleY() != null) {
                animationStates.get(i).getScaleY().setNowMotion(-1);
                animationStates.get(i).getScaleY().setTime(0);
            }
        }
    }
}
