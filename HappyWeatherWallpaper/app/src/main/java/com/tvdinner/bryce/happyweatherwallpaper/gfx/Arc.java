package com.tvdinner.bryce.happyweatherwallpaper.gfx;

/**
 * Created by Bryce on 11/22/2014.
 */


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Arc extends Element {
    public Arc(int subdivisions, float radius, float angleStart, float angleStop, float r, float g, float b) {
        super(GL10.GL_LINE_STRIP);
        generate(subdivisions, radius, angleStart, angleStop, r, g, b);
        regenerateBuffers();
    }

   protected void generate(int prec, float radius, float angleStart, float angleStop, float r, float g, float b) {
        vertices = new float[3*(prec+1)];
        colors = new float[4*(prec+1)];
        float a = angleStart;
        float da = (angleStop-angleStart)/prec;
        //generate the circle
        for(int i = 0, j = 0; i <= prec*3; i += 3, j += 4, a += da) {
            vertices[i] = (float)Math.cos(a);
            vertices[i+1] = (float)Math.sin(a);
            vertices[i+2] = 0.0f;

            colors[j] = r;
            colors[j+1] = g;
            colors[j+2] = b;
            colors[j+3] = 1.0f;
        }
    }
}