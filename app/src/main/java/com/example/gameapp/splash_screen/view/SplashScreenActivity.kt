package com.example.gameapp.splash_screen.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.gameapp.core.base.BaseActivity
import com.example.gameapp.databinding.ActivitySplashScreenBinding
import com.example.gameapp.feature.main.view.GamesActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {
    override fun getViewBinding(): ActivitySplashScreenBinding {
        return ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        Handler().postDelayed({
            val intent = Intent(this, GamesActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}