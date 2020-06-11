package com.example.preat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Btnsignup = findViewById<View>(R.id.Btnsignup) as Button
        val Btnsignin =  findViewById<View>(R.id.Btnsignin) as Button

        Btnsignup.setOnClickListener(View.OnClickListener {
            signup()
        })
        Btnsignin.setOnClickListener(View.OnClickListener {
            signin()
        })
    }

    private fun signup(){
        startActivity(Intent(this,register::class.java))
    }

    private fun signin(){
        startActivity(Intent(this,Signin::class.java))
    }

}
