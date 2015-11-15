package com.tvdinner.bryce.happyweatherwallpaper.creatures;

import com.tvdinner.bryce.happyweatherwallpaper.gfx.*;
import com.tvdinner.bryce.happyweatherwallpaper.vector.Vector2;

import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

import zh.wang.android.apis.yweathergetter4a.WeatherInfo;

/**
 * Created by Bryce on 11/25/2014.
 */
public class Creature {
    ///the x and y positions of this creature
    public Vector2 position;
    //the scale
    public Vector2 scale;
    //the rotation
    public float rot;
    ///the graphical components which compose this element.
    protected Vector<Element> elements;

    public Creature() {
        elements = new Vector<Element>();
        scale = new Vector2(1.0f, 1.0f);
        position = new Vector2(0.0f, 0.0f);
        rot = 0.0f;
    }
    public void update() {
    }
    public void updateWeather(WeatherInfo weather) {

    }
    public void draw(GL10 gl) {
        gl.glPushMatrix();
        gl.glTranslatef(position.x, position.y, 0.0f);
        gl.glRotatef(rot, 0.0f, 0.0f, 1.0f);
        gl.glScalef(scale.x, scale.y, 1.0f);
        for(int i = 0; i < elements.size(); i++) {
            Element e = elements.elementAt(i);
            e.draw(gl);
        }
        gl.glPopMatrix();
    }
}
