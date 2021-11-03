package com.layon.customedittext

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    var TAG = "layon.f - MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val doubleLinkedListEditText: DoubleLinkedListEditText = findViewById(R.id.doubleLinkedListEditText)

        doubleLinkedListEditText.setCheckCodeCallback( object : CheckCodeCallback{
            override fun checkCode(code: String) {
                if (code == "12345"){
                    Log.d(TAG, "checkCode success")
                    doubleLinkedListEditText.setSuccessBackground()
                    //do whatever you want
                } else {
                    Log.d(TAG, "checkCode fail")
                    doubleLinkedListEditText.setErrorBackground()
                }
            }
        })

    }

}