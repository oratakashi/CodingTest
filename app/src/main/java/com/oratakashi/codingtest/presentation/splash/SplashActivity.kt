package com.oratakashi.codingtest.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.oratakashi.codingtest.databinding.ActivitySplashBinding
import com.oratakashi.codingtest.presentation.main.MainActivity
import com.oratakashi.viewbinding.core.binding.activity.viewBinding
import com.oratakashi.viewbinding.core.tools.startActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(MainActivity::class.java)
                finish()
            }, 3000)
        }
    }

    private val binding: ActivitySplashBinding by viewBinding()
}