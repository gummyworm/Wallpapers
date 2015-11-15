package com.tvdinner.bryce.happyweatherwallpaper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import zh.wang.android.apis.yweathergetter4a.YahooWeatherInfoListener;
import zh.wang.android.apis.yweathergetter4a.*;

public class WallpaperService extends OpenGLES2WallpaperService implements YahooWeatherInfoListener, YahooWeatherExceptionListener  {
    MyEngine engine;
    private YahooWeather mYahooWeather = YahooWeather.getInstance(5000, 5000, true);
    public WallpaperService() {
        super();
    }
    @Override
    public void onCreate() {
    }
    @Override
    public void onFailConnection(final Exception e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void onFailParsing(final Exception e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void onFailFindLocation(final Exception e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void gotWeatherInfo(WeatherInfo weatherInfo) {
        if(weatherInfo != null) {
            // Add your code here
            engine.renderer.setWeather(weatherInfo);
            engine.renderer.setTempF(weatherInfo.getCurrentTempF());
            engine.renderer.setWindSpeed(Integer.parseInt(weatherInfo.getWindSpeed()));
            engine.renderer.setWeather(weatherInfo.getCurrentCode());
        }
    }
    public Renderer getNewRenderer() {
        return engine.renderer;
    }
    public Engine onCreateEngine() {
        engine = new MyEngine();
        mYahooWeather.setExceptionListener(this);
        mYahooWeather.setSearchMode(YahooWeather.SEARCH_MODE.GPS);
        mYahooWeather.queryYahooWeatherByGPS(getApplicationContext(), this);
        return engine;
    }
    class MyEngine extends GLEngine implements SharedPreferences.OnSharedPreferenceChangeListener, SensorEventListener {
        WallpaperRenderer renderer;
        private SensorManager sm;

        public MyEngine() {
            super();
            // handle prefs, other initialization
            renderer = new WallpaperRenderer();
            setRenderer(renderer);
            setRenderMode(RENDERMODE_CONTINUOUSLY);
        }

        public void onDestroy() {
            // Unregister this as listener
            sm.unregisterListener(this);

            // Kill renderer
            if (renderer != null) {
                renderer.release(); // assuming yours has this method - it
                // should!
            }
            renderer = null;
            setTouchEventsEnabled(false);
            super.onDestroy();
        }

        @Override
        public void onTouchEvent(MotionEvent event) {
            super.onTouchEvent(event);
            renderer.onTouchEvent(event);
        }

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);

            // Add touch events
            setTouchEventsEnabled(true);

            // Get sensormanager and register as listener.
            sm = (SensorManager) getSystemService(SENSOR_SERVICE);
            Sensor orientationSensor = sm
                    .getDefaultSensor(SensorManager.SENSOR_ORIENTATION);
            sm.registerListener(this, orientationSensor,
                    SensorManager.SENSOR_DELAY_GAME);

        }

        public void onSharedPreferenceChanged(
            SharedPreferences sharedPreferences, String key) {
        }
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
        public void onSensorChanged(SensorEvent event) {
            // /renderer.onSensorChanged(event);
        }
    }
}