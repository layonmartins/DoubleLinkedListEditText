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
        createEditText(3)
    }

    //create the custons editText and add inside this LinearLayout
    private fun createEditText(size: Int) {
        repeat(size) {
            val edit = NodeEditText(this)
            edit.layoutParams = LinearLayout.LayoutParams(200, 200)
            edit.setBackgroundColor(Color.GREEN)
            edit.hint = "0"
            val linearLayout : LinearLayout = findViewById(R.id.my_linear_layout)
            linearLayout.addView(edit)
            Log.d(TAG, "add nodeEditText: $edit")
        }
    }
}