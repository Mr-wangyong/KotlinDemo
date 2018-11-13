package com.mrwang.openGl

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import java.nio.FloatBuffer
import java.nio.ShortBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * @date 2018/11/5
 * @author chengwangyong
 */
abstract class Shape : GLSurfaceView.Renderer {
    // 每个顶点的坐标数
    protected val COORDS_PER_VERTEX = 3
    /**
     * float类型大小为4个字节
     */
    private val LENGTH = 4

    private val VERTEX_SHADER =
            "attribute vec4 vPosition;\n" +
                    " void main() {\n" +
                    "     gl_Position = vPosition;\n" +
                    " }"


    //gl_FragColor是fragment shader的内置变量，用于指定当前顶点的颜色，
    // 四个分量（r, g, b, a）。这里是想指定为红色，不透明。
    private val FRAGMENT_SHADER =
            "precision mediump float;\n" +
                    " uniform vec4 vColor;\n" +
                    " void main() {\n" +
                    "     gl_FragColor = vColor;\n" +
                    " }"


    fun loadShader(type: Int, shaderString: String): Int {
        val shader = GLES20.glCreateShader(type)
        GLES20.glShaderSource(shader, shaderString)
        GLES20.glCompileShader(shader)
        return shader
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
    }


    /**
     * 顶点着色器（Vertex Shader）：用来渲染形状顶点的OpenGL ES代码
     *
     * @return 顶点着色器
     */
    private fun getVertexShader(): Int {
        return loadShader(GLES20.GL_VERTEX_SHADER, VERTEX_SHADER)
    }

    /**
     * 片段着色器（Fragment Shader）：使用颜色或纹理渲染形状表面的OpenGL ES代码。
     *
     * @return 片段着色器
     */
    private fun getFragmentShader(): Int {
        return loadShader(GLES20.GL_FRAGMENT_SHADER, FRAGMENT_SHADER)
    }

    /**
     * 程式（Program）：一个OpenGL ES对象，包含了你希望用来绘制一个或更多图形所要用到的着色器
     * 程式（program)是用来装配着色器的（个人理解）
     *
     * @return 程式
     */
    protected fun getProgram(): Int {
        //获取顶点着色器
        val vertexShader = getVertexShader()
        //获取片段着色器
        val fragmentShader = getFragmentShader()

        val program = GLES20.glCreateProgram()             // 创建空的OpenGL ES Program
        GLES20.glAttachShader(program, vertexShader)   // 将vertex shader添加到program
        GLES20.glAttachShader(program, fragmentShader) // 将fragment shader添加到program
        GLES20.glLinkProgram(program)                  // 创建可执行的 OpenGL ES program

        // 添加program到OpenGL ES环境中
        GLES20.glUseProgram(program)
        return program
    }


    /**
     * @param coords_per_vertex 每个顶点的坐标数
     * @param vertexBuffer      浮点缓冲区
     * @param color             颜色数组，数组的四个数分别为图形的RGB值和透明度
     */
    fun draw(coords_per_vertex: Int, vertexBuffer: FloatBuffer, color: FloatArray) {
        //获取程式
        val program = getProgram()

        //得到处理到顶点着色器的vPosition成员
        val vPositionHandler = GLES20.glGetAttribLocation(program, "vPosition")

        // 启用一个指向图形的顶点数组的handle
        GLES20.glEnableVertexAttribArray(vPositionHandler)

        // 准备坐标数据
        GLES20.glVertexAttribPointer(vPositionHandler, coords_per_vertex,
                GLES20.GL_FLOAT, false,
                LENGTH * coords_per_vertex, vertexBuffer)

        // 得到处理到片段着色器的vPosition成员
        val mColorHandle = GLES20.glGetUniformLocation(program, "vColor")

        // 设置颜色
        GLES20.glUniform4fv(mColorHandle, 1, color, 0)

        // 绘制三角形比较简单，这里采用glDrawArrays方法(默认是逆时针方向)
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3)

        // 禁用指向图形的顶点数组
        GLES20.glDisableVertexAttribArray(vPositionHandler)
    }

    /**
     *
     * @param coords_per_vertex 每个顶点的坐标数
     * @param vertexBuffer      浮点缓冲区
     * @param color             颜色数组，数组的四个数分别为图形的RGB值和透明度
     * @param drawOrder         绘制顶点的顺序（按逆时针方向）
     * @param drawListBuffer    绘图顺序顶点的缓冲区
     */
    fun draw(coords_per_vertex: Int, vertexBuffer: FloatBuffer, color: FloatArray,
             drawOrder: ShortArray, drawListBuffer: ShortBuffer) {
        //获取程式
        val program = getProgram()

        //得到处理到顶点着色器的vPosition成员
        val vPositionHandler = GLES20.glGetAttribLocation(program, "vPosition")

        // 启用一个指向图形的顶点数组的handle
        GLES20.glEnableVertexAttribArray(vPositionHandler)

        // 准备坐标数据
        GLES20.glVertexAttribPointer(vPositionHandler, coords_per_vertex,
                GLES20.GL_FLOAT, false,
                LENGTH * coords_per_vertex, vertexBuffer)

        // 得到处理到片段着色器的vPosition成员
        val mColorHandle = GLES20.glGetUniformLocation(program, "vColor")

        // 设置颜色
        GLES20.glUniform4fv(mColorHandle, 1, color, 0)
        // 绘制图形
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.size, GLES20.GL_UNSIGNED_SHORT, drawListBuffer)

        // 禁用指向图形的顶点数组
        GLES20.glDisableVertexAttribArray(vPositionHandler)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
    }


}