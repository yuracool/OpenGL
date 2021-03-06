package com.yuracool.opengl.glstuff.rect;

import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Kuhta on 06.03.2015.
 */
public class RectGL {
    private static final int MODE_IN = 1;
    private static final int MODE_OUT = 2;
	private static final float POINTS_ARRAY[] = {
			-1, 1, //left top
			-1, -1, //left bottom
			1, -1, //right bottom
			1, 1 // right top
	};

	private static final byte INDEXES_ARRAY[] = {0, 3, 1, 3, 2, 1};

	private FloatBuffer pointsBuffer;
	private ByteBuffer indexesBuffer;

    private final long animationDuration;
    private final float angle;
    private long animationStart;
    private int animationMode;
    private float coefficient = 1;
    private float previousCoefficient = 0;
    private float currentAngle = 0;

    private boolean isAnimate = false;

	public RectGL(long animationDuration, float angle){
        this.animationDuration = animationDuration;
        this.angle = angle;

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
        updateFrame();

		gl.glFrontFace(GL10.GL_CW);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, pointsBuffer);

        gl.glPushMatrix();
        gl.glRotatef(currentAngle, 0, 0, 1);
		gl.glDrawElements(GL10.GL_TRIANGLES, INDEXES_ARRAY.length, GL10.GL_UNSIGNED_BYTE, indexesBuffer);
        gl.glPopMatrix();

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

    public void animateIn() {
        animate(MODE_IN);
    }

    public void animateOut() {
        animate(MODE_OUT);
    }

    private void animate(int mode){
        animationStart = System.currentTimeMillis();
        animationMode = mode;
        previousCoefficient = 1 - coefficient;
        isAnimate = true;
    }

    private void updateFrame() {
        if(!isAnimate)
            return;

        // coefficient goes from 0 to 1
        coefficient = previousCoefficient + (System.currentTimeMillis() - animationStart) / (float) animationDuration;

        if(coefficient > 1){
            coefficient = 1;
            isAnimate = false;
        }

        float x1, x2;

        if(animationMode == MODE_IN){
            x1 = POINTS_ARRAY[0] - POINTS_ARRAY[0] * coefficient;
            x2 = POINTS_ARRAY[6] - POINTS_ARRAY[6] * coefficient;
            currentAngle = angle * coefficient;
        }else{
            x1 = POINTS_ARRAY[0] * coefficient;
            x2 = POINTS_ARRAY[6] * coefficient;
            currentAngle = angle - angle * coefficient;
        }

        pointsBuffer.put(0, x1);
        pointsBuffer.put(6, x2);
        pointsBuffer.position(0);
    }
}
