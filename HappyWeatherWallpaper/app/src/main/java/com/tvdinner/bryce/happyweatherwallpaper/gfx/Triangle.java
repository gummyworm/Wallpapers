package com.tvdinner.bryce.happyweatherwallpaper.gfx;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * This class is an object representation of a Triangle containing the vertex
 * information and drawing functionality, which is called by the renderer.
 *
 * @author Savas Ziplies (nea/INsanityDesign)
 */
public class Triangle extends Element {
    public Triangle(float r, float g, float b) {
        super(GL10.GL_TRIANGLE_STRIP);
        float v[] = {
                0.0f,  1.0f, 0.0f, // Top
                -1.0f, -1.0f, 0.0f, // Bottom Left
                1.0f, -1.0f, 0.0f  // Bottom Right
        };
        float c[] = {
                1.0f,1.0f,1.0f,1.0f,
                1.0f,1.0f,1.0f,1.0f,
                1.0f,1.0f,1.0f,1.0f
        };
        vertices = v;
        colors = c;
        setColor(r,g,b);
        regenerateBuffers();
    }
}