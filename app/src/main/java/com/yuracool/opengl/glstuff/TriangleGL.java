package com.yuracool.opengl.glstuff;

import javax.microedition.khronos.opengles.GL10;
import java.nio.*;

/**
 * Created by Kuhta on 06.03.2015.
 */
public class TriangleGL {
	private static final float POINTS_ARRAY[] = {
		0f, 1f,
		1f, -1f,
		-1f, -1f
	};

	private static final byte INDEXES_ARRAY[] = {0, 1, 2};

	private FloatBuffer pointsBuffer;
	private ByteBuffer indexesBuffer;

	public TriangleGL(){
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
}
