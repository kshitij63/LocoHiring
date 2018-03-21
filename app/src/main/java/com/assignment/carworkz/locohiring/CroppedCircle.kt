package com.assignment.carworkz.locohiring

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View

/**
 * Created by user on 3/19/2018.
 */

class CroppedCircle : View {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val Cirpaint = Paint()
        Cirpaint.color = Color.TRANSPARENT
        Cirpaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)


        val x = resources.getDimension(R.dimen.cropped_cir_width).toInt()
        val y = resources.getDimension(R.dimen.cropped_cir_height).toInt()
        val rad = resources.getDimension(R.dimen.crop_cir_radius).toInt()


        canvas.drawCircle(x.toFloat(), y.toFloat(), rad.toFloat(), Cirpaint)


    }
}
