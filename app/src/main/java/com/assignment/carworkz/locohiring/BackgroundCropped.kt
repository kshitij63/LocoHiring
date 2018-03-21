package com.assignment.carworkz.locohiring

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.Shader
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View

/**
 * Created by user on 3/20/2018.
 */

class BackgroundCropped : View {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        val rec_paint = Paint()
        rec_paint.color = resources.getColor(R.color.colorPrimary)
        val rect = Rect()
        rect.set(0, 0, canvas.width, canvas.height)
        canvas.drawRect(rect, rec_paint)


        val cir_paint = Paint()
        cir_paint.color = Color.TRANSPARENT
        cir_paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)


        val x = resources.getDimension(R.dimen.cropped_cir_width).toInt()
        val y = resources.getDimension(R.dimen.cropped_cir_height).toInt()
        val rad = resources.getDimension(R.dimen.crop_cir_radius).toInt()

        canvas.drawCircle(x.toFloat(), y.toFloat(), rad.toFloat(), cir_paint)


    }
}
