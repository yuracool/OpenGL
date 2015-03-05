package com.yuracool.opengl.glstuff;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Kuhta on 05.03.2015.
 */
public class AppRenderer implements GLSurfaceView.Renderer{
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glClearColor(0f, 0.8f, 0.8f, 1f);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glClearColor(0f, 0.8f, 0.8f, 1f);
	}

	@Override
	public void onDrawFrame(GL10 gl) {

	}
}
