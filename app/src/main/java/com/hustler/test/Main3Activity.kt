package com.hustler.test

import android.app.Application
import android.app.ApplicationErrorReport
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.BottomSheetDialog
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.SeekBar
import android.widget.Toast
import com.hustler.test.adapter.Adapter
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.android.synthetic.main.list_view.*
import kotlinx.android.synthetic.main.onclick.*
import java.util.*
import java.util.concurrent.Delayed
import kotlin.concurrent.timerTask


class Main3Activity : AppCompatActivity() {

    companion object {
        val mp by lazy { MediaPlayer() }
    }

    val i =Intent(this,player::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)


        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerview.layoutManager = layoutManager

        loadList()

        val navigationView =findViewById<View>(R.id.navigation) as BottomNavigationView
        navigationView.setOnNavigationItemSelectedListener { item->
            when(item.itemId)
            {
              R.id.playing->{
                    startActivity(i)
                  return@setOnNavigationItemSelectedListener true
              }
                else->return@setOnNavigationItemSelectedListener true
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadList() {
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            val adapter = Adapter(this, MusicListRequest().execute(this))
            recyclerview.adapter = adapter

        }else {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE),8)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)


        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {

            val builder = AlertDialog.Builder(this)
                .setTitle("Grant Permissions")
                .setMessage("Please grant storage permissions for app to work")
                .setPositiveButton("OK") { _, _ -> loadList()}
                .setNegativeButton("Cancel") {_, _ -> finish()}

            builder.show()

        }else loadList()
    }
    private fun convert (  dur : Long):String{
        val minutes :Long =(dur/1000)/60
        val seconds:Long =(dur/1000)%60
        val finalString:String
        finalString=String.format("%d:%02d",minutes,seconds)
        return finalString
    }

 /*   @RequiresApi(Build.VERSION_CODES.N)
    public fun updateseekbar() {
        seekBar.setProgress(mp.currentPosition,true)
        dur.text = convert(mp.duration.toLong())
        seekBar.max = 100

        Timer().scheduleAtFixedRate(timerTask { runOnUiThread({updatetimer()})},0,1000)
    }

    public fun updatetimer(){
        seekBar.post({ seekBar.progress = ((mp.currentPosition.toDouble() / mp.duration.toDouble()) * 100).toInt() })
    } */

   /* public fun pop(){
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.onclick,null)
        dialog.setContentView(view)
        dialog.show()
    }*/
    public fun openplayer( spath:String,stitle :String,sduration:String)
   {
       i.putExtra("spath",spath)
       i.putExtra("stitle",stitle)
       i.putExtra("sduration",sduration)
       startActivity(i)

   }


}









