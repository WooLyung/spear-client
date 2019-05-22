package com.example.SpearClient.GameSystem.Scene;

import android.util.Log;

import com.example.SpearClient.GameIO.Input;
import com.example.SpearClient.GameSystem.GameObject.IntroImage;
import com.example.SpearClient.GameSystem.GameObject.LoadingImage1;
import com.example.SpearClient.GameSystem.GameObject.LoadingImage2;
import com.example.SpearClient.GraphicSystem.GL.GLRenderer;
import com.example.SpearClient.GraphicSystem.ImageData;
import com.example.SpearClient.Main.Engine;
import com.example.SpearClient.Main.Game;

public class IntroScene extends Scene {
    private enum STATE {
        NONE,
        IMAGE1_APPEAR, IMAGE1, IMAGE1_DISAPPEAR,
        TERM,
        IMAGE2_APPEAR, IMAGE2, IMAGE2_DISAPPEAR,
        TERM2,
        INTRO_APPEAR, INTRO
    }

    private LoadingImage1 loadingImage1;
    private LoadingImage2 loadingImage2;
    private IntroImage introImage;
    private ImageData imgData1, imgData2, introData;
    private STATE state = STATE.NONE;
    private float time = 0;

    @Override
    public void start() {
        loadingImage1 = new LoadingImage1();
        loadingImage2 = new LoadingImage2();
        introImage = new IntroImage();
        objs.add(loadingImage1);
        objs.add(loadingImage2);
        objs.add(introImage);

        imgData1 = GLRenderer.imageDatas.get(GLRenderer.findImage("loading_image1"));
        imgData2 = GLRenderer.imageDatas.get(GLRenderer.findImage("loading_image2"));
        introData = GLRenderer.imageDatas.get(GLRenderer.findImage("intro_image"));
        float[] color = {
                1, 1, 1, 0,
                1, 1, 1, 0,
                1, 1, 1, 0,
                1, 1, 1, 0
        };
        imgData1.setColors(color);
        imgData2.setColors(color);
        introData.setColors(color);
    }

    @Override
    public void update() {
        super.update();

        animation();
        changeScene();
    }

    private void changeScene() {
        if (state == STATE.INTRO && (Input.getTouchState() == Input.TOUCH_STATE.DOWN || Input.getTouchState() == Input.TOUCH_STATE.STAY)) {
            Game.engine.changeScene(new IntroScene());
        }
    }

    private void animation() {
        time += Game.deltaTime;

        if (state == STATE.NONE) { // 아무것도 없음
            if (time >= 1) {
                state = STATE.IMAGE1_APPEAR;
                time = 0;
            }
        }
        else if (state == STATE.IMAGE1_APPEAR) { // 이미지1 나타남
            if (time >= 1) {
                state = STATE.IMAGE1;
                time = 0;
            }
            else {
                float[] color = {
                        1, 1, 1, time,
                        1, 1, 1, time,
                        1, 1, 1, time,
                        1, 1, 1, time
                };
                imgData1.setColors(color);
            }
        }
        else if (state == STATE.IMAGE1) { // 이미지1 유지
            if (time >= 1) {
                state = STATE.IMAGE1_DISAPPEAR;
                time = 0;
            }
        }
        else if (state == STATE.IMAGE1_DISAPPEAR) { // 이미지1 사라짐
            if (time >= 1) {
                state = STATE.TERM;
                time = 0;
            }
            else {
                float[] color = {
                        1, 1, 1, 1 - time,
                        1, 1, 1, 1 - time,
                        1, 1, 1, 1 - time,
                        1, 1, 1, 1 - time
                };
                imgData1.setColors(color);
            }
        }
        else if (state == STATE.TERM) { // 아무것도 없음
            if (time >= 1) {
                state = STATE.IMAGE2_APPEAR;
                time = 0;
            }
        }
        else if (state == STATE.IMAGE2_APPEAR) { // 이미지2 나타남
            if (time >= 1) {
                state = STATE.IMAGE2;
                time = 0;
            }
            else {
                float[] color = {
                        1, 1, 1, time,
                        1, 1, 1, time,
                        1, 1, 1, time,
                        1, 1, 1, time
                };
                imgData2.setColors(color);
            }
        }
        else if (state == STATE.IMAGE2) { // 이미지2 유지
            if (time >= 1) {
                state = STATE.IMAGE2_DISAPPEAR;
                time = 0;
            }
        }
        else if (state == STATE.IMAGE2_DISAPPEAR) { // 이미지2 사라짐
            if (time >= 1) {
                state = STATE.TERM2;
                time = 0;
            }
            else {
                float[] color = {
                        1, 1, 1, 1 - time,
                        1, 1, 1, 1 - time,
                        1, 1, 1, 1 - time,
                        1, 1, 1, 1 - time
                };
                imgData2.setColors(color);
            }
        }
        else if (state == STATE.TERM2) { // 아무것도 없음
            if (time >= 0.5f) {
                state = STATE.INTRO_APPEAR;
                time = 0;
            }
        }
        else if (state == STATE.INTRO_APPEAR) { // 인트로이미지 나타남
            if (time >= 1) {
                state = STATE.INTRO;
                time = 0;
            }
            else {
                float[] color = {
                        1, 1, 1, time,
                        1, 1, 1, time,
                        1, 1, 1, time,
                        1, 1, 1, time
                };
                introData.setColors(color);
            }
        }
    }
}