package com.example.SpearClient.GameSystem.Other;

import android.util.Log;

import com.example.SpearClient.GraphicSystem.GL.GLView;
import com.example.SpearClient.Main.Game;
import com.example.SpearClient.Types.Vector;

public class Camera {

    public class Vibration_move {
        public float time = 0;
        public Vector power = new Vector();
        public Vector nowPower = new Vector();
        public Vector maxPower = new Vector();
        public Vector minPower = new Vector();
        public Vector sign = new Vector();

        public Vibration_move() {
            power.x = 0;
            power.y = 0;
            nowPower.x = 0;
            nowPower.y = 0;
            maxPower.x = 0;
            maxPower.y = 0;
            minPower.x = 0;
            minPower.y = 0;
            sign.x = 1;
            sign.y = 1;
        }

        public void update() {
            if (time > 0) {
                time -= Game.deltaTime;
                if (time < 0) {
                    time = 0;

                    nowPower.x = 0;
                    nowPower.y = 0;
                }

                nowPower.x += power.x * sign.x * Game.deltaTime;
                if (sign.x == 1 && nowPower.x >= maxPower.x) {
                    sign.x = -1;
                }
                else if (sign.x == -1 && nowPower.x <= minPower.x) {
                    sign.x = 1;
                }

                nowPower.y += power.y * sign.y * Game.deltaTime;
                if (sign.y == 1 && nowPower.y >= maxPower.y) {
                    sign.y = -1;
                }
                else if (sign.y == -1 && nowPower.y <= minPower.y) {
                    sign.y = 1;
                }
            }
        }
    }

    private Vector position = new Vector();
    private float angle = 0;
    private Vector zoom = new Vector(1, 1);

    public Vibration_move vibration_move = new Vibration_move();

    public Camera() {
        vibration_move.maxPower.x = 1;
        vibration_move.maxPower.y = 1;
        vibration_move.minPower.x = -1;
        vibration_move.minPower.y = -1;
        vibration_move.power.x = 16.6f;
        vibration_move.power.y = 14f;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getAngle() {
        return angle;
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
    }
}
