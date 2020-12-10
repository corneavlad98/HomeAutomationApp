package com.example.androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openTemperatureButton = findViewById<ImageButton>(R.id.startTemperatureButton);
        val temperatureIconText = findViewById<TextView>(R.id.temperatureIconTextView)
        temperatureIconText.setText("Temperature App")

        openTemperatureButton.setOnClickListener(){
            val intent = Intent(this, TemperatureActivity::class.java).apply {}
            startActivity(intent)
        }

        val openCameraButton = findViewById<Button>(R.id.startCameraButton);
        openCameraButton.setOnClickListener(){

            val intent = packageManager.getLaunchIntentForPackage("com.owlr.controller.dlink")
            if (intent != null) {
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "There is no package available in android", Toast.LENGTH_LONG).show();
            }
        }
        val openZWaveButton = findViewById<Button>(R.id.startZWaveApp);
        openZWaveButton.setOnClickListener(){

            val intent = packageManager.getLaunchIntentForPackage("de.pathec.hubapp")
            if (intent != null) {
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "There is no package available in android", Toast.LENGTH_LONG).show();
            }
        }
    }
}