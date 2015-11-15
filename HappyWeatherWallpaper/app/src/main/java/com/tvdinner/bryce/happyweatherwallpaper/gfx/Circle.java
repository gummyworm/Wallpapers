package com.tvdinner.bryce.happyweatherwallpaper.gfx;

/**
 * Created by Bryce on 11/22/2014.
 */

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * This class is an object representation of a Square containing the vertex
 * information and drawing functionality, which is called by the renderer.
 *
 * @author Savas Ziplies (nea/INsanityDesign)
 */
public class Circle extends Element {
    public Circle(int sides, float r, float g, float b)  {
        super(GL10.GL_TRIANGLE_FAN);
        generate(sides, r, g, b);
        regenerateBuffers();
    }
    protected void generate(int prec, float r, float g, float b) {
        vertices = new float[3*(prec+2)];
        colors = new float[4*(prec+2)];

        vertices[0] = 0.0f;
        vertices[1] = 0.0f;
        vertices[2] = 0.0f;
        colors[0] = r;
        colors[1] = g;
        colors[2] = b;
        colors[3] = 1.0f;
        //generate the circle
        for(int i = 3, j = 4; i <= (prec +1)* 3; i += 3, j += 4) {
            float a = (float)Math.PI*2 * ((float)i/(prec*3));

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
