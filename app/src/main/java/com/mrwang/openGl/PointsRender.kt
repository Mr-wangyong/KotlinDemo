package com.mrwang.openGl

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.util.Log
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * 点
 * @date 2018/11/5
 * @author chengwangyong
 */

object PointsRender : GLSurfaceView.Renderer {

    //shader语言跟C语言很像，它有一个主函数，也叫void main(){}。
    //gl_Position是一个内置变量，用于指定顶点，它是一个点，
    // 三维空间的点，所以用一个四维向量来赋值。vec4是四维向量的类型，
    // vec4()是它的构造方法。等等，三维空间，不是（x, y, z）三个吗？
    // 咋用vec4呢？ 四维是叫做齐次坐标，它的几何意义仍是三维，先了解这么多，
    // 记得对于2D的话，第四位永远传1.0就可以了。这里，是指定原点(0, 0, 0)作为顶点，
    // 就是说想在原点位置画一个点。gl_PointSize是另外一个内置变量，用于指定点的大小。
    //这个shader就是想在原点画一个尺寸为20的点。
    private const val VERTEX_SHADER =
            "void main() {\n" +
                    "gl_Position = vec4(0.0, 0.0, 0.0, 1.0);\n" +
                    "gl_PointSize = 20.0;\n" +
                    "}\n"


    //gl_FragColor是fragment shader的内置变量，用于指定当前顶点的颜色，
    // 四个分量（r, g, b, a）。这里是想指定为红色，不透明。
    private const val FRAGMENT_SHADER =
            "void main() {\n" +
                    "gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0);\n" +
                    "}\n"

    private var programs: Int = -1


    /**
     * 这个是最先被回调到的方法，告诉你系统层面，已经ready了，你可以开始做你的事情了。
     * 一般我们会在此方法里面做一些初始化工作，比如编译链接shader程序，初始化buffer等。
     */
    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        //告诉OpenGL，给我把背景，或者叫作画布，画成黑色，不透明。
        // 比较绕人的说法是用参数指定的(r, g, b, a)这个颜色来初始化颜色缓冲区（color buffer）。
        // 目前就理解成为画面背景色就可以了。
        GLES20.glClearColor(0f, 0f, 0f, 0f)

        // 创建一个vertex shader程序 返回值是一个句柄/程序指针 JNI 知识
        val vsh = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER)
        // 设置 vertex shader 源码
        GLES20.glShaderSource(vsh, VERTEX_SHADER)
        // 编译 vertex shader
        GLES20.glCompileShader(vsh)

        // 创建一个fragment shader程序 返回值是一个句柄/程序指针 JNI 知识
        val fsh = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER)
        GLES20.glShaderSource(fsh, FRAGMENT_SHADER)
        GLES20.glCompileShader(fsh)

        // 创建shader program句柄
        programs = GLES20.glCreateProgram()
        GLES20.glAttachShader(programs, vsh)// 把vertex shader添加到program
        GLES20.glAttachShader(programs, fsh)// 把fragment shader添加到program
        GLES20.glLinkProgram(programs)// 做链接，可以理解为把两种shader进行融合，做好投入使用的最后准备工作

        GLES20.glValidateProgram(programs)  // 让OpenGL来验证一下我们的shader program，并获取验证的状态

        val status = IntArray(1)
        GLES20.glGetProgramiv(programs, GLES20.GL_VALIDATE_STATUS, status, 0)

        Log.d(TAG, "validate shader program: " + GLES20.glGetProgramInfoLog(programs))
    }


    //此回调，会在surface发生改变时，通常是size发生变化。这里我们改变一下视角。
    // 就是要指定OpenGL的可视区域(view port)，（0, 0）是左上角，然后是width和height。
    // 我们目前只学习2D绘制，所以，先不管三维视角的处理。
    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height) // 参数是left, top, width, height
    }


    //这个是最重要的方法，没有之一。前面两个，只会在surface created时调一次。
    // 而此方法是用来绘制每帧的，所以每次刷新都会被调一次，所有的绘制都发生在这里。
    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)// 清除颜色缓冲区，因为我们要开始新一帧的绘制了，所以先清理，以免有脏数据。
        GLES20.glUseProgram(programs) // 告诉OpenGL，使用我们在onSurfaceCreated里面准备好了的shader program来渲染
        GLES20.glDrawArrays(GLES20.GL_POINTS, 0, 1)// 开始渲染，发送渲染点的指令， 第二个参数是offset，第三个参数是点的个数。目前只有一个点，所以是1。
    }


}