package com.tvdinner.bryce.happyweatherwallpaper.creatures;

/**
 * Created by Bryce on 11/22/2014.
 */

import com.tvdinner.bryce.happyweatherwallpaper.gfx.Arc;
import com.tvdinner.bryce.happyweatherwallpaper.gfx.Circle;
import com.tvdinner.bryce.happyweatherwallpaper.gfx.Element;
import com.tvdinner.bryce.happyweatherwallpaper.gfx.NStar;
import com.tvdinner.bryce.happyweatherwallpaper.gfx.TearDrop;
import com.tvdinner.bryce.happyweatherwallpaper.vector.Vector3;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import zh.wang.android.apis.yweathergetter4a.WeatherInfo;


public class Snowflake extends FallingCreature {
    private float angle;
    private final int NUM_EDGES = 128;

    //the eyes
    private Circle eyeL;
    private Circle eyeR;
    private Arc smile;
    private NStar body;

    private float eyeW, eyeH;
    private float smileW, smileH;

    private float angularVelocity;

    /**
     * The Square constructor.
     * <p/>
     * Initiate the buffers.
     */
    public Snowflake() {
        super();
        rot = (float)Math.random();
        angularVelocity = (float)Math.random() * 0.05f;

        //make the eyes
        eyeR = new Circle(10, 0.4f, 0.3f, 0.9f);
        eyeL = new Circle(10, 0.4f, 0.3f, 0.9f);
        eyeL.setPosition(-0.05f, 0.05f);
        eyeR.setPosition(0.05f, 0.05f);
        eyeL.scale(0.025f, 0.025f);
        eyeR.scale(0.025f, 0.025f);

        //smile
        smile = new Arc(10, 1.0f, (float)Math.toRadians(180), (float)Math.toRadians(360), 0.4f,0.3f,0.9f);
        smile.scale(0.1f, 0.1f);

        //body
        body = new NStar();

        //build the creature
        elements.add(body);
        elements.add(eyeL);
        elements.add(eyeR);
        elements.add(smile);
    }

    public void update() {
        super.update();
        rot += angularVelocity;
    }
    public void updateWeather(WeatherInfo weatherInfo) {
        setXVelocity(Integer.parseInt(weatherInfo.getWindSpeed()) / 300.0f);
    }
}
