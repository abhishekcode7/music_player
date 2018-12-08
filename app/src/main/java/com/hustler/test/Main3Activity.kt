package com.hustler.test

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main3.*

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerview.layoutManager = layoutManager

        loadList()
    }

    private fun loadList() {
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            val adapter = Adapter(this, MusicListRequest().execute(this))
            recyclerview.adapter = adapter
        }else {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE),8)
        }
    }

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
}
