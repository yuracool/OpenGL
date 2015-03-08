package com.yuracool.opengl.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yuracool.opengl.R;

/**
 * Created by Kuhta on 05.03.2015.
 */
public class MainActivity extends Activity implements View.OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_main);

        initViews();
	}

    private void initViews() {
        findViewById(R.id.btnTriangle).setOnClickListener(this);
        findViewById(R.id.btnRect).setOnClickListener(this);
        findViewById(R.id.btnCube).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.btnTriangle){
            startActivity(new Intent(this, TriangleActivity.class));
        }else if(id == R.id.btnRect){
            startActivity(new Intent(this, RectActivity.class));
        }else if(id == R.id.btnCube){
            startActivity(new Intent(this, CubeActivity.class));
        }
    }
}
