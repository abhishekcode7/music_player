package com.hustler.test

import android.content.Context
import android.media.MediaPlayer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_view.view.*

class Adapter(val context: Context, val sngs: List<Song>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    companion object {
        val mp by lazy { MediaPlayer() }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_view, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sngs.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val song = sngs[p1]
        p0.setData(song, p1)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(song: Song, pos: Int) {
            itemView.txvTitle.text = song.title

            itemView.txvTitle.setOnClickListener {
                mp.reset()
                mp.setDataSource(song.path)
                mp.setVolume(1F, 0F)
                mp.prepare()
                mp.start()

            }
        }
    }
}
