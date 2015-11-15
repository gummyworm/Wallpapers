package com.tvdinner.bryce.happyweatherwallpaper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.hardware.SensorEvent;
import android.opengl.GLU;
import android.view.MotionEvent;

import com.tvdinner.bryce.happyweatherwallpaper.creatures.Creature;
import com.tvdinner.bryce.happyweatherwallpaper.creatures.RainDrop;
import com.tvdinner.bryce.happyweatherwallpaper.creatures.Snowflake;
import com.tvdinner.bryce.happyweatherwallpaper.gfx.Square;

import zh.wang.android.apis.yweathergetter4a.WeatherInfo;

public class WallpaperRenderer implements GLWallpaperService.Renderer {
    private final int NUM_FLAKES = 10;

    private Square square;
    private Creature[] snowflakes;
    private TriangleSnow snow;

    private int shaderProgram;
    private int attributePosLoc;
    private int mvpLoc;
    private float angle;

    private WeatherInfo weather;

    public void onSensorChanged(SensorEvent event) {

    }

    public WallpaperRenderer() {
        square = new Square();
        square.scale(2.0f, 2.5f);
        snowflakes = new Creature[NUM_FLAKES];
        weather = new WeatherInfo();

        snow = new TriangleSnow();
        for(int i = 0; i < NUM_FLAKES; ++i) {
            snowflakes[i] = new Snowflake();
        }
        angle = 0.0f;
    }

    public void release() {
        // TODO stuff to release
    }

    /**
     * The Surface is created/init()
     */
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        String vShaderStr = "uniform mat4 u_mvpMatrix;         \n"
                + "attribute vec4 a_position;                  \n"
                + "void main()                                 \n"
                + "{                                           \n"
                + "   gl_Position = u_mvpMatrix * a_position;  \n"
                + "}                                           \n";

        String fShaderStr = "precision mediump float;                  \n"
                + "void main()                                         \n"
                + "{                                                   \n"
                + "  gl_FragColor = vec4( 1.0, 0.0, 0.0, 1.0 );        \n"
                + "}                                                   \n";

        gl.glShadeModel(GL10.GL_SMOOTH); // Enable Smooth Shading
        gl.glClearColor(1.0f, 0.3f, 1.0f, 0.5f); // Black Background
        gl.glClearDepthf(1.0f); // Depth Buffer Setup

        gl.glDisable(GL10.GL_DEPTH_TEST);
        //gl.glEnable(GL10.GL_DEPTH_TEST); // Enables Depth Testing
        //gl.glDepthFunc(GL10.GL_LEQUAL); // The Type Of Depth Testing To Do

        // Really Nice Perspective Calculations
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
    }

    /**
     * Here we do our drawing
     */
    public void onDrawFrame(GL10 gl) {
        // Clear Screen And Depth Buffer
        gl.glLineWidth(4);
        gl.glPointSize(8);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity(); // Reset The Current Modelview Matrix
        gl.glTranslatef(0.0f, 0.0f, -6.0f); // Move down 1.2 Unit And Into The Screen 6.0
        square.draw(gl);
        square.update();
        snow.draw(gl);
        snow.update();
        for(int i = 0; i < snowflakes.length; ++i) {
            snowflakes[i].update();
            snowflakes[i].draw(gl);
        }
        angle += 0.025f;
    }

    /**
     * If the surface changes, reset the view
     */
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (height == 0) { // Prevent A Divide By Zero By
            height = 1; // Making Height Equal One
        }

        gl.glViewport(0, 0, width, height); // Reset The Current Viewport
        gl.glMatrixMode(GL10.GL_PROJECTION); // Select The Projection Matrix
        gl.glLoadIdentity(); // Reset The Projection Matrix

        // Calculate The Aspect Ratio Of The Window
        GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f,
                100.0f);

        gl.glMatrixMode(GL10.GL_MODELVIEW); // Select The Modelview Matrix
        gl.glLoadIdentity(); // Reset The Modelview Matrix
    }

    /**
     * Override the touch screen listener.
     *
     * React to moves and presses on the touchscreen.
     */
    public boolean onTouchEvent(MotionEvent event) {
        // We handled the event
        return true;
    }
    public void setWeather(WeatherInfo weatherInfo) {
        for(int i = 0; i < NUM_FLAKES; i++) {
            snowflakes[i].updateWeather(weatherInfo);
        }
    }
    public void setWindSpeed(int mph) {
        float speed = mph/100.0f;
        snow.setWind(mph);
    }
    public void setTempF(float degF) {
        float r = (degF+50.0f) / 100.0f;
        if(r > 1.0f) {
            r = 1.0f;
        }
        else if(r < 0.0f) {
            r = 0.0f;
        }
        float g = 1.0f;
        float b = 1.0f;
        r = 1.0f;
        square.setColor(r, g, b);
    }
    public void setWeather(int code) {
        //check if weather is available
        if(code == 3200) {
            return;
        }
    }
}