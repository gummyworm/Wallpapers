package com.tvdinner.bryce.happyweatherwallpaper.creatures;

import com.tvdinner.bryce.happyweatherwallpaper.gfx.Arc;
import com.tvdinner.bryce.happyweatherwallpaper.gfx.Circle;
import com.tvdinner.bryce.happyweatherwallpaper.gfx.Element;
import com.tvdinner.bryce.happyweatherwallpaper.gfx.TearDrop;
import com.tvdinner.bryce.happyweatherwallpaper.vector.Vector3;

import zh.wang.android.apis.yweathergetter4a.WeatherInfo;


public class RainDrop extends FallingCreature {
    private float angle;
    private final int NUM_EDGES = 128;

    //the eyes
    private Circle eyeL;
    private Circle eyeR;
    private Arc smile;
    //private NStar body;
    private TearDrop body;

    private float eyeW, eyeH;
    private float smileW, smileH;

    /**
     * The Square constructor.
     * <p/>
     * Initiate the buffers.
     */
    public RainDrop() {
        super();
        rot = (float)Math.random();

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
        body = new TearDrop(24, new Vector3(0.5f, 0.5f, 0.5f), new Vector3(0.5f, 0.6f, 1.0f),
                Element.ColorStyle.LINEAR_GRADIENT);

        //build the creature
        elements.add(body);
        elements.add(eyeL);
        elements.add(eyeR);
        elements.add(smile);
    }
    public void setXVelocity(float x) {
        super.setXVelocity(x);
        //angle the raindrop in the direction of its velocity
        rot = -(float)Math.tan(velocity.y / x);
    }
    public void update() {
        super.update();
    }
    public void updateWeather(WeatherInfo weatherInfo) {
        setXVelocity(Integer.parseInt(weatherInfo.getWindSpeed()) / 300.0f);
    }
}
