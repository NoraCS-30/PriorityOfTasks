package com.example.priorityoftasks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        android.os.Handler().postDelayed({
            val intent = Intent(this,HomePage::class.java)
            startActivity(intent)
        },2000)
    }
}