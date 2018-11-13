package com.mrwang.openGl

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.ShortBuffer
import javax.microedition.khronos.opengles.GL10

/**
 * @date 2018/11/5
 * @author chengwangyong
 */
class Square2 : Shape() {

    //顶点缓冲区
    private val vertexBuffer: FloatBuffer
    //绘图顺序顶点缓冲区
    private val drawListBuffer: ShortBuffer


    //正方形四个顶点的坐标
    private val squareCoords = floatArrayOf(
            -0.5f, 0.5f, 0.0f,   // top left
            -0.5f, -0.5f, 0.0f,   // bottom left
            0.5f, -0.5f, 0.0f,   // bottom right
            0.5f, 0.5f, 0.0f     // top right
    )

    private val drawOrder = shortArrayOf(0, 1, 2, 0, 2, 3) // 顶点的绘制顺序
    // 设置图形的RGB值和透明度
    private val color = floatArrayOf(0.63671875f, 0.76953125f, 0.22265625f, 1.0f);

    init {
        // initialize vertex byte buffer for shape coordinates
        val bb = ByteBuffer.allocateDirect(
                // (坐标数 * 4)
                squareCoords.size * 4)
        bb.order(ByteOrder.nativeOrder())
        vertexBuffer = bb.asFloatBuffer()
        vertexBuffer.put(squareCoords)
        vertexBuffer.position(0)

        // 为绘制列表初始化字节缓冲
        val dlb = ByteBuffer.allocateDirect(
                // (对应顺序的坐标数 * 2)short是2字节
                drawOrder.size * 2)
        dlb.order(ByteOrder.nativeOrder())
        drawListBuffer = dlb.asShortBuffer()
        drawListBuffer.put(drawOrder)
        drawListBuffer.position(0)
    }

    override fun onDrawFrame(gl: GL10?) {
        draw(COORDS_PER_VERTEX, vertexBuffer, color, drawOrder, drawListBuffer);
    }

}