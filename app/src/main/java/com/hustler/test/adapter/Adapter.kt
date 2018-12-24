package com.hustler.test.adapter

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.graphics.drawable.RoundedBitmapDrawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.hustler.test.Main3Activity
import com.hustler.test.Main3Activity.Companion.mp
import com.hustler.test.R


import com.hustler.test.Song
import com.hustler.test.player

import kotlinx.android.synthetic.main.activity_main3.view.*

import kotlinx.android.synthetic.main.list_view.view.*
import java.lang.IllegalArgumentException

class Adapter(val context: Context, val sngs: List<Song>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    val activity = context as Main3Activity

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_view, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sngs.size
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val song = sngs[p1]
        p0.setData(song, p1)


    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @RequiresApi(Build.VERSION_CODES.N)
        fun setData(song: Song, pos: Int) {


                itemView.txvTitle.text = song.title
            itemView.duration.text= convert(song.duration.toLong())
           try {

               val mmr = MediaMetadataRetriever()
               mmr.setDataSource(song.path)
               val data = mmr.embeddedPicture
               if(data!=null) {
                   val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)

                   itemView.img.setImageBitmap(bitmap)
               }

                else {
                   itemView.img.setImageResource(R.drawable.no)
               }
           } catch (e:IllegalArgumentException){
               itemView.img.setImageResource(R.drawable.no)
           }
                //itemView.dur.text=song.duration

                itemView.txvTitle.setOnClickListener {

                    itemView.startAnimation(AnimationUtils.loadAnimation(context,R.anim.blink_anim))
                mp.reset()

                mp.setDataSource(song.path)
                mp.setVolume(10F, 10F)
                mp.prepare()
                    activity.openplayer(song.path,song.title,convert(song.duration.toLong()))

                    mp.start()


                }



            }
        private fun convert (  dur : Long):String{
            val minutes :Long =(dur/1000)/60
            val seconds:Long =(dur/1000)%60
            val finalString:String
            finalString=String.format("%d:%02d",minutes,seconds)
            return finalString
        }







        }


    }

