package com.example.androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    var database = FirebaseDatabase.getInstance()
    var myDbReference = database.getReference()
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
    override fun onBackPressed() {
        super.onBackPressed()
        //reset LEDs when pressing backbutton from MainActivity
        myDbReference.child("RaspberryPi/LED/LED1").setValue(0)
        myDbReference.child("RaspberryPi/LED/LED2").setValue(0)
    }
    override fun onDestroy() {
        super.onDestroy()
        //reset LEDs when destroying (closing) MainActivity
        myDbReference.child("RaspberryPi/LED/LED1").setValue(0)
        myDbReference.child("RaspberryPi/LED/LED2").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(0)
    }
}