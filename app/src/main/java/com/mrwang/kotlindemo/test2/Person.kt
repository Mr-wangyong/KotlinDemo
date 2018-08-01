package com.mrwang.kotlindemo.test2

import kotlin.properties.Delegates

/**
 * @date 2018/7/22
 * @author chengwangyong
 */
class Person(){

    var name:String by Delegates.observable(""){property, oldValue, newValue ->

    }

}