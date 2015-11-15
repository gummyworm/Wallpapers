package com.tvdinner.bryce.happyweatherwallpaper.vector;

import java.util.Vector;

/**
 * Created by Bryce on 11/26/2014.
 */
public class Vector2 {
    public float x;
    public float y;

    public Vector2() {
        x = 0.0f;
        y = 0.0f;
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float distance(Vector2 other) {
        return (float)Math.sqrt((other.x - x)*(other.x - x) + (other.y - y)*(other.y - y));
    }
}
