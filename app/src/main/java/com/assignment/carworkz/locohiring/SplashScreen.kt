package com.assignment.carworkz.locohiring

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        var handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                var i = Intent(this@SplashScreen, MainActivity::class.java);
                startActivity(i);

                finish();

            }
        }, 5000
        )

    }
}
