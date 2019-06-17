package com.example.SpearClient.GameIO;

import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.Types.Vector;

public class Input {

    public static boolean isDown = false;
    public static boolean[] multiIsDown = new boolean[4];
    public static boolean isBackkeyDown = false;
    public static TOUCH_STATE touchState = TOUCH_STATE.NONE;
    public static TOUCH_STATE[] multiTouchState = new TOUCH_STATE[4];
    public static boolean backkeyDown = false;
    public static int touchCount = 0;
    public static Vector touchPos = new Vector();
    public static Vector[] multiTouchPos = new Vector[4];

    public enum TOUCH_STATE {
        NONE,
        UP,
        DOWN,
        STAY
    };

    public static void update() {
        if (isDown == false) {
            touchCount = 0;
        }

        if (Input.touchState == Input.TOUCH_STATE.NONE) {
            if (Input.isDown == true) {
                Input.touchState = Input.TOUCH_STATE.DOWN;
            }
        }
        else if (Input.touchState == Input.TOUCH_STATE.DOWN) {
            if (Input.isDown == true) {
                Input.touchState = Input.TOUCH_STATE.STAY;
            }
            else {
                Input.touchState = Input.TOUCH_STATE.UP;
            }
        }
        else if (Input.touchState == Input.TOUCH_STATE.UP) {
            if (Input.isDown == true) {
                Input.touchState = Input.TOUCH_STATE.DOWN;
            }
            else {
                Input.touchState = Input.TOUCH_STATE.NONE;
            }
        }
        else if (Input.touchState == Input.TOUCH_STATE.STAY) {
            if (Input.isDown == false) {
                Input.touchState = Input.TOUCH_STATE.UP;
            }
        }

        for (int i = 0; i < 4; i++) {
            if (Input.multiTouchState[i] == Input.TOUCH_STATE.NONE) {
                if (Input.multiIsDown[i] == true) {
                    Input.multiTouchState[i] = Input.TOUCH_STATE.DOWN;
                }
            }
            else if (Input.multiTouchState[i] == Input.TOUCH_STATE.DOWN) {
                if (Input.multiIsDown[i] == true) {
                    Input.multiTouchState[i] = Input.TOUCH_STATE.STAY;
                }
                else {
                    Input.multiTouchState[i] = Input.TOUCH_STATE.UP;
                }
            }
            else if (Input.multiTouchState[i] == Input.TOUCH_STATE.UP) {
                if (Input.multiIsDown[i] == true) {
                    Input.multiTouchState[i] = Input.TOUCH_STATE.DOWN;
                }
                else {
                    Input.multiTouchState[i] = Input.TOUCH_STATE.NONE;
                }
            }
            else if (Input.multiTouchState[i] == Input.TOUCH_STATE.STAY) {
                if (Input.multiIsDown[i] == false) {
                    Input.multiTouchState[i] = Input.TOUCH_STATE.UP;
                }
            }
        }

        if (isBackkeyDown) {
            isBackkeyDown = false;
            backkeyDown = true;
        }
        else {
            backkeyDown = false;
        }
    }

    public static TOUCH_STATE getTouchState() {
        return touchState;
    }

    public static Vector getTouchWorldPos() {
        Vector pos = new Vector();
        pos.x = 2 * (touchPos.x - Game.screenWidth / 2) * (float)GLView.nowWidth / Game.screenWidth + Game.engine.nowScene.camera.getPosition().x;
        pos.y = 2 * (Game.screenHeight - touchPos.y - Game.screenHeight / 2) * (float)GLView.nowHeight / Game.screenHeight + Game.engine.nowScene.camera.getPosition().y;

        return pos;
    }

    public static Vector getTouchUIPos() {
        Vector pos = new Vector();
        pos.x = 2 * (touchPos.x - Game.screenWidth / 2) * (float)GLView.defaultWidth / Game.screenWidth;
        pos.y = 2 * (Game.screenHeight - touchPos.y - Game.screenHeight / 2) * (float)GLView.defaultHeight / Game.screenHeight;

        return pos;
    }

    public static TOUCH_STATE getTouchState(int id) {
        if (id == 0)
            return getTouchState();
        return multiTouchState[id - 1];
    }

    public static Vector getTouchWorldPos(int id) {
        if (id == 0)
            return getTouchWorldPos();
        Vector pos = new Vector();
        pos.x = 2 * (multiTouchPos[id - 1].x - Game.screenWidth / 2) * (float)GLView.nowWidth / Game.screenWidth + Game.engine.nowScene.camera.getPosition().x;
        pos.y = 2 * (Game.screenHeight - multiTouchPos[id - 1].y - Game.screenHeight / 2) * (float)GLView.nowHeight / Game.screenHeight + Game.engine.nowScene.camera.getPosition().y;

        return pos;
    }

    public static Vector getTouchUIPos(int id) {
        if (id == 0)
            return getTouchUIPos();
        Vector pos = new Vector();
        pos.x = 2 * (multiTouchPos[id - 1].x - Game.screenWidth / 2) * (float)GLView.defaultWidth / Game.screenWidth;
        pos.y = 2 * (Game.screenHeight - multiTouchPos[id - 1].y - Game.screenHeight / 2) * (float)GLView.defaultHeight / Game.screenHeight;

        return pos;
    }
}
