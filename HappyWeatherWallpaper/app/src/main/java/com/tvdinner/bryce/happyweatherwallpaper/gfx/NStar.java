package com.tvdinner.bryce.happyweatherwallpaper.gfx;

/**
 * Created by Bryce on 11/22/2014.
 */


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class NStar extends Element {
    public NStar() {
        super(GL10.GL_TRIANGLES);
        int sides = (int)(Math.random() * 24.0) + 4;
        generate(sides, 0.3f);
        regenerateBuffers();
    }
    public NStar(int subdivisions) {
        super(GL10.GL_TRIANGLES);
        generate(subdivisions, 0.3f);
        regenerateBuffers();
    }

    protected void generate(int edges, float depth) {
        vertices = new float[3 * 3 * edges];
        colors = new float[4 * 3 * edges];
        float r =  (float)Math.random();
        float r2 =  (float)Math.random();
        float g = 0.5f * (float)Math.random() + 0.3f;
        float g2 = g + 0.2f;
        for (int i = 0; i < edges; i++) {
            double a = ((double) (i - 1)) / edges;
            double b = ((double) i) / edges;
            double c = ((double) (i + 1)) / edges;
            //x0
            vertices[i * 9] = (float) (Math.cos(Math.PI * 2.0 * a)) * depth;
            //y0
            vertices[i * 9 + 1] = (float) (Math.sin(Math.PI * 2.0 * a)) * depth;
            //z0
            vertices[i * 9 + 2] = 0.0f;

            colors[i * 12] = r;
            colors[i * 12 + 1] = g;
            colors[i * 12 + 2] = 1.0f;
            colors[i * 12 + 3] = 1.0f;

            //x1
            vertices[i * 9 + 3] = (float) (Math.cos(Math.PI * 2.0 * b));
            //y1
            vertices[i * 9 + 4] = (float) (Math.sin(Math.PI * 2.0 * b));
            //z1
            vertices[i * 9 + 5] = 0.0f;

            colors[i * 12 + 4] = r2;
            colors[i * 12 + 5] = g2;
            colors[i * 12 + 6] = 1.0f;
            colors[i * 12 + 7] = 1.0f;

            //x2
            vertices[i * 9 + 6] = (float) (Math.cos(Math.PI * 2.0 * c)) * depth;
            //y2
            vertices[i * 9 + 7] = (float) (Math.sin(Math.PI * 2.0 * c)) * depth;
            //z2
            vertices[i * 9 + 8] = 0.0f;

            colors[i * 12 + 8] = r;
            colors[i * 12 + 9] = g;
            colors[i * 12 + 10] = 1.0f;
            colors[i * 12 + 11] = 1.0f;
        }
    }
}