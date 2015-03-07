package com.yuracool.opengl.glstuff;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Yura on 07.03.2015.
 */
public class CubeGL {
    private static final float POINTS_ARRAY[] = {
            -1, 1, -1,//left top front
            -1, -1, -1,//left bottom front
            1, -1, -1,//right bottom front
            1, 1 , -1,// right top front
            -1, 1, 1,//left top back
            -1, -1, 1,//left bottom back
            1, -1, 1,//right bottom back
            1, 1 , 1,// right top back
    };

    private static final byte INDEXES_ARRAY[] = {
            0, 3, 1,   3, 2, 1, // front
            4, 7, 0,   7, 3, 0, // top
            5, 6, 4,   6, 7, 4, // back
            1, 2, 5,   2, 6, 5, // bottom
            4, 0, 5,   0, 1, 5, // left
            3, 7, 2,   7, 6, 2  // right
    };

    private FloatBuffer pointsBuffer;
    private ByteBuffer indexesBuffer;

    public CubeGL() {
        ByteBuffer bBuffer = ByteBuffer.allocateDirect(POINTS_ARRAY.length * Float.SIZE / 8);
        bBuffer.order(ByteOrder.nativeOrder());
        pointsBuffer = bBuffer.asFloatBuffer();
        pointsBuffer.put(POINTS_ARRAY);
        pointsBuffer.position(0);

        indexesBuffer = ByteBuffer.allocateDirect(INDEXES_ARRAY.length * Byte.SIZE / 8);
        indexesBuffer.order(ByteOrder.nativeOrder());
        indexesBuffer.put(INDEXES_ARRAY);
        indexesBuffer.position(0);
    }

    public void draw(GL10 gl){
        gl.glFrontFace(GL10.GL_CW); // clock wise

        gl.glEnable(GL10.GL_CULL_FACE); // Enable only top surface of the object
        gl.glCullFace(GL10.GL_BACK);    //

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY); // Set vertices and indexes
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, pointsBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, INDEXES_ARRAY.length, GL10.GL_UNSIGNED_BYTE, indexesBuffer);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY); // Clear what we previously enabled
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}
