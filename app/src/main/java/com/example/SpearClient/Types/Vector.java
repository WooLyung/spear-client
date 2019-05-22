package com.example.SpearClient.Types;

public class Vector {

    public float x;
    public float y;

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector() {
        this.x = 0;
        this.y = 0;
    }

    public static float distanceDouble(Vector v1, Vector v2) {
        return (float)Math.pow(v1.x - v2.x, 2) + (float)Math.pow(v1.y - v2.y, 2);
    }

    public static float distance(Vector v1, Vector v2) {
        return (float)Math.sqrt(distanceDouble(v1, v2));
    }
}