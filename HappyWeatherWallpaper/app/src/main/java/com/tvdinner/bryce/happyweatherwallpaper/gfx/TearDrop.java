package com.tvdinner.bryce.happyweatherwallpaper.gfx;

import com.tvdinner.bryce.happyweatherwallpaper.vector.Vector2;
import com.tvdinner.bryce.happyweatherwallpaper.vector.Vector3;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Bryce on 11/26/2014.
 */
public class TearDrop extends Element {
    public TearDrop(int subdivisions, Vector3 color1, Vector3 color2, ColorStyle colorStyle)
    {
        super(GL10.GL_TRIANGLE_FAN, color1, color2, colorStyle);
        this.primitive = primitive;
        position = new Vector2(0.0f, 0.0f);
        scale = new Vector2(1.0f, 1.0f);
        rot = 0.0f;
        generate(subdivisions, color1, color2, colorStyle);
        regenerateBuffers();
    }
    public TearDrop(int subdivisions) {
        this(subdivisions, new Vector3(1.0f, 1.0f, 1.0f), new Vector3(1.0f, 1.0f, 1.0f), ColorStyle.SINGLE);
    }
    public TearDrop() {
        this(10);
    }

    protected void generate(int subdivisions, Vector3 c1, Vector3 c2, ColorStyle colorStyle) {
        vertices = new float[3 * subdivisions];
        colors = new float[4 * subdivisions];
        Vector3 deltaC = new Vector3(0.0f, 0.0f, 0.0f);
        Vector3 c = c1;

        if(colorStyle == colorStyle.SINGLE) {
            deltaC = new Vector3(0.0f, 0.0f, 0.0f);
        }
        else if(colorStyle == colorStyle.LINEAR_GRADIENT) {
            deltaC = new Vector3((c2.x - c1.x) / subdivisions, (c2.y - c1.y) / subdivisions, (c2.z - c1.z) / subdivisions);
        }
        for(int i = 0; i < subdivisions; i++) {
            int vidx = i*3;
            int cidx = i*4;
            float a = (float)(Math.PI * 2) * ((float)i/subdivisions);
            vertices[vidx] = (float)Math.cos(a);
            vertices[vidx+1] = (float)(Math.sin(a)*Math.sin(a/2));
            vertices[vidx+2] = 0.0f;
            colors[cidx] = c.x;
            colors[cidx+1] = c.y;
            colors[cidx+2] = c.z;
            colors[cidx+3] = 1.0f;
            c.x += deltaC.x;
            c.y += deltaC.y;
            c.z += deltaC.z;
        }
    }
}
