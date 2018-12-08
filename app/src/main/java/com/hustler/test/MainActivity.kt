package com.hustler.test

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this, "ca-app-pub-1788332764809455~5493699836")
        val adRequest=AdRequest.Builder().build()
        adView.loadAd(adRequest)
        adView.visibility= View.GONE

        adView.adListener = object : AdListener()
        {
            override fun onAdLoaded() {
                adView.visibility=View.VISIBLE
                super.onAdLoaded()
            }
        }


        val k=ContextCompat.checkSelfPermission(this,android.Manifest.permission.INTERNET)
        if(k!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.INTERNET),8)
        }


        val storageCode=7

        button.setOnClickListener{
            Toast.makeText(this," button was clicked",Toast.LENGTH_SHORT).show()
            val message : String = text1.text.toString()

            val intent = Intent( this,Main2Activity::class.java)
            intent.putExtra("displayText",message)
            startActivity(intent)
        }



       /* button3.setOnClickListener {

               val display = MusicListRequest().execute(this)

               //val disp = display.songs
               //text3.text = disp.toString()


        } */
        btnshare.setOnClickListener{
            val message : String = text1.text.toString()
            val intent = Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,message)
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"SEND TO :"))
        }

        btnview.setOnClickListener{
            val intent = Intent(this, Main3Activity::class.java)
            startActivity(intent)
        }



    }


}



