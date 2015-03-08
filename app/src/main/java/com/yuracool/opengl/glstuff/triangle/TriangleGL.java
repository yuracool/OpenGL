package com.yuracool.opengl.glstuff.triangle;

import javax.microedition.khronos.opengles.GL10;
import java.nio.*;

/**
 * Created by Kuhta on 06.03.2015.
 */
public class TriangleGL {
	private static final float TRIANGLE_POINTS_ARRAY[] = {
            0f, 1f,
            1f, -1f,
            -1f, -1f
	};

    private static final float TRIANGLE_COLOR_ARRAY[] = {
            1, 0, 0, 1,
            1, 0, 0, 1,
            1, 0, 0, 1
    };

    private static final byte TRIANGLE_INDEXES_ARRAY[] = {0, 1, 2};

    private static final float AXIS_POINTS_ARRAY[] = {
            -5,0,  -4.5f,0,  -4,0,  -3.5f,0,  -3,0,  -2.5f,0,  -2,0,  -1.5f,0,  -1,0, -0.5f,0,
            0.5f,0,  1,0,  1.5f,0,  2,0,  2.5f,0,  3,0,  3.5f,0,  4,0,  4.5f,0,  5,0,

            0,-5,  0,-4.5f,  0,-4,  0,-3.5f,  0,-3,  0,-2.5f,  0,-2,  0,-1.5f,  0,-1, 0,-0.5f,
            0,0.5f,  0,1,  0,1.5f,  0,2,  0,2.5f,  0,3,  0,3.5f,  0,4,  0,4.5f,  0,5
    };

    private static final float AXIS_COLOR_ARRAY[] = {
            1,1,0,1,  1,1,0,1,  1,1,0,1,  1,1,0,1,  1,1,0,1,  1,1,0,1,  1,1,0,1,  1,1,0,1,  1,1,0,1,  1,1,0,1,
            1,1,0,1,  1,1,0,1,  1,1,0,1,  1,1,0,1,  1,1,0,1,  1,1,0,1,  1,1,0,1,  1,1,0,1,  1,1,0,1,  1,1,0,1,

            0,0,1,1,  0,0,1,1,  0,0,1,1,  0,0,1,1,  0,0,1,1,  0,0,1,1,  0,0,1,1,  0,0,1,1,  0,0,1,1,  0,0,1,1,
            0,0,1,1,  0,0,1,1,  0,0,1,1,  0,0,1,1,  0,0,1,1,  0,0,1,1,  0,0,1,1,  0,0,1,1,  0,0,1,1,  0,0,1,1
    };

	private FloatBuffer trianglePointsBuffer;
    private ByteBuffer triangleIndexesBuffer;
    private FloatBuffer triangleColorBuffer;

    private FloatBuffer axisPointsBuffer;
    private FloatBuffer axisColorBuffer;

	public TriangleGL(){
		ByteBuffer bBuffer = ByteBuffer.allocateDirect(TRIANGLE_POINTS_ARRAY.length * Float.SIZE / 8);
		bBuffer.order(ByteOrder.nativeOrder());
		trianglePointsBuffer = bBuffer.asFloatBuffer();
		trianglePointsBuffer.put(TRIANGLE_POINTS_ARRAY);
		trianglePointsBuffer.position(0);

        bBuffer = ByteBuffer.allocateDirect(TRIANGLE_COLOR_ARRAY.length * Float.SIZE / 8);
        bBuffer.order(ByteOrder.nativeOrder());
        triangleColorBuffer = bBuffer.asFloatBuffer();
        triangleColorBuffer.put(TRIANGLE_COLOR_ARRAY);
        triangleColorBuffer.position(0);

		triangleIndexesBuffer = ByteBuffer.allocateDirect(TRIANGLE_INDEXES_ARRAY.length * Byte.SIZE / 8);
		triangleIndexesBuffer.order(ByteOrder.nativeOrder());
		triangleIndexesBuffer.put(TRIANGLE_INDEXES_ARRAY);
		triangleIndexesBuffer.position(0);

        bBuffer = ByteBuffer.allocateDirect(AXIS_POINTS_ARRAY.length * Float.SIZE / 8);
        bBuffer.order(ByteOrder.nativeOrder());
        axisPointsBuffer = bBuffer.asFloatBuffer();
        axisPointsBuffer.put(AXIS_POINTS_ARRAY);
        axisPointsBuffer.position(0);

        bBuffer = ByteBuffer.allocateDirect(AXIS_COLOR_ARRAY.length * Float.SIZE / 8);
        bBuffer.order(ByteOrder.nativeOrder());
        axisColorBuffer = bBuffer.asFloatBuffer();
        axisColorBuffer.put(AXIS_COLOR_ARRAY);
        axisColorBuffer.position(0);
	}

	public void draw(GL10 gl){
		gl.glFrontFace(GL10.GL_CW);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, trianglePointsBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, triangleColorBuffer);
		gl.glDrawElements(GL10.GL_TRIANGLES, TRIANGLE_INDEXES_ARRAY.length, GL10.GL_UNSIGNED_BYTE, triangleIndexesBuffer);

        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, axisPointsBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, axisColorBuffer);
        gl.glDrawArrays(GL10.GL_POINTS, 0, AXIS_POINTS_ARRAY.length);
        gl.glEnable(GL10.GL_POINT_SIZE);
        gl.glPointSize(4);
        gl.glDisable(GL10.GL_POINT_SIZE);

        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
}
