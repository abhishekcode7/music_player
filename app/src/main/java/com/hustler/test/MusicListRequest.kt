package com.hustler.test



import android.content.Context
import android.provider.MediaStore


import java.time.Duration
import kotlin.coroutines.coroutineContext



data class MusicList(val songs:ArrayList<Song>)
data class Song(val artist:String,val title:String,val id:String,val duration:String,val path:String)
class MusicListRequest {
    fun execute (context : Context) : MusicList {
        val uriExternal = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DATA
        )


        val cur = context.contentResolver.query(uriExternal, projection, null, null, MediaStore.Audio.Media._ID)

        val songList: ArrayList<Song> = ArrayList()

            if (cur != null) {
                while (cur.moveToNext()) {
                    val artistC = cur.getColumnIndex(MediaStore.Audio.Media.ARTIST)
                    val titleC = cur.getColumnIndex(MediaStore.Audio.Media.TITLE)
                    val idC = cur.getColumnIndex(MediaStore.Audio.Media._ID)
                    val pathC = cur.getColumnIndex(MediaStore.Audio.Media.DATA)
                    val durationC = cur.getColumnIndex(MediaStore.Audio.Media.DURATION)

                    val song = Song(
                        cur.getString(artistC),
                        cur.getString(titleC),
                        cur.getString(idC),
                        cur.getString(durationC),
                        cur.getString(pathC)
                    )

                    songList.add(song)

                }
            }
        cur.close()
    val result = MusicList(songList)
    return result
    }
}