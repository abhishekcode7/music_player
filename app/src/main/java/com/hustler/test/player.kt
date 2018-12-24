package com.hustler.test

import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.hustler.test.Main3Activity.Companion.mp
import kotlinx.android.synthetic.main.list_view.view.*
import kotlinx.android.synthetic.main.onclick.*
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.concurrent.timerTask

class player : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onclick)

        val intentObject = intent
        val path = intentObject.getStringExtra("spath")
        val title = intentObject.getStringExtra("stitle")
        val dur = intentObject.getStringExtra("sduration")

        play1.setOnClickListener {
            mp.seekTo(mp.currentPosition - 5000)
        }
        play2.setOnClickListener {
            mp.seekTo(mp.currentPosition + 5000)
        }
        ppause.setOnClickListener {
            if (mp.isPlaying) {
                mp.pause()
                ppause.setImageResource(R.drawable.play)
            } else {
                mp.start()
                ppause.setImageResource(R.drawable.pause)
            }

        }

        ptitle.text = title

        try {

            val mmr = MediaMetadataRetriever()
            mmr.setDataSource(path)
            val data = mmr.embeddedPicture
            if (data != null) {
                val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)

                pimage.setImageBitmap(bitmap)
            } else {
                pimage.setImageResource(R.drawable.no)
            }
        } catch (e: IllegalArgumentException) {
            pimage.setImageResource(R.drawable.no)
        }
        updateseekbar(dur)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateseekbar(duration: String) {
        seekBar.progress=0
        pdur.text = duration
        seekBar.max = 100

        Timer().scheduleAtFixedRate(timerTask { runOnUiThread({ updatetimer() }) }, 0, 1000)
    }

    public fun updatetimer() {
        seekBar.post({ seekBar.progress = ((mp.currentPosition.toDouble() / mp.duration.toDouble()) * 100).toInt() })

    }
}
