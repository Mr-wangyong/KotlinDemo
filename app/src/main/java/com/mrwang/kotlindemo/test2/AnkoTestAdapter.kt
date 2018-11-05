package com.mrwang.kotlindemo.test2

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.textColor

/**
 * @date 2018/9/2
 * @author chengwangyong
 */

class AnkoTestAdapter(val data: List<AnkoData>?) : RecyclerView.Adapter<AnkoTestAdapter.AnkoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnkoTestAdapter.AnkoViewHolder {
        return when (viewType) {
            1 -> {
                AnkoViewHolder.TitleViewHolder(TextView(parent.context).apply {
                    textSize = 20.0f
                    textColor = Color.BLACK
                    layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    gravity = Gravity.CENTER
                })
            }

            else -> {
                AnkoViewHolder.NormalViewHolder(NormalCustomView(parent.context))
            }
        }

    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }


    override fun onBindViewHolder(holder: AnkoTestAdapter.AnkoViewHolder, position: Int) {
        data?.let {
            when (holder) {
                is AnkoViewHolder.NormalViewHolder -> {
                    holder.bind(data[position] as? AnkoData.NormalData)
                }
                is AnkoViewHolder.TitleViewHolder -> {
                    holder.bind(data[position] as? AnkoData.TitleData)
                }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == 0) return 1 else 0
    }

    sealed class AnkoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        class NormalViewHolder(private var view: NormalCustomView) : AnkoViewHolder(view) {

            fun bind(data: AnkoData.NormalData?) {
                view.setData(data?.title, data?.subTitle)
            }
        }

        class TitleViewHolder(private var view: TextView) : AnkoViewHolder(view) {

            fun bind(data: AnkoData.TitleData?) {
                view.text = data?.title
            }
        }
    }

}