package com.yuracool.opengl.glstuff;

import android.graphics.PointF;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Kuhta on 05.03.2015.
 */
public class RectRenderer extends BasicRenderer{
	private RectGL rectGL;

	public RectRenderer(long animationDuration){
		rectGL = new RectGL(animationDuration);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glDisable(GL10.GL_DITHER);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		gl.glClearColor(0f, 0.8f, 0.8f, 1f);
		gl.glClearDepthf(1f);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glDisable(GL10.GL_DITHER);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 0, 0, -10, 0, 0, 0, 0, 1, 0);

		rectGL.draw(gl);

        //log();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		float ratio = (float) width / height;
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 25);
	}

    public void animateIn(){
        rectGL.animateIn();
    }

    public void animateOut(){
        rectGL.animateOut();
    }
}
