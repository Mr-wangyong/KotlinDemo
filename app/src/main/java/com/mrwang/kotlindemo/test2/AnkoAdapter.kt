package com.mrwang.kotlindemo.release.test2

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.textColor

/**
 * @date 2018/5/30
 * @author chengwangyong
 */
class AnkoAdapter(val data: List<Int>?) : RecyclerView.Adapter<AnkoAdapter.AnkoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnkoViewHolder {
        return AnkoViewHolder(TextView(parent.context).apply {
            textSize = 20.0f
            textColor = Color.BLACK
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            gravity = Gravity.CENTER
        })
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: AnkoViewHolder, position: Int) {
        data?.let {
            holder.bind(it[position])
        }
    }

    class AnkoViewHolder(private var view: TextView) : RecyclerView.ViewHolder(view) {

        fun bind(name: Int) {
            view.text = name.toString()
        }
    }
}