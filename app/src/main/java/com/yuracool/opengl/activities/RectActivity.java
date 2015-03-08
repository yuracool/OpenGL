package com.yuracool.opengl.activities;

import android.os.Bundle;
import android.view.View;

import com.yuracool.opengl.glstuff.rect.RectRenderer;

/**
 * Created by Yura on 07.03.2015.
 */
public class RectActivity extends BaseGLActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initGLSurfaceView();
    }

    	private void initGLSurfaceView(){
        final RectRenderer renderer = new RectRenderer(1000, 90);
		surfaceView.setRenderer(renderer);

        surfaceView.setOnClickListener(new View.OnClickListener() {
            boolean in = false;
            @Override
            public void onClick(View v) {
                in = !in;

                if(in)
                    renderer.animateIn();
                else
                    renderer.animateOut();
            }
        });
	}
}
