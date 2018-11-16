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
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val storageCode=7

        button.setOnClickListener{
            Toast.makeText(this," button was clicked",Toast.LENGTH_SHORT).show()
            val message : String = text1.text.toString()

            val intent = Intent( this,Main2Activity::class.java)
            intent.putExtra("displayText",message)
            startActivity(intent)
        }
        val p = ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
        button2.setOnClickListener{

            if(p!= PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),storageCode)
            }


            else
            {

                Toast.makeText(this,"Permission already granted",Toast.LENGTH_SHORT).show()
            }
        }

        button3.setOnClickListener {
           if(p==PackageManager.PERMISSION_GRANTED) {
               val display = MusicListRequest().execute(this)

               val disp = display.songs
               text3.text = disp.toString()
           }
            else
           {
               Toast.makeText(this,"Read permission not granted",Toast.LENGTH_SHORT).show()
           }
        }
        btnshare.setOnClickListener{
            val message : String = text1.text.toString()
            val intent = Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,message)
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"SEND TO :"))
        }
    }


}



