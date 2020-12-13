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

        //Temperature Activity start
        val openTemperatureButton = findViewById<ImageButton>(R.id.startTemperatureButton);
        openTemperatureButton.setOnClickListener(){
            val intent = Intent(this, TemperatureActivity::class.java).apply {}
            startActivity(intent)
        }
        //LED Activity start
        val openLEDButton = findViewById<ImageButton>(R.id.startLEDButton);
        openLEDButton.setOnClickListener(){
            val intent = Intent(this, LEDActivity::class.java).apply {}
            startActivity(intent)
        }
        //Camera App start
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
        //ZWave App start
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