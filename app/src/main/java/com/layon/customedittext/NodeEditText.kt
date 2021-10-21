package com.layon.customedittext

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText

class NodeEditText(context: Context) : AppCompatEditText(context) ,
    TextWatcher {

    var prev: NodeEditText? = null
    var next: NodeEditText? = null

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        Log.d(TAG,"beforeTextChanged()")
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        Log.d(TAG,"onTextChanged(): $text start: $start lengthBefore: $lengthBefore lengthAfter: $lengthAfter")
        if(lengthAfter == 1){
            //TODO go to next
            Log.d(TAG,"go to next")
            next?.requestFocus()
        } else {
            //TODO go to prev
            Log.d(TAG,"go to prev")
            prev?.requestFocus()
        }
    }

    override fun afterTextChanged(s: Editable?) {
        Log.d(TAG,"afterTextChanged()")
    }

    // extension function to set edit text maximum length

    fun setMaxLength(maxLength: Int){
        filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
    }

}