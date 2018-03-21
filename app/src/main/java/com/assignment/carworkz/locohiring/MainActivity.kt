package com.assignment.carworkz.locohiring

import android.animation.Animator
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_main.*

import java.util.Random

class MainActivity : AppCompatActivity() {
/*
    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, cover_relative_layout2: Boolean) {
        if (!cover_relative_layout2) {
            p1?.loadVideo(Config.videoString)
            p1?.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS)
        }
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        Toast.makeText(this, "ERROR ${p1.toString()}", Toast.LENGTH_SHORT).show()
    }
*/

    var cover_relative_layout: RelativeLayout.LayoutParams? = null
    var cover_relative_layout2: RelativeLayout.LayoutParams? = null
    var cardParam: FrameLayout.LayoutParams? = null
    var position = 0;

    var flag = true
    var end = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        videoView?.initialize(Config.API_KEY, this)


        video_view?.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.videoplayback))
        video_view?.start()


        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (!end)
                    Collapse_Video()
                handler.postDelayed(this, 5000)
            }
        }, 5000)

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    fun Collapse_Video() {

        if (flag) {

            removeLayout()


            SetCard()
            if (parent_view != null)
                reveal_animation()
            flag = false
        } else {

            showVideo()
            if (back_crop != null)
                hide_animation()

            flag = true

        }

    }

    fun SetCard() {

        cardParam = FrameLayout.LayoutParams(card_view?.layoutParams)

        val card_height_min = resources.getDimension(R.dimen.card_min_height).toInt()
        val card_height_max = resources.getDimension(R.dimen.card_max_height).toInt()
        val rand = Random()
        val card_height = rand.nextInt(card_height_max) + card_height_min

        cardParam?.height = pxToDp(card_height)
        cardParam?.width = FrameLayout.LayoutParams.MATCH_PARENT
        cardParam?.topMargin = resources.getDimension(R.dimen.card_top_margin).toInt()
        cardParam?.leftMargin = resources.getDimension(R.dimen.card_left_margin).toInt()
        cardParam?.rightMargin = resources.getDimension(R.dimen.card_right_margin).toInt()
        card_view?.layoutParams = cardParam
    }

    fun pxToDp(px: Int): Int {
        val displayMetrics = this.resources.displayMetrics

        return px / (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    fun reveal_animation() {

        val cx = parent_view?.measuredWidth!! / 2
        val cy = parent_view?.measuredHeight!! / 2
        val startRadius = 0
        val endRadius = Math.max(parent_view?.width!!, parent_view?.height!!)

        val anim = ViewAnimationUtils
                .createCircularReveal(parent_view, cx, cy, startRadius.toFloat()
                        , endRadius.toFloat())
        anim.duration = 500


        video_view?.layoutParams = cover_relative_layout
        back_crop?.setVisibility(View.VISIBLE)
        cropped?.visibility = View.VISIBLE
        anim.start()
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun hide_animation() {

        val centerX = resources.getDimension(R.dimen.cropped_cir_width).toInt()
        val centerY = resources.getDimension(R.dimen.cropped_cir_height).toInt()

        val startRadius = Math.max(parent_view?.getWidth()!!, parent_view?.getHeight()!!)
        val endRadius = resources.getDimension(R.dimen.crop_cir_radius).toInt()

        val anim1 = ViewAnimationUtils.createCircularReveal(parent_view, centerX, centerY, startRadius.toFloat()
                , endRadius.toFloat())
        anim1.duration = 500

        anim1.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {

            }

            override fun onAnimationEnd(animator: Animator) {
                back_crop?.setVisibility(View.GONE)

            }

            override fun onAnimationCancel(animator: Animator) {

            }

            override fun onAnimationRepeat(animator: Animator) {

            }
        })
        cropped?.visibility = View.GONE
        video_view?.layoutParams = cover_relative_layout2
        anim1.start()


    }

    override fun onBackPressed() {
        super.onBackPressed()
        end = true

    }

    fun removeLayout() {
        cover_relative_layout = RelativeLayout.LayoutParams(video_view?.layoutParams)
        cover_relative_layout?.width = resources.getDimension(R.dimen.vid_width).toInt()
        cover_relative_layout?.height = resources.getDimension(R.dimen.vid_height).toInt()
        cover_relative_layout?.topMargin = resources.getDimension(R.dimen.margin_top).toInt()
        cover_relative_layout?.removeRule(RelativeLayout.CENTER_IN_PARENT)
        cover_relative_layout?.addRule(RelativeLayout.CENTER_HORIZONTAL)


    }

    fun showVideo() {
        cover_relative_layout2 = RelativeLayout.LayoutParams(video_view?.layoutParams)
        cover_relative_layout2?.width = ViewGroup.LayoutParams.MATCH_PARENT
        cover_relative_layout2?.height = ViewGroup.LayoutParams.MATCH_PARENT
        cover_relative_layout2?.addRule(RelativeLayout.CENTER_IN_PARENT)
    }

    override fun onPause() {
        super.onPause()
        position = video_view.currentPosition
        video_view.pause()

    }

    override fun onResume() {
        super.onResume()
        if (position != 0)
            video_view.seekTo(position)
        video_view.start()
    }
}
