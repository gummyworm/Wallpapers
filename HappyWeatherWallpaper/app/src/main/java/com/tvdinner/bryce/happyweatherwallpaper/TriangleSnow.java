package com.tvdinner.bryce.happyweatherwallpaper;

import com.tvdinner.bryce.happyweatherwallpaper.gfx.Triangle;

import javax.microedition.khronos.opengles.GL10;

/**
 * This class is an object representation of a Triangle containing the vertex
 * information and drawing functionality, which is called by the renderer.
 *
 * @author Savas Ziplies (nea/INsanityDesign)
 */
public class TriangleSnow {
    private final int NUM_FLAKES = 150;
    private final float FLAKE_SIZE = 0.05f;
    private final float MIN_FLAKE_SIZE = 0.3f;

    private float[] velocities;
    private float[] xVelocities;
    private float[] angularVelocities;
    private float[] scales;
    private float[] xPositions;
    private float[] yPositions;
    private float[] zRotations;
    Triangle flake;

    /**
     * The Triangle constructor.
     *
     * Initiate the buffers.
     */
    public TriangleSnow() {
        flake = new Triangle(0.95f, 0.95f, 0.95f);

        velocities = new float[NUM_FLAKES];
        xVelocities = new float[NUM_FLAKES];
        angularVelocities = new float[NUM_FLAKES];
        scales = new float[NUM_FLAKES];
        xPositions = new float[NUM_FLAKES];
        yPositions = new float[NUM_FLAKES];
        zRotations = new float[NUM_FLAKES];
        for(int i = 0; i < NUM_FLAKES; i++) {
            velocities[i] = (float)Math.random() * 0.025f + .05f;
            xVelocities[i] = 0.0f;
            angularVelocities[i] = (float)Math.random() * 0.15f;
            scales[i] = (float)Math.random() * FLAKE_SIZE + 0.02f;
            xPositions[i] = -2.5f + (float)(Math.random() * 5f);
            yPositions[i] = 3.5f + (float)(Math.random() * 1.0f);
            zRotations[i] = (float)(Math.random() * Math.PI);
        }
    }

    /**
     * The object own drawing function. Called from the renderer to redraw this
     * instance with possible changes in values.
     *
     * @param gl
     *            - The GL context
     */
    public void draw(GL10 gl) {
        // Set the face rotation
        for(int i = 0; i < NUM_FLAKES; i++) {
            flake.setPosition(xPositions[i], yPositions[i]);
            flake.scale(scales[i], scales[i]);
            flake.rotate(zRotations[i]);
            flake.draw(gl);
        }
    }

    public void update() {
        for(int i = 0; i < NUM_FLAKES; ++i) {
            yPositions[i] -= velocities[i];
            zRotations[i] += angularVelocities[i];
            xPositions[i] += xVelocities[i];
            if(yPositions[i] < -4.0f) {
                yPositions[i] = 3.5f + (float)Math.random()*2.0f;
            }
            if(xPositions[i] > 2.5f) {
                xPositions[i] = -2.5f;
            }
            zRotations[i] += angularVelocities[i];
        }
    }
    public void setWind(int mph) {
        float speed = mph/100.0f;
        for(int i = 0; i < xVelocities.length; i++) {
            xVelocities[i] = speed + (float)Math.random()*0.003f;
        }
    }
}