package com.mrwang.openGl

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * @date 2018/11/1
 * @author chengwangyong
 */
const val TAG = "HelloPoints"

class GlActivity : AppCompatActivity() {

    private lateinit var glSurfaceView: GLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        glSurfaceView = GLSurfaceView(applicationContext)
        setContentView(glSurfaceView)

        // 设置 openGl 的类型 OpenGL ES 2.0
        glSurfaceView.setEGLContextClientVersion(2)
        // 设置 render
        glSurfaceView.setRenderer(getRender())
        // 设置渲染模式 一直渲染
        glSurfaceView.renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY

    }

    private fun getRender(): GLSurfaceView.Renderer? {
        return Square2()
    }

    override fun onResume() {
        super.onResume()
        glSurfaceView.onResume()
    }

    override fun onPause() {
        super.onPause()
        glSurfaceView.onPause()
    }



}