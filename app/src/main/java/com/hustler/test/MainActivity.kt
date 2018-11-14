package com.hustler.test

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            Toast.makeText(this," button was clicked",Toast.LENGTH_SHORT).show()
            val message : String = text1.text.toString()

            val intent = Intent( this,Main2Activity::class.java)
            intent.putExtra("displayText",message)
            startActivity(intent)
        }

    }


}
