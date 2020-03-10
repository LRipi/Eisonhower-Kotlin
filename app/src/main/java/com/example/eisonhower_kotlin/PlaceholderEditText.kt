package com.example.eisonhower_kotlin

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

class PlaceholderEditText : TextInputEditText {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val placeholder = hint

    init {
        hint = ""
        onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                postDelayed({ hint = placeholder }, 200)
            } else {
                hint = ""
            }
        }
    }
}