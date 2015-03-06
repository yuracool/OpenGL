package com.yuracool.opengl.glstuff;

import android.graphics.PointF;

import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Kuhta on 06.03.2015.
 */
public class RectGL {
	private static final float POINTS_ARRAY[] = {
			-1, 1, //left top
			-1, -1, //left bottom
			1, -1, //right top
			1, 1 // right bottom
	};

	private static final byte INDEXES_ARRAY[] = {0, 1, 2, 2, 3, 0};

	private FloatBuffer pointsBuffer;
	private ByteBuffer indexesBuffer;
	private PointF point = new PointF();

	public RectGL(){
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
		gl.glFrontFace(GL10.GL_CW);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, pointsBuffer);
		gl.glDrawElements(GL10.GL_TRIANGLES, INDEXES_ARRAY.length, GL10.GL_UNSIGNED_BYTE, indexesBuffer);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	public PointF getTopLeft(){
		point.set(pointsBuffer.get(0), pointsBuffer.get(1));
		return point;
	}

	public PointF getBottomLeft(){
		point.set(pointsBuffer.get(2), pointsBuffer.get(3));
		return point;
	}

	public PointF getBottomRight(){
		point.set(pointsBuffer.get(4), pointsBuffer.get(5));
		return point;
	}

	public PointF getTopRight(){
		point.set(pointsBuffer.get(6), pointsBuffer.get(7));
		return point;
	}

	public void setTopLeft(float x, float y){
		pointsBuffer.put(0, x);
		pointsBuffer.put(1, y);
		pointsBuffer.position(0);
	}

	public void setBottomLeft(float x, float y){
		pointsBuffer.put(2, x);
		pointsBuffer.put(3, y);
		pointsBuffer.position(0);
	}

	public void setBottomRight(float x, float y){
		pointsBuffer.put(4, x);
		pointsBuffer.put(5, y);
		pointsBuffer.position(0);
	}

	public void setTopRight(float x, float y){
		pointsBuffer.put(6, x);
		pointsBuffer.put(7, y);
		pointsBuffer.position(0);
	}
}
