package com.example.SpearClient.GameSystem.Component.Components.AnimationComponent.AnimSupportClasses;

public class AnimationDataElement {
    private float start, end, time;

    public float getTime() {
        return time;
    }

    public float getStart() {
        return start;
    }

    public float getEnd() {
        return end;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setStart(float start) {
        this.start = start;
    }

    public void setEnd(float end) {
        this.end = end;
    }
}
