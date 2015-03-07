package com.yuracool.opengl.glstuff;

import android.opengl.GLSurfaceView;

/**
 * Created by Yura on 07.03.2015.
 */
public abstract class BasicRenderer implements GLSurfaceView.Renderer {
    private int x=0;
    private long time = System.currentTimeMillis();

    public void log(){
        if(System.currentTimeMillis() - time > 1000){
            System.out.println("Frames per second = " + String.valueOf(x));
            time = System.currentTimeMillis();
            x=0;
        }else{
            x++;
        }
    }
}
