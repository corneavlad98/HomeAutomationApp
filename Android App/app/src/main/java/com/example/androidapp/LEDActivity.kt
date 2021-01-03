package com.example.androidapp

import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class LEDActivity : AppCompatActivity(){
    var database = FirebaseDatabase.getInstance()
    var myDbReference = database.getReference()
    private fun resetValues(){
        myDbReference.child("RaspberryPi/LED/LED1").setValue(0)
        myDbReference.child("RaspberryPi/LED/LED2").setValue(0)

        myDbReference.child("RaspberryPi/LED/StartNormal1").setValue(0)
        myDbReference.child("RaspberryPi/LED/StartNormal2").setValue(0)

        myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)

        myDbReference.child("RaspberryPi/LED/rgbLED/StartRed").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/StartGreen").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/StartBlue").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/StartLed").setValue(0)
    }
    private fun resetRGBValues(){
        myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)

        myDbReference.child("RaspberryPi/LED/rgbLED/StartRed").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/StartGreen").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/StartBlue").setValue(0)
        myDbReference.child("RaspberryPi/LED/rgbLED/StartLed").setValue(0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l_e_d)
        resetValues()

        Toast.makeText(this, "Make sure that the LED script is running!", Toast.LENGTH_LONG).show();
        val ledImageButton1 = findViewById<ImageButton>(R.id.normalLed1)
        val ledImageButton2 = findViewById<ImageButton>(R.id.normalLed2)
        val rgbLed = findViewById<ImageView>(R.id.rgbLed)

        //initialization of LED pictures
        ledImageButton1.setImageResource(R.drawable.normal_led)
        ledImageButton2.setImageResource(R.drawable.normal_led)
        rgbLed.setImageResource(R.drawable.normal_led)

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
            if(!pressedOnce1)
            {
                myDbReference.child("RaspberryPi/LED/LED1").setValue(1)

                val valueListener = object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val led1Value = snapshot.child("RaspberryPi/LED/LED1").value
                        val ledStart = snapshot.child("RaspberryPi/LED/StartNormal1").value

                        if (led1Value != null && ledStart != null)
                        {
                            if(led1Value.toString().equals("1") && ledStart.toString().equals("1"))
                            {
                                ledImageButton1.setImageResource(R.drawable.normal_led_on)
                            }
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {

                    }
                }
                myDbReference.addValueEventListener(valueListener);
                pressedOnce1 = true;
            }
            else
            {
                myDbReference.child("RaspberryPi/LED/LED1").setValue(0)

                val valueListener = object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val led1Value = snapshot.child("RaspberryPi/LED/LED1").value
                        val ledStart = snapshot.child("RaspberryPi/LED/StartNormal1").value

                        if (led1Value != null && ledStart != null)
                        {
                            if(led1Value.toString().equals("0") && ledStart.toString().equals("0"))
                            {
                                ledImageButton1.setImageResource(R.drawable.normal_led_off)
                            }
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {

                    }
                }
                myDbReference.addValueEventListener(valueListener);
                pressedOnce1 = false;
            }
        }

        var pressedOnce2 = false;
        ledImageButton2.setOnClickListener(){
            if(!pressedOnce2)
            {
                myDbReference.child("RaspberryPi/LED/LED2").setValue(1)

                val valueListener = object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val led2Value = snapshot.child("RaspberryPi/LED/LED2").value
                        val ledStart = snapshot.child("RaspberryPi/LED/StartNormal2").value

                        if (led2Value != null && ledStart != null)
                        {
                            if(led2Value.toString().equals("1") && ledStart.toString().equals("1"))
                            {
                                ledImageButton2.setImageResource(R.drawable.normal_led_on)
                            }
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {

                    }
                }
                myDbReference.addValueEventListener(valueListener);
                pressedOnce2 = true;
            }
            else
            {
                myDbReference.child("RaspberryPi/LED/LED2").setValue(0)

                val valueListener = object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val led2Value = snapshot.child("RaspberryPi/LED/LED2").value
                        val ledStart = snapshot.child("RaspberryPi/LED/StartNormal2").value

                        if (led2Value != null && ledStart != null)
                        {
                            if(led2Value.toString().equals("0") && ledStart.toString().equals("0"))
                            {
                                ledImageButton2.setImageResource(R.drawable.normal_led_off)
                            }
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {

                    }
                }
                myDbReference.addValueEventListener(valueListener);
                pressedOnce2 = false;
            }
        }


        //color buttons for the RGB LED
        //RED COLOR
        redButton.setOnClickListener(){
            resetRGBValues()
            myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(1)
            myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(0)
            myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(0)
            myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)
            val valueListener = object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val redStart = snapshot.child("RaspberryPi/LED/rgbLED/StartRed").value
                    if (redStart != null)
                    {
                        if(redStart.toString().equals("1"))
                        {
                            rgbLed.setImageResource(R.drawable.rgb_led_red)
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }
            }
            myDbReference.addValueEventListener(valueListener);
        }

        //GREEN COLOR
        greenButton.setOnClickListener(){
            resetRGBValues()
            myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(0)
            myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(1)
            myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(0)
            myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)

            val valueListener = object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val greenStart = snapshot.child("RaspberryPi/LED/rgbLED/StartGreen").value
                    if (greenStart != null)
                    {
                        if(greenStart.toString().equals("1"))
                        {
                            rgbLed.setImageResource(R.drawable.rgb_led_green)
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }
            }
            myDbReference.addValueEventListener(valueListener);
        }
        //BLUE COLOR
        blueButton.setOnClickListener(){
            resetRGBValues()
            myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(0)
            myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(0)
            myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(1)
            myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)

            val valueListener = object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val blueStart = snapshot.child("RaspberryPi/LED/rgbLED/StartBlue").value
                    if (blueStart != null)
                    {
                        if(blueStart.toString().equals("1"))
                        {
                            rgbLed.setImageResource(R.drawable.rgb_led_blue)
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }
            }
            myDbReference.addValueEventListener(valueListener);
        }
        //MAGENTA COLOR
        magentaButton.setOnClickListener(){
            resetRGBValues()
            myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(1)
            myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(0)
            myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(1)
            myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)

            val valueListener = object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val redStart = snapshot.child("RaspberryPi/LED/rgbLED/StartRed").value
                    val blueStart = snapshot.child("RaspberryPi/LED/rgbLED/StartBlue").value
                    if (blueStart != null && redStart != null)
                    {
                        if(redStart.toString().equals("1") && blueStart.toString().equals("1"))
                        {
                            rgbLed.setImageResource(R.drawable.rgb_led_magenta)
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }
            }
            myDbReference.addValueEventListener(valueListener);
        }
        //CYAN COLOR
        cyanButton.setOnClickListener(){
            resetRGBValues()
            myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(0)
            myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(1)
            myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(1)
            myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)

            val valueListener = object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val greenStart = snapshot.child("RaspberryPi/LED/rgbLED/StartGreen").value
                    val blueStart = snapshot.child("RaspberryPi/LED/rgbLED/StartBlue").value
                    if (blueStart != null && greenStart != null)
                    {
                        if(greenStart.toString().equals("1") && blueStart.toString().equals("1"))
                        {
                            rgbLed.setImageResource(R.drawable.rgb_led_cyan)
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }
            }
            myDbReference.addValueEventListener(valueListener);
        }
        //YELLOW COLOR
        yellowButton.setOnClickListener(){
            resetRGBValues()
            myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(1)
            myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(1)
            myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(0)
            myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)

            val valueListener = object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val redStart = snapshot.child("RaspberryPi/LED/rgbLED/StartRed").value
                    val greenStart = snapshot.child("RaspberryPi/LED/rgbLED/StartGreen").value
                    if (redStart != null && greenStart != null)
                    {
                        if(greenStart.toString().equals("1") && redStart.toString().equals("1"))
                        {
                            rgbLed.setImageResource(R.drawable.rgb_led_yellow)
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }
            }
            myDbReference.addValueEventListener(valueListener);
        }
        //WHITE COLOR
        whiteButton.setOnClickListener(){
            resetRGBValues()
            myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(1)
            myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(1)
            myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(1)
            myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)

            val valueListener = object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val redStart = snapshot.child("RaspberryPi/LED/rgbLED/StartRed").value
                    val greenStart = snapshot.child("RaspberryPi/LED/rgbLED/StartGreen").value
                    val blueStart = snapshot.child("RaspberryPi/LED/rgbLED/StartBlue").value
                    if (redStart != null && greenStart != null && blueStart != null)
                    {
                        if(greenStart.toString().equals("1") && redStart.toString().equals("1") && blueStart.toString().equals("1") )
                        {
                            rgbLed.setImageResource(R.drawable.rgb_led_white)
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }
            }
            myDbReference.addValueEventListener(valueListener);
        }
        //OFF MODE
        offButton.setOnClickListener(){
            resetRGBValues()
            myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(0)
            myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(0)
            myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(0)
            myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)

            val valueListener = object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val redStart = snapshot.child("RaspberryPi/LED/rgbLED/StartRed").value
                    val greenStart = snapshot.child("RaspberryPi/LED/rgbLED/StartGreen").value
                    val blueStart = snapshot.child("RaspberryPi/LED/rgbLED/StartBlue").value
                    if (redStart != null && greenStart != null && blueStart != null)
                    {
                        if(greenStart.toString().equals("0") && redStart.toString().equals("0") && blueStart.toString().equals("0"))
                        {
                            rgbLed.setImageResource(R.drawable.normal_led_off)
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }
            }
            myDbReference.addValueEventListener(valueListener);
        }
        //DISCO MODE
        discoButton.setOnClickListener()
        {
            resetRGBValues()
            myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(0)
            myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(0)
            myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(0)
            myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(1)

            val valueListener = object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val discoValue = snapshot.child("RaspberryPi/LED/rgbLED/Disco").value
                    val discoStart = snapshot.child("RaspberryPi/LED/rgbLED/StartLed").value
                    if (discoStart != null && discoValue != null)
                    {
                        if(discoStart.toString().equals("1") && discoValue.toString().equals("1"))
                        {
                            discoSet()
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }
            }
            myDbReference.addValueEventListener(valueListener);
        }

        val returnButton = findViewById<Button>(R.id.returnButtonLED)
        returnButton.setOnClickListener(){
            finish();
        }
    }
    private fun discoSet()
    {
        //Toast.makeText(this, "Got inside discoSet()!", Toast.LENGTH_SHORT).show();
        val rgbLed = findViewById<ImageView>(R.id.rgbLed)
        val handler = Handler()

        val r1 = Runnable { rgbLed.setImageResource(R.drawable.rgb_led_red) }
        val r2 = Runnable { rgbLed.setImageResource(R.drawable.rgb_led_green) }
        val r3 = Runnable { rgbLed.setImageResource(R.drawable.rgb_led_blue) }
        val r4 = Runnable { rgbLed.setImageResource(R.drawable.rgb_led_magenta) }
        val r5 = Runnable { rgbLed.setImageResource(R.drawable.rgb_led_cyan) }
        val r6 = Runnable { rgbLed.setImageResource(R.drawable.rgb_led_yellow) }
        val r7 = Runnable { rgbLed.setImageResource(R.drawable.rgb_led_white) }

        var i = 1
        var count = 1
        val bonusTime = 0
        //executes disco mode 3 times
        while(i <= 15)
        {
            handler.postDelayed(r1, (i*500 + bonusTime).toLong())
            handler.postDelayed(r2, (i*500 + 500 + bonusTime).toLong())
            handler.postDelayed(r3, (i*500 + 1000 + bonusTime).toLong())
            handler.postDelayed(r4, (i*500 + 1500 + bonusTime).toLong())
            handler.postDelayed(r5, (i*500 + 2000 + bonusTime).toLong())
            handler.postDelayed(r6, (i*500 + 2500 + bonusTime).toLong())
            handler.postDelayed(r7, (i*500 + 3000 + bonusTime).toLong())
            i += 7
            count += 1
        }
    }
}