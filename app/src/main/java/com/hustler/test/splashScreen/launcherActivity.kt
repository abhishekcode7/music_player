package com.hustler.test.splashScreen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.hustler.test.Main3Activity
import com.hustler.test.R

class launcherActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        Handler().postDelayed({
            val intent = Intent(this, Main3Activity::class.java)
            startActivity(intent)
        }, 2000)
    }



    }

