package com.yuracool.opengl.glstuff.cube;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Yura on 07.03.2015.
 */
public class CubeGL {
    private static final float POINTS_ARRAY[] = {
            -1,1,-1,     -1,-1,-1,     1,-1,-1,     1,1,-1,
            1,1,-1,      1,-1,-1,      1,-1,1,      1,1,1,
            -1,1,1,      -1,1,-1,      1,1,-1,      1,1,1,
            -1,1,1,      -1,-1,1,      -1,-1,-1,    -1,1,-1,
            1,1,1,       1,-1,1,       -1,-1,1,     -1,1,1,
            -1,-1,-1,    -1,-1,1,      1,-1,1,      1,-1,-1
    };

    private static final byte INDEXES_ARRAY[] = {
            0,3,1,     3,2,1,
            4,7,5,     7,6,5,
            8,11,9,    11,10,9,
            12,15,13,  15,14,13,
            16,19,17,  19,18,17,
            20,23,21,  23,22,21
    };

    private static final float COLOR_ARRAY[] = {
            1,0,0,1,   1,0,0,1,   1,0,0,1,   1,0,0,1,
            0,1,0,1,   0,1,0,1,   0,1,0,1,   0,1,0,1,
            0,0,1,1,   0,0,1,1,   0,0,1,1,   0,0,1,1,
            1,1,0,1,   1,1,0,1,   1,1,0,1,   1,1,0,1,
            1,1,1,1,   1,1,1,1,   1,1,1,1,   1,1,1,1,
            0,0,0,1,   0,0,0,1,   0,0,0,1,   0,0,0,1
    };

    private FloatBuffer pointsBuffer;
    private FloatBuffer colorBuffer;
    private ByteBuffer indexesBuffer;

    public CubeGL() {
        ByteBuffer bBuffer = ByteBuffer.allocateDirect(POINTS_ARRAY.length * Float.SIZE / 8);
        bBuffer.order(ByteOrder.nativeOrder());
        pointsBuffer = bBuffer.asFloatBuffer();
        pointsBuffer.put(POINTS_ARRAY);
        pointsBuffer.position(0);

        bBuffer = ByteBuffer.allocateDirect(COLOR_ARRAY.length * Float.SIZE / 8);
        bBuffer.order(ByteOrder.nativeOrder());
        colorBuffer = bBuffer.asFloatBuffer();
        colorBuffer.put(COLOR_ARRAY);
        colorBuffer.position(0);

        indexesBuffer = ByteBuffer.allocateDirect(INDEXES_ARRAY.length * Byte.SIZE / 8);
        indexesBuffer.order(ByteOrder.nativeOrder());
        indexesBuffer.put(INDEXES_ARRAY);
        indexesBuffer.position(0);
    }

    public void draw(GL10 gl){
        gl.glFrontFace(GL10.GL_CW); // clock wise

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY); // Set vertices, indexes, color
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        gl.glEnable(GL10.GL_CULL_FACE); // Enable only top surface of the object
        gl.glCullFace(GL10.GL_FRONT);    //
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, pointsBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, INDEXES_ARRAY.length, GL10.GL_UNSIGNED_BYTE, indexesBuffer);
        gl.glDisable(GL10.GL_CULL_FACE);

        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);   // Clear what we previously enabled
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
