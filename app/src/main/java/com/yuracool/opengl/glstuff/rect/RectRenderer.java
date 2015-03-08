package com.yuracool.opengl.glstuff.rect;

import android.opengl.GLU;

import com.yuracool.opengl.glstuff.BasicRenderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Kuhta on 05.03.2015.
 */
public class RectRenderer extends BasicRenderer {
	private RectGL rectGL;

	public RectRenderer(long animationDuration, float angle){
		rectGL = new RectGL(animationDuration, angle);
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

		rectGL.draw(gl);

        //log();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();

        float max = Math.max(width, height);
        float left, right, top, bottom;
        left = right = max / height;
        top = bottom = max / width;

        gl.glFrustumf(-left, right, -bottom, top, 1, 20);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        GLU.gluLookAt(gl, 0, 0, -3, 0, 0, 0, 0, 1, 0);
	}

    public void animateIn(){
        rectGL.animateIn();
    }

    public void animateOut(){
        rectGL.animateOut();
    }
}
