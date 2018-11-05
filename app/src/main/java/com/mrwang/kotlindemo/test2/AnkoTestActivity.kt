package com.mrwang.kotlindemo.release.test2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearLayoutManager.VERTICAL
import android.support.v7.widget.RecyclerView
import android.view.ViewManager
import com.mrwang.kotlindemo.test2.AnkoData
import com.mrwang.kotlindemo.test2.AnkoTestAdapter
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.matchParent

/**
 * AnkoLayout 的使用
 * https://github.com/Kotlin/anko
 * @author chengwangyong
 * @date 2018/5/9
 */
class AnkoTestActivity : AppCompatActivity(), AnkoLogger {

    private val data: List<AnkoData> by lazy {
        ArrayList<AnkoData>().apply {
            (0..100).forEach {
                if (it == 0) {
                    add(AnkoData.TitleData("陌陌直播Title"))
                } else {
                    add(AnkoData.NormalData("陌陌直播", "陌陌直播副标题"))
                }
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout {
            recyclerView {
                layoutManager = LinearLayoutManager(applicationContext).apply {
                    orientation = VERTICAL
                }
                adapter = AnkoTestAdapter(data)
            }.lparams(matchParent, matchParent)
        }

    }

    fun ViewManager.customView() = customView() {}

    fun ViewManager.customView(init: CustomView.() -> Unit): CustomView {
        return ankoView({ CustomView(it) }, theme = 0, init = init)
    }

    fun onClick(onClick: () -> Unit) {

    }

    fun ViewManager.recyclerView(init: RecyclerView.() -> Unit): RecyclerView {
        return ankoView({ RecyclerView(it) }, theme = 0, init = init)
    }


}



