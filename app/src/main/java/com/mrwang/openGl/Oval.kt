package com.mrwang.openGl

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


/**
 * @date 2018/11/8
 * @author chengwangyong
 */
open class Oval : GLSurfaceView.Renderer {

    private val VERTEX_SHADER =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}"


    //gl_FragColor是fragment shader的内置变量，用于指定当前顶点的颜色，
    // 四个分量（r, g, b, a）。这里是想指定为红色，不透明。
    private val FRAGMENT_SHADER =
            "precision mediump float;\n" +
                    " uniform vec4 vColor;\n" +
                    " void main() {\n" +
                    "     gl_FragColor = vColor;\n" +
                    " }"

    private var mProgram: Int = 0

    private var vertexBuffer: FloatBuffer


    private var mPositionHandle: Int = 0
    private var mColorHandle: Int = 0

    private val mViewMatrix = FloatArray(16)
    private val mProjectMatrix = FloatArray(16)
    private val mMVPMatrix = FloatArray(16)

    //顶点之间的偏移量
    private val vertexStride = 0 // 每个顶点四个字节

    private var mMatrixHandler: Int = 0

    private val radius = 0.5f
    private val n = 360  //切割份数

    private var shapePos: FloatArray

    private val height = 0.0f  // z轴的高度

    //设置颜色，依次为红绿蓝和透明通道
    var color = floatArrayOf(1.0f, 1.0f, 1.0f, 1.0f)

    private val COORDS_PER_VERTEX = 3

    init {
        shapePos = createPositions()
        vertexBuffer = ByteBuffer.allocateDirect(shapePos.size * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(shapePos)
        vertexBuffer.position(0);

    }

    /**
     *
     */
    private fun createPositions(): FloatArray {
        val data = ArrayList<Float>()
        data.add(0.0f)            //设置圆心坐标
        data.add(0.0f)
        data.add(height)
        val angDegSpan = 360f / n
        var i = 0f
        while (i < 360 + angDegSpan) {
            data.add((radius * Math.sin(i * Math.PI / 180f)).toFloat())
            data.add((radius * Math.cos(i * Math.PI / 180f)).toFloat())
            data.add(height)
            i += angDegSpan
        }
        return FloatArray(data.size).apply {
            forEachIndexed { index, _ ->
                this[index] = data[index]
            }
        }
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT or GLES20.GL_DEPTH_BUFFER_BIT)
        //将程序加入到OpenGLES2.0环境
        GLES20.glUseProgram(mProgram)
        //获取变换矩阵vMatrix成员句柄
        //mMatrixHandler = GLES20.glGetUniformLocation(mProgram, "vMatrix")
        //指定vMatrix的值
        //GLES20.glUniformMatrix4fv(mMatrixHandler, 1, false, mMVPMatrix, 0);
        //获取顶点着色器的vPosition成员句柄
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition")
        //启用三角形顶点的句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle)
        //准备三角形的坐标数据
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer)
        //获取片元着色器的vColor成员的句柄
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor")
        //设置绘制三角形的颜色
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);
        //绘制三角形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, shapePos.size / 3)
        //禁止顶点数组的句柄
        GLES20.glDisableVertexAttribArray(mPositionHandle)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
        //计算宽高比
//        val ratio = width.toFloat() / height
//        //设置透视投影
//        Matrix.frustumM(mProjectMatrix, 0, -ratio, ratio, -1f, 1f, 3f, 7f)
//        //设置相机位置
//        Matrix.setLookAtM(mViewMatrix, 0, 0f, 0f, 7.0f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)
//        //计算变换矩阵
//        Matrix.multiplyMM(mMVPMatrix, 0, mProjectMatrix, 0, mViewMatrix, 0)
    }

    /**
     * OpenGL的代码一定要在 生命周期里面使用
     */
    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        val vertexShader = loadShader(GLES20.GL_VERTEX_SHADER,
                VERTEX_SHADER)
        val fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,
                FRAGMENT_SHADER)

        //创建一个空的OpenGLES程序
        mProgram = GLES20.glCreateProgram()
        //将顶点着色器加入到程序
        GLES20.glAttachShader(mProgram, vertexShader)
        //将片元着色器加入到程序中
        GLES20.glAttachShader(mProgram, fragmentShader)
        //连接到着色器程序
        GLES20.glLinkProgram(mProgram)
    }


    fun loadShader(type: Int, shaderString: String): Int {
        val shader = GLES20.glCreateShader(type)
        GLES20.glShaderSource(shader, shaderString)
        GLES20.glCompileShader(shader)
        return shader
    }

}