package com.yuracool.opengl.activities;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * Created by Yura on 07.03.2015.
 */
public class BaseGLActivity extends Activity {
    protected GLSurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        surfaceView = new GLSurfaceView(this);
        setContentView(surfaceView);
    }

    @Override
    protected void onPause() {
        surfaceView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        surfaceView.onResume();
    }
}
