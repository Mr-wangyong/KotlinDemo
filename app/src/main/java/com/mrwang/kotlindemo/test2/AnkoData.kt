package com.mrwang.kotlindemo.test2

/**
 * @date 2018/9/2
 * @author chengwangyong
 */

sealed class AnkoData{
    data class NormalData(var title: String, var subTitle: String):AnkoData()
    data class TitleData(var title: String):AnkoData()
}


