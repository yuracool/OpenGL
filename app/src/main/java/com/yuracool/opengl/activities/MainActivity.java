package com.yuracool.opengl.activities;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import com.yuracool.opengl.glstuff.AppRenderer;

/**
 * Created by Kuhta on 05.03.2015.
 */
public class MainActivity extends Activity {
	GLSurfaceView surfaceView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initGLSurfaceView();

		setContentView(surfaceView);
	}

	private void initGLSurfaceView(){
		surfaceView = new GLSurfaceView(this);
		surfaceView.setRenderer(new AppRenderer());
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
