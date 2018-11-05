package com.mrwang.kotlindemo.release.test1

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import com.mrwang.kotlindemo.R
import com.mrwang.kotlindemo.test1.JavaTest
import org.jetbrains.anko.find


/**
 * kotlin 空安全
 */
class MainActivity : AppCompatActivity() {

    private val iv by lazy(LazyThreadSafetyMode.NONE) {
        find<TextView>(R.id.text)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iv.text = "xxx"

        val person = object {
            var name:String?=null
        }

        person.apply {

        }

        val name= person.run {
            println("xxxx")
           "xxx"
        }


        JavaTest.getResult(object : JavaTest.HttpResult {
            override fun error(result: String?) {
                print("xxx")
            }

            override fun httpResult(result: String?) {
                print("xxx")
            }

        })

        toast("xxxx")

    }


    fun Context.toast(value:String) {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show()
    }


    fun Context.toast2(value:String) {
        Toast.makeText(this,value,Toast.LENGTH_LONG).show()
    }
}



