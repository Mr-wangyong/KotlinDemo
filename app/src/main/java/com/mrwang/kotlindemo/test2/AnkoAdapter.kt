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
        return when (viewType) {
            1 -> {
                AnkoViewHolder.NormalViewHolder(TextView(parent.context).apply {
                    textSize = 20.0f
                    textColor = Color.BLACK
                    layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    gravity = Gravity.CENTER
                })
            }
            else -> {
                AnkoViewHolder.TitleViewHolder(TextView(parent.context).apply {
                    textSize = 20.0f
                    textColor = Color.BLACK
                    layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    gravity = Gravity.CENTER
                })
            }
        }

    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: AnkoViewHolder, position: Int) {
        data?.let {
            when (holder) {
                is AnkoViewHolder.NormalViewHolder -> {
                    holder.bind(data[position])
                }
                is AnkoViewHolder.TitleViewHolder -> {
                    holder.bind(data[position])
                }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == 0) return 1 else 0
    }

    sealed class AnkoViewHolder(view: TextView) : RecyclerView.ViewHolder(view) {

        class NormalViewHolder(private var view: TextView) : AnkoViewHolder(view) {

            fun bind(name: Int) {
                view.text = name.toString()
            }
        }

        class TitleViewHolder(private var view: TextView) : AnkoViewHolder(view) {

            fun bind(name: Int) {
                view.text = name.toString()
            }
        }
    }

}