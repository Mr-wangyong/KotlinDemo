package com.mrwang.openGl

import android.opengl.GLES20
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.ShortBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * 矩形 https://blog.piasy.com/2016/06/14/Open-gl-es-android-2-part-2/
 * @date 2018/11/5
 * @author chengwangyong
 */
class Square : Shape() {

    private val VERTEX = floatArrayOf(// in counterclockwise order:
            1f, 1f, 0f, // top right
            -1f, 1f, 0f, // top left
            -1f, -1f, 0f, // bottom left
            1f, -1f, 0f)// bottom right
    private val VERTEX_INDEX = shortArrayOf(0, 1, 2, 0, 2, 3)

    private lateinit var mVertexIndexBuffer: ShortBuffer

    private lateinit var mVertexBuffer: FloatBuffer


    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        mVertexBuffer = ByteBuffer.allocateDirect(VERTEX.size * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(VERTEX)
        mVertexBuffer.position(0);

        mVertexIndexBuffer = ByteBuffer.allocateDirect(VERTEX_INDEX.size * 2)
                .order(ByteOrder.nativeOrder())
                .asShortBuffer()
                .put(VERTEX_INDEX)
        mVertexIndexBuffer.position(0)

    }

    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        // 用 glDrawElements 来绘制，mVertexIndexBuffer 指定了顶点绘制顺序
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, VERTEX_INDEX.size,
                GLES20.GL_UNSIGNED_SHORT, mVertexIndexBuffer)
    }
}