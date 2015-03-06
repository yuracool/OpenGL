package com.yuracool.opengl.glstuff;

import android.graphics.PointF;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Kuhta on 05.03.2015.
 */
public class AppRenderer implements GLSurfaceView.Renderer{
	private static final int ANIMATION_DURATION = 500;

	private TriangleGL triangleGL;
	private RectGL rectGL;
	private boolean animate = true;

	public AppRenderer(){
		triangleGL = new TriangleGL();
		rectGL = new RectGL();
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glDisable(GL10.GL_DITHER);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		gl.glClearColor(0f, 0.8f, 0.8f, 1f);
		gl.glClearDepthf(1f);
	}

	int x=0;
	long time = System.currentTimeMillis();
	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glDisable(GL10.GL_DITHER);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 0, 0, -2, 0, 0, 0, 0, 1, 0);

		rectGL.draw(gl);

		if(animate)
			animate();

		//triangleGL.draw(gl);

		if(System.currentTimeMillis() - time > 1000){
			System.out.println("Frames per second = " + String.valueOf(x));
			time = System.currentTimeMillis();
			x=0;
		}else{
			x++;
		}
	}

	long animDuration;
	long animStart;
	float fromX1, fromY1;
	float fromX2, fromY2;

	private void animate() {
		if(animStart == 0){
			animStart = System.currentTimeMillis();
			PointF point = rectGL.getTopLeft();
			fromX1 = point.x;
			fromY1 = point.y;

			point = rectGL.getTopRight();
			fromX2 = point.x;
			fromY2 = point.y;
		}else{
			animDuration = System.currentTimeMillis() - animStart;

			if(animDuration > ANIMATION_DURATION){
				animate = false;
			}else{
				float coefficient = (float) (ANIMATION_DURATION - animDuration) / ANIMATION_DURATION;

				rectGL.setTopLeft(fromX1 * coefficient, fromY1);
				rectGL.setTopRight(fromX2 * coefficient, fromY2);
			}
		}
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		float ratio = (float) width / height;
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 25);
	}
}
