package com.tvdinner.bryce.happyweatherwallpaper.vector;
/**
 * Created by Bryce on 11/26/2014.
 */
public class Vector3 {
    public float x;
    public float y;
    public float z;

    public Vector3() {
        x = 0.0f;
        y = 0.0f;
    }
    public Vector3(float x, float y) {
        this.x = x;
        this.y = y;
        this.z = 0.0f;
    }
    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float distance(Vector3 other) {
        return (float)Math.sqrt((other.x - x)*(other.x - x) + (other.y - y)*(other.y - y) +
                (other.z - z)*(other.z - z));
    }
}
