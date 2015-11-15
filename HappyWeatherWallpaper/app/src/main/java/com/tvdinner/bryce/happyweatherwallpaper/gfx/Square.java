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
public class Square extends Element {
    private float tick;
    public Square() {
        super(GL10.GL_TRIANGLE_STRIP);
        float c[] ={
            0.3f,1.0f,1.0f,1.0f, // Bottom Left
            0.34f,1.0f,1.0f,1.0f, // Bottom Right
            0.4f,0.9f,1.0f,1.0f, // Top Left
            0.25f,1.0f,0.9f,1.0f // Top Right
        };
        float v[] = {
            -1.0f, -1.0f, 0.0f, // Bottom Left
            1.0f,  -1.0f, 0.0f, // Bottom Right
            -1.0f,  1.0f, 0.0f, // Top Left
            1.0f,   1.0f, 0.0f  // Top Right
        };
        colors = c;
        vertices = v;
        regenerateBuffers();
        tick = 0.0f;
    }

    public void update() {
        //update colors
        colors[0] = (float)Math.sin(tick);
        colors[4] = (float)Math.sin(tick);
        colors[8] = (float)Math.cos(tick);
        colors[12] = (float)Math.cos(tick);
        //regenerate color buffer
        regenerateColors();
        tick += 0.0125f;
    }

    public void setColor(float r, float g, float b) {
        for(int i = 0; i < 12; i+=4) {
            colors[i] = r;
            colors[i+1] = g;
            colors[i+2] = b;
        }
        regenerateColors();
    }
}