package com.tvdinner.bryce.happyweatherwallpaper.gfx;

import com.tvdinner.bryce.happyweatherwallpaper.vector.Vector2;
import com.tvdinner.bryce.happyweatherwallpaper.vector.Vector3;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Bryce on 11/25/2014.
 */
public class Element {
    public enum ColorStyle {
        SINGLE, LINEAR_GRADIENT
    }

    ///the position of this element
    public Vector2 position;
    ///the scale of this element
    public Vector2 scale;
    ///the rotation of this element
    public float rot;

    protected FloatBuffer vertexBuffer;
    protected FloatBuffer colorBuffer;

    /** The initial vertex definition */
    protected float vertices[];
    protected float colors[];
    //the primitive used to render this element
    protected int primitive;

    protected ColorStyle colorStyle;
    protected Vector3 color1;
    protected Vector3 color2;

    public Element(int primitive, Vector3 color1, Vector3 color2, ColorStyle colorStyle)
    {
        this.primitive = primitive;
        position = new Vector2(0.0f, 0.0f);
        scale = new Vector2(1.0f, 1.0f);
        rot = 0.0f;
    }
    public Element(int primitive) {
        this(primitive, new Vector3(1.0f, 1.0f, 1.0f), new Vector3(1.0f, 1.0f, 1.0f), ColorStyle.SINGLE);
    }
    public Element() {
        this(GL10.GL_TRIANGLES);
    }

    public void draw(GL10 gl) {        // Set the face rotation
        gl.glFrontFace(GL10.GL_CCW);

        // Point to our vertex buffer
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        // Enable vertex buffer
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        //transform
        gl.glPushMatrix();
        gl.glTranslatef(position.x, position.y, 0.0f);
        gl.glRotatef(rot, 0.0f, 0.0f, 1.0f);
        gl.glScalef(scale.x, scale.y, 1.0f);

        // Draw the vertices according to this element's primitive
        gl.glDrawArrays(primitive, 0, vertices.length / 3);
        gl.glPopMatrix();

        // Disable the client state before leaving
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
    protected void update() {

    }
    public void regenerateBuffers() {
        regenerateVertices();
        regenerateColors();
    }

    public void regenerateVertices() {
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        vertexBuffer = byteBuf.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

    }
    public void regenerateColors() {
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(colors.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        colorBuffer = byteBuf.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);
    }
    public void setColor(float r, float g, float b) {
        for(int i = 0; i < colors.length; i+=4) {
            colors[i] = r;
            colors[i+1] = g;
            colors[i+2] = b;
            colors[i+3] = 1.0f;
        }
        regenerateColors();
    }

    public void scale(float x, float y) {
        scale.x = x;
        scale.y = y;
    }
    public void translate(float x, float y) {
        position.x += x;
        position.y += y;
    }
    public void rotate(float r) {
        rot = r;
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }
}

