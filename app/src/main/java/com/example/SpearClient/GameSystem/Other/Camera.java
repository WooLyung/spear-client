package com.example.SpearClient.GameSystem.Other;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.Types.Vector;

public class Camera {
    public SharedPreferences pref;

    public class Vibration_move {
        public Vector power = new Vector();
        public Vector nowPower = new Vector();
        public Vector maxPower = new Vector();
        public Vector sign = new Vector();

        public Vibration_move() {
            power.x = 0;
            power.y = 0;
            nowPower.x = 0;
            nowPower.y = 0;
            maxPower.x = 0;
            maxPower.y = 0;
            sign.x = 1;
            sign.y = 1;
        }

        public void update() {
            if (pref.getBoolean("setting2", true)) {
                power.x = 0;
                nowPower.x = 0;
                maxPower.x = 0;
                sign.x = 0;
                power.y = 0;
                nowPower.y = 0;
                maxPower.y = 0;
                sign.y = 0;
            }

            if (maxPower.x != 0) {
                nowPower.x += power.x * sign.x * Game.getDeltaTime();
                maxPower.x -= Game.getDeltaTime() * 4.5f;
                power.x -= Game.getDeltaTime() / 8;
                if (sign.x == 1 && nowPower.x >= maxPower.x) {
                    sign.x = -1;
                }
                else if (sign.x == -1 && nowPower.x <= -maxPower.x) {
                    sign.x = 1;
                }

                if (maxPower.x < 0) {
                    maxPower.x = 0;
                    nowPower.x = 0;
                }
            }
            if (maxPower.y != 0) {
                nowPower.y += power.y * sign.y * Game.getDeltaTime();
                maxPower.y -= Game.getDeltaTime() * 4.5f;
                power.y -= Game.getDeltaTime() / 8;
                if (sign.y == 1 && nowPower.y >= maxPower.y) {
                    sign.y = -1;
                }
                else if (sign.y == -1 && nowPower.y <= -maxPower.y) {
                    sign.y = 1;
                }

                if (maxPower.y < 0) {
                    maxPower.y = 0;
                    nowPower.y = 0;
                }
            }
        }
    }

    public class Vibration_rot {
        public float power = 0;
        public float nowPower = 0;
        public float maxPower = 0;
        public int sign = 0;

        public Vibration_rot() {
            power = 0;
            nowPower = 0;
            maxPower = 0;
            sign = 1;
        }

        public void update() {
            if (pref.getBoolean("setting2", true)) {
                power = 0;
                nowPower = 0;
                maxPower = 0;
                sign = 0;
            }

            if (maxPower != 0) {
                nowPower += power * sign * Game.getDeltaTime();
                maxPower -= Game.getDeltaTime() * 7f;
                power -= Game.getDeltaTime() / 8;
                if (sign == 1 && nowPower >= maxPower) {
                    sign = -1;
                }
                else if (sign == -1 && nowPower <= -maxPower) {
                    sign = 1;
                }

                if (maxPower < 0) {
                    maxPower = 0;
                    nowPower = 0;
                }
            }
        }
    }

    private Vector position = new Vector();
    private float angle = 0;
    private Vector zoom = new Vector(1, 1);

    public Vibration_move vibration_move = new Vibration_move();
    public Vibration_rot vibration_rot = new Vibration_rot();

    public Camera() {
        setAngle(0);
        setZoomX(1);
        setZoomY(1);

        pref =  PreferenceManager.getDefaultSharedPreferences(Game.instance);
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getAngleNone() {
        return angle;
    }

    public float getAngle() {
        return angle + vibration_rot.nowPower;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector getPositionNone() {
        return position;
    }

    public Vector getPosition() {
        Vector vector = new Vector(position.x + vibration_move.nowPower.x, position.y + vibration_move.nowPower.y);
        return vector;
    }

    public void setZoomX(float x) {
        this.zoom.x = x;
        GLView.nowWidth = GLView.defaultWidth / x;
    }

    public void setZoomY(float y) {
        this.zoom.y = y;
        GLView.nowHeight = GLView.defaultHeight / y;
    }

    public float getZoomX() {
        return zoom.x;
    }

    public float getZoomY() {
        return zoom.y;
    }

    public void update() {
        vibration_move.update();
        vibration_rot.update();
    }

    public void vibrateMiddle() {
        vibration_move.maxPower.x = 2 * Game.engine.nowScene.camera.getZoomX();
        vibration_move.power.x = 50;
        vibration_move.maxPower.y = 2 * Game.engine.nowScene.camera.getZoomY();
        vibration_move.power.y = 30;

        vibration_rot.maxPower = 3;
        vibration_rot.power = 100;
    }

    public void vibrateLight() {
        vibration_move.maxPower.x = 0.8f * Game.engine.nowScene.camera.getZoomX();
        vibration_move.power.x = 50;
        vibration_move.maxPower.y = 0.8f * Game.engine.nowScene.camera.getZoomY();
        vibration_move.power.y = 30;

        vibration_rot.maxPower = 1.3f;
        vibration_rot.power = 100;
    }

    public void vibrateHeavy() {
        vibration_move.maxPower.x = 2.5f * Game.engine.nowScene.camera.getZoomX();
        vibration_move.power.x = 50;
        vibration_move.maxPower.y = 2.5f * Game.engine.nowScene.camera.getZoomY();
        vibration_move.power.y = 30;

        vibration_rot.maxPower = 4f;
        vibration_rot.power = 100;
    }
}
