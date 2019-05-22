package com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimSupportClasses;

import com.example.SpearClient.GameSystem.GameObject.GameObject;

import java.util.ArrayList;

public class AnimationData {
    private GameObject obj;
    private ArrayList<AnimationDataElement> rot, posX, posY, scaleX, scaleY;

    public GameObject getObj() {
        return obj;
    }

    public ArrayList<AnimationDataElement> getPosX() {
        return posX;
    }

    public ArrayList<AnimationDataElement> getPosY() {
        return posY;
    }

    public ArrayList<AnimationDataElement> getRot() {
        return rot;
    }

    public ArrayList<AnimationDataElement> getScaleX() {
        return scaleX;
    }

    public ArrayList<AnimationDataElement> getScaleY() {
        return scaleY;
    }

    public void setObj(GameObject obj) {
        this.obj = obj;
    }

    public void setScaleY(ArrayList<AnimationDataElement> scaleY) {
        this.scaleY = scaleY;
    }

    public void setScaleX(ArrayList<AnimationDataElement> scaleX) {
        this.scaleX = scaleX;
    }

    public void setPosY(ArrayList<AnimationDataElement> posY) {
        this.posY = posY;
    }

    public void setPosX(ArrayList<AnimationDataElement> posX) {
        this.posX = posX;
    }

    public void setRot(ArrayList<AnimationDataElement> rot) {
        this.rot = rot;
    }
}
