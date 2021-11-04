package com.layon.doublelinkedlistlibrary

import android.content.Context
import android.text.InputFilter
import androidx.appcompat.widget.AppCompatEditText

class NodeEditText(context: Context) : AppCompatEditText(context)
     {

    var prev: NodeEditText? = null
    var next: NodeEditText? = null
    var index: Int? = null

    // extension function to set edit text maximum length
    fun setMaxLength(maxLength: Int){
        filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
    }

}