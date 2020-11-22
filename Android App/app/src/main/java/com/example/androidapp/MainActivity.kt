package com.example.androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openTemperatureButton = findViewById<ImageButton>(R.id.imageButton);
        val temperatureIconText = findViewById<TextView>(R.id.temperatureIconTextView)
        temperatureIconText.setText("Temperature App")
        openTemperatureButton.setOnClickListener(){
            val intent = Intent(this, TemperatureActivity::class.java).apply {}
            startActivity(intent)
        }
    }
}