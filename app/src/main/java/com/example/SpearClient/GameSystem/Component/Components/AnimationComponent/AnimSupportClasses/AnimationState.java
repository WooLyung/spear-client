package com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimSupportClasses;

public class AnimationState {
    private AnimationStateElement rot, posX, posY, scaleX, scaleY;

    public AnimationStateElement getPosX() {
        return posX;
    }

    public AnimationStateElement getPosY() {
        return posY;
    }

    public AnimationStateElement getRot() {
        return rot;
    }

    public AnimationStateElement getScaleX() {
        return scaleX;
    }

    public AnimationStateElement getScaleY() {
        return scaleY;
    }

    public void setRot(AnimationStateElement rot) {
        this.rot = rot;
    }

    public void setPosX(AnimationStateElement posX) {
        this.posX = posX;
    }

    public void setPosY(AnimationStateElement posY) {
        this.posY = posY;
    }

    public void setScaleX(AnimationStateElement scaleX) {
        this.scaleX = scaleX;
    }

    public void setScaleY(AnimationStateElement scaleY) {
        this.scaleY = scaleY;
    }
}
