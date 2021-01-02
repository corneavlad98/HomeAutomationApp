package com.example.androidapp

import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase


class LEDActivity : AppCompatActivity(){
    var database = FirebaseDatabase.getInstance()
    var myDbReference = database.getReference()

    private fun resetValues(){
        myDbReference.child("RaspberryPi/LED/LED1").setValue(0)
        myDbReference.child("RaspberryPi/LED/LED2").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l_e_d)
        resetValues()

        Toast.makeText(this, "Make sure that the LED script is running!", Toast.LENGTH_LONG).show();
        val ledImageButton1 = findViewById<ImageButton>(R.id.normalLed1)
        val ledImageButton2 = findViewById<ImageButton>(R.id.normalLed2)

        val redButton = findViewById<Button>(R.id.redButton)
        val greenButton = findViewById<Button>(R.id.greenButton)
        val blueButton = findViewById<Button>(R.id.blueButton)
        val magentaButton = findViewById<Button>(R.id.magentaButton)
        val cyanButton = findViewById<Button>(R.id.cyanButton)
        val yellowButton = findViewById<Button>(R.id.yellowButton)
        val whiteButton = findViewById<Button>(R.id.whiteButton)
        val offButton = findViewById<Button>(R.id.offButton)
        val discoButton = findViewById<Button>(R.id.discoButton)

        var pressedOnce1 = false;

        //click listeners to change Database value of normal led switches
        ledImageButton1.setOnClickListener(){
            val drawable = ledImageButton1.drawable as TransitionDrawable

            if(!pressedOnce1)
            {
                myDbReference.child("RaspberryPi/LED/LED1").setValue(1)
                drawable.startTransition(800);
                pressedOnce1 = true;
            }
            else
            {
                myDbReference.child("RaspberryPi/LED/LED1").setValue(0)
                drawable.reverseTransition(800);
                pressedOnce1 = false;
            }
        }

        var pressedOnce2 = false;
        ledImageButton2.setOnClickListener(){
            val drawable = ledImageButton2.drawable as TransitionDrawable
            if(!pressedOnce2)
            {
                myDbReference.child("RaspberryPi/LED/LED2").setValue(1)
                drawable.startTransition(800);
                pressedOnce2 = true;
            }
            else
            {
                myDbReference.child("RaspberryPi/LED/LED2").setValue(0)
                drawable.reverseTransition(800);
                pressedOnce2 = false;
            }
        }

        //color buttons for the RGB LED
        redButton.setOnClickListener(){
            redSet();
        }
        greenButton.setOnClickListener(){
            greenSet();
        }
        blueButton.setOnClickListener(){
            blueSet();
        }
        magentaButton.setOnClickListener(){
            magentaSet()
        }
        cyanButton.setOnClickListener(){
            cyanSet()
        }
        yellowButton.setOnClickListener(){
            yellowSet()
        }
        whiteButton.setOnClickListener(){
            whiteSet()
        }
        offButton.setOnClickListener(){
            offSet()
        }
        discoButton.setOnClickListener(){
            discoSet()
        }

        val returnButton = findViewById<Button>(R.id.returnButtonLED)
        returnButton.setOnClickListener(){
            finish();
        }
    }
    private fun redSet()
    {
        myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(1)
        myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)

    }
    private fun greenSet()
    {
        myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(1)
        myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)

    }
    private fun blueSet()
    {
        myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(1)
        myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)

    }
    private fun magentaSet()
    {
        myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(1)
        myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(1)
        myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)

    }
    private fun cyanSet()
    {
        myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(1)
        myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(1)
        myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)

    }
    private fun yellowSet()
    {
        myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(1)
        myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(1)
        myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)

    }
    private fun whiteSet()
    {
        myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(1)
        myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(1)
        myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(1)
        myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)

    }
    private fun offSet()
    {
        myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)

    }
    private fun discoSet()
    {
        myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(1)
    }


}