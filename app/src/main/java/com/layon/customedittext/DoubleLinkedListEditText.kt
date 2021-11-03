package com.layon.customedittext

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout


var TAG = "layon.f - DoubleLinkedListEditText"

//TODO remove the manually focus when user touch in a edittext between the first and last
class DoubleLinkedListEditText(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {

    private var size = 0
    private var head : NodeEditText? = null
    private var tail : NodeEditText? = null
    private var inputCode : StringBuilder
    private var count : Int = 0
    private lateinit var callback : CheckCodeCallback

    init {
        Log.d(TAG, "init()")
        inputCode = StringBuilder()
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
                    selectFirstAutomatic()
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
            node.gravity = Gravity.CENTER
            node.setBackgroundResource(R.drawable.custom_background)
            node.inputType = InputType.TYPE_CLASS_NUMBER //TODO pass this by attrs
            node.setMaxLength(1)
            node.index = it
            inputCode.append("*")
            setTextWatcher(node)
            addView(node)
            addInTail(node)
            Log.d(TAG, "add nodeEditText: $node")
        }
    }

    //TODO: execute only if this flag is true
    private fun selectFirstAutomatic(){
        head?.requestFocus()
        //TODO: open keyboard automatically is not working
        //val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //inputMethodManager?.showSoftInput(head, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun addInTail(node: NodeEditText) {
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

    fun setErrorBackground(){
        var n = head
        while(n != null) {
            n.setBackgroundResource(R.drawable.custom_background_error)
            n = n.next
        }
    }

    fun setSuccessBackground(){
        var n = head
        while(n != null) {
            n.setBackgroundResource(R.drawable.custom_background_success)
            n = n.next
        }
    }

    fun setCheckCodeCallback(c : CheckCodeCallback){
        callback = c
    }

    private fun checkCode(){
        if(this::callback.isInitialized){
            callback.checkCode(inputCode.toString())
        }
    }

    private fun setTextWatcher(node: NodeEditText){
        Log.d(TAG, "adding TextWatcher")
        node.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                Log.d(TAG,"afterTextChanged()")
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                Log.d(TAG,"beforeTextChanged()")
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, after: Int) {
                Log.d(TAG,"onTextChanged(): $s start: $start before: $before after: $after")
                if(after == 1){
                    Log.d(TAG,"go to next")
                    inputCode[node.index!!] = s[0]
                    Log.d(TAG,"inputCode: $inputCode")
                    Log.d(TAG,"count: $count == size: $size")
                    count++
                    if(count == size){
                        Log.d(TAG, "check the code")
                        checkCode()
                    } else {
                        node.next?.requestFocus()
                        node.setBackgroundResource(R.drawable.custom_background)
                    }
                } else {
                    Log.d(TAG,"go to prev")
                    inputCode[node.index!!] = '*'
                    Log.d(TAG,"inputCode: $inputCode")
                    node.prev?.requestFocus()
                    node.setBackgroundResource(R.drawable.custom_background)
                    count--
                }
            }
        })
    }

    private fun print(){
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