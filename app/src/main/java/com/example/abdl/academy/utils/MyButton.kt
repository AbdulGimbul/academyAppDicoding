package com.example.abdl.academy.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity.CENTER
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.example.abdl.academy.R

class MyButton : AppCompatButton {
    private var enableBackground: Drawable? = null
    private var disabledBackground: Drawable? = null
    private var textColour: Int = 0

    constructor(context: Context) : super (context){
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStylAttr: Int) : super(context, attrs, defStylAttr){
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        background = if (isEnabled) enableBackground else disabledBackground
        setTextColor(textColour)
        textSize = 12f
        gravity = CENTER
    }

    private fun init(){
        val resources = resources
        enableBackground = resources.getDrawable(R.drawable.bg_button)
        disabledBackground = resources.getDrawable(R.drawable.bg_disable)
        textColour = ContextCompat.getColor(context, android.R.color.background_light)
    }
}