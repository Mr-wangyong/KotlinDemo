package com.mrwang.openGl

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import javax.microedition.khronos.opengles.GL10

/**
 * @date 2018/11/5
 * @author chengwangyong
 */
interface Shape: GLSurfaceView.Renderer{
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


}