package com.example.androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

import com.google.firebase.database.FirebaseDatabase

class LEDActivity : AppCompatActivity(){
    var database = FirebaseDatabase.getInstance()
    var myDbReference = database.getReference()
    private fun resetSwitches(){
        myDbReference.child("RaspberryPi/LED/LED1").setValue(0)
        myDbReference.child("RaspberryPi/LED/LED2").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l_e_d)
        resetSwitches()

        Toast.makeText(this, "Make sure that the LED script is running!", Toast.LENGTH_LONG).show();

        val ledSwitch1 = findViewById<Switch>(R.id.ledSwitch1);
        val ledSwitch2 = findViewById<Switch>(R.id.ledSwitch2);

        val rgbLedRedSwitch = findViewById<Switch>(R.id.redSwitch)
        val rgbLedGreenSwitch = findViewById<Switch>(R.id.greenSwitch)
        val rgbLedBlueSwitch = findViewById<Switch>(R.id.blueSwitch)

        //click listeners to change Database value of normal led switches
        ledSwitch1.setOnClickListener(){
            if(ledSwitch1.isChecked)
                myDbReference.child("RaspberryPi/LED/LED1").setValue(1)
            else
                myDbReference.child("RaspberryPi/LED/LED1").setValue(0)
        }
        ledSwitch2.setOnClickListener(){
            if(ledSwitch2.isChecked)
                myDbReference.child("RaspberryPi/LED/LED2").setValue(1)
            else
                myDbReference.child("RaspberryPi/LED/LED2").setValue(0)
        }

        //click listeners to change Database value of rgb led switches
        rgbLedRedSwitch.setOnClickListener(){
            if(rgbLedRedSwitch.isChecked)
                myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(1)
            else
                myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(0)
        }
        rgbLedGreenSwitch.setOnClickListener(){
            if(rgbLedGreenSwitch.isChecked)
                myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(1)
            else
                myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(0)
        }
        rgbLedBlueSwitch.setOnClickListener(){
            if(rgbLedBlueSwitch.isChecked)
                myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(1)
            else
                myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(0)
        }

        val returnButton = findViewById<Button>(R.id.returnButtonLED)
        returnButton.setOnClickListener(){
            finish();
        }
    }


}