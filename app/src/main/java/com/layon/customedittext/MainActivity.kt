package com.layon.customedittext

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    var TAG = "layon.f - MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val doubleLinkedListEditText: DoubleLinkedListEditText = findViewById(R.id.doubleLinkedListEditText)

        doubleLinkedListEditText.setCheckCodeCallback( object : CheckCodeCallback{
            override fun checkCode(code: String) {
                if (code == "123"){
                    Log.d(TAG, "checkCode success")
                    //do whatever you want
                } else {
                    Log.d(TAG, "checkCode fail")
                    doubleLinkedListEditText.enableFailLayout()
                }
            }
        })

    }

}