package com.layon.customedittext

import android.content.Context
import android.graphics.Color
import android.text.InputType
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import androidx.annotation.Dimension

var TAG = "layon.f - DoubleLinkedListEditText"

//TODO check the correct numbers
class DoubleLinkedListEditText(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {

    var size = 0
    var head : NodeEditText? = null
    var tail : NodeEditText? = null


    init {
        Log.d(TAG, "init()")
        orientation = HORIZONTAL
        layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

        context.theme.obtainStyledAttributes(attrs, R.styleable.DoubleLinkedListEditText, 0, 0)
            .apply {
                try {
                    val sizeElements = getInteger(R.styleable.DoubleLinkedListEditText_size_elements, 1)
                    val marginBetween = getDimensionPixelSize(R.styleable.DoubleLinkedListEditText_margin_between_elements, 10)
                    val (width, height) = Pair(
                        getDimensionPixelSize(R.styleable.DoubleLinkedListEditText_element_layout_weight, 100),
                        getDimensionPixelSize(R.styleable.DoubleLinkedListEditText_element_layout_height, 100)
                    )
                    createEditText(sizeElements, marginBetween, width, height)
                } finally {
                    recycle()
                }
            }
    }

    //create the custom editText and add inside this LinearLayout
    private fun createEditText(size: Int, margin: Int, width: Int, height: Int) {
        Log.d(TAG, "createEditText() sizeElements: $size marginBetween: $margin layout: $width, $height")
        repeat(size) {
            val node = NodeEditText(context)
            val params = LayoutParams(width, height)
            params.setMargins(margin, 0, margin, 0)
            node.layoutParams = params
            node.setBackgroundColor(Color.GREEN)
            node.hint = "$it"
            node.inputType = InputType.TYPE_CLASS_NUMBER //TODO pass this by attrs
            node.setMaxLength(1)
            addView(node)
            addInTail(node)
            Log.d(TAG, "add nodeEditText: $node")
        }
    }

    fun addInTail(node: NodeEditText) {
        Log.d(TAG,"\naddInTail()")
        if(size == 0) head = node
        node.next = null
        node.prev = tail
        var oldTail = tail
        tail = node
        if(oldTail != null) oldTail.next = tail
        this.size++
        print()
    }

    fun print(){
        if (size == 0) {
            Log.d(TAG, "list is empty")
            return
        }
        Log.d(TAG, "List: size = $size")
        var n = head
        while(n != null) {
            Log.d(TAG, "[${n.hint}]")
            n = n.next
        }
    }

}