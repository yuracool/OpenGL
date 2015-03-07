package com.yuracool.opengl.activities;

import android.os.Bundle;

import com.yuracool.opengl.glstuff.TriangleRenderer;

/**
 * Created by Yura on 07.03.2015.
 */
public class TriangleActivity extends BaseGLActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initGLSurfaceView();
    }

    private void initGLSurfaceView() {
        surfaceView.setRenderer(new TriangleRenderer());
    }
}
