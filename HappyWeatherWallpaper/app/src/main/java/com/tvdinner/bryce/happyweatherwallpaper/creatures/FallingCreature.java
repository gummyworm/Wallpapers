package com.tvdinner.bryce.happyweatherwallpaper.creatures;

import com.tvdinner.bryce.happyweatherwallpaper.vector.Vector2;

import zh.wang.android.apis.yweathergetter4a.WeatherInfo;

/**
 * Created by Bryce on 11/25/2014.
 */
public class FallingCreature extends Creature {
    ///the x and y velocities of this creature.
    protected Vector2 velocity;

    public FallingCreature() {
        super();
        initPos();
        initVel();
    }
    protected void initPos() {
        position.x = (float)Math.random() * 3.0f - 2.0f;
        position.y = (float)Math.random() * 3.0f;
    }
    protected void initVel() {
        velocity = new Vector2(0.0f, -(0.05f * (float)Math.random() + 0.01f));
    }

    public void update() {
        position.x += velocity.x;
        position.y += velocity.y;
        if(position.y < -3.5f) {
            position.y = 3.5f + (float)Math.random() * 3.0f;
        }
        if(position.x > 2.5f) {
            position.x = -2.5f;
        }
    }
    public void setXVelocity(float x) {
        velocity.x = x;
    }
    public void setYVelocity(float y) {
        velocity.y = y;
    }
    public void setVelocity(float x, float y) {
        setXVelocity(x);
        setYVelocity(y);
    }
}
