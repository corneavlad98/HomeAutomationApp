package com.example.androidapp

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
        setNormalLedValuesInDatabase(led = 1, value = 0)
        setNormalLedValuesInDatabase(led = 2, value = 0)

        setNormalLedStartInDatabase(led = 1, value = 0)
        setNormalLedStartInDatabase(led = 2, value = 0)

        setRgbLedValuesInDatabase(red = false, green = false , blue = false ,disco = false)
        setRgbLedStartInDatabase(red = false, green = false , blue = false ,disco = false)
    }
    private fun resetRGBValues(){
        setRgbLedValuesInDatabase(red = false, green = false , blue = false ,disco = false)
        setRgbLedStartInDatabase(red = false, green = false , blue = false ,disco = false)
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

        val led1ValuePath = "RaspberryPi/LED/LED1"
        val led1StartPath = "RaspberryPi/LED/StartNormal1"
        val led2ValuePath = "RaspberryPi/LED/LED2"
        val led2StartPath = "RaspberryPi/LED/StartNormal2"

        val startRedPath = "RaspberryPi/LED/rgbLED/StartRed"
        val startGreenPath = "RaspberryPi/LED/rgbLED/StartGreen"
        val startBluePath = "RaspberryPi/LED/rgbLED/StartBlue"

        //click listeners to change Database value of normal led switches
        var pressedOnce1 = false;
        ledImageButton1.setOnClickListener(){
            if(!pressedOnce1)
            {
                setNormalLedValuesInDatabase(led = 1, value = 1)
                val valueListener = normalLedPictureHandler(R.id.normalLed1, led1ValuePath, led1StartPath)
                myDbReference.addValueEventListener(valueListener);
                pressedOnce1 = true;
            }
            else
            {
                setNormalLedValuesInDatabase(led = 1, value = 0)
                val valueListener = normalLedPictureHandler(R.id.normalLed1, led1ValuePath, led1StartPath)
                myDbReference.addValueEventListener(valueListener);
                pressedOnce1 = false;
            }
        }

        var pressedOnce2 = false;
        ledImageButton2.setOnClickListener(){
            if(!pressedOnce2)
            {
                setNormalLedValuesInDatabase(led = 2, value = 1)
                val valueListener = normalLedPictureHandler(R.id.normalLed2, led2ValuePath, led2StartPath)
                myDbReference.addValueEventListener(valueListener);
                pressedOnce2 = true;
            }
            else
            {
                setNormalLedValuesInDatabase(led = 2, value = 0)
                val valueListener = normalLedPictureHandler(R.id.normalLed2, led2ValuePath, led2StartPath)
                myDbReference.addValueEventListener(valueListener);
                pressedOnce2 = false;
            }
        }

        //color buttons for the RGB LED
        //RED COLOR
        redButton.setOnClickListener(){
            //resetRGBValues()
            setRgbLedValuesInDatabase(red = true, green = false, blue = false ,disco = false)
            val valueListener = rgbLedPictureHandler(startRedPath, startGreenPath, startBluePath)
            myDbReference.addValueEventListener(valueListener);
        }
        //GREEN COLOR
        greenButton.setOnClickListener(){
            //resetRGBValues()
            setRgbLedValuesInDatabase(red = false, green = true, blue = false ,disco = false)
            val valueListener = rgbLedPictureHandler(startRedPath, startGreenPath, startBluePath)
            myDbReference.addValueEventListener(valueListener);
        }
        //BLUE COLOR
        blueButton.setOnClickListener(){
            //resetRGBValues()
            setRgbLedValuesInDatabase(red = false, green = false, blue = true ,disco = false)
            val valueListener = rgbLedPictureHandler(startRedPath, startGreenPath, startBluePath)
            myDbReference.addValueEventListener(valueListener);
        }
        //MAGENTA COLOR
        magentaButton.setOnClickListener(){
            //resetRGBValues()
            setRgbLedValuesInDatabase(red = true, green = false, blue = true ,disco = false)
            val valueListener = rgbLedPictureHandler(startRedPath, startGreenPath, startBluePath)
            myDbReference.addValueEventListener(valueListener);
        }
        //CYAN COLOR
        cyanButton.setOnClickListener(){
            //resetRGBValues()
            setRgbLedValuesInDatabase(red = false, green = true, blue = true ,disco = false)
            val valueListener = rgbLedPictureHandler(startRedPath, startGreenPath, startBluePath)
            myDbReference.addValueEventListener(valueListener);
        }
        //YELLOW COLOR
        yellowButton.setOnClickListener(){
            //resetRGBValues()
            setRgbLedValuesInDatabase(red = true, green = true, blue = false ,disco = false)
            val valueListener = rgbLedPictureHandler(startRedPath, startGreenPath, startBluePath)
            myDbReference.addValueEventListener(valueListener);
        }
        //WHITE COLOR
        whiteButton.setOnClickListener(){
           // resetRGBValues()
            setRgbLedValuesInDatabase(red = true, green = true, blue = true ,disco = false)
            val valueListener = rgbLedPictureHandler(startRedPath, startGreenPath, startBluePath)
            myDbReference.addValueEventListener(valueListener);
        }
        //OFF MODE
        offButton.setOnClickListener(){
            //resetRGBValues()
            setRgbLedValuesInDatabase(red = false, green = false, blue = false ,disco = false)
            val valueListener = rgbLedPictureHandler(startRedPath, startGreenPath, startBluePath)
            myDbReference.addValueEventListener(valueListener);
        }
        //DISCO MODE
        discoButton.setOnClickListener()
        {
            resetRGBValues()
            setRgbLedValuesInDatabase(red = false, green = false, blue = false ,disco = true)

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
    private fun normalLedPictureHandler(ledId: Int, ledValuePath: String, ledStartPath: String): ValueEventListener
    {
        val ledImageButton = findViewById<ImageButton>(ledId)

        return object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val ledValue = snapshot.child(ledValuePath).value
                val ledStart = snapshot.child(ledStartPath).value

                if (ledValue != null && ledStart != null)
                {
                    if(ledValue.toString().equals("1") && ledStart.toString().equals("1"))
                        ledImageButton.setImageResource(R.drawable.normal_led_on)

                    if(ledValue.toString().equals("0") && ledStart.toString().equals("0"))
                        ledImageButton.setImageResource(R.drawable.normal_led_off)
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        }
    }
    private fun rgbLedPictureHandler(redStartPath: String, greenStartPath: String, blueStartPath: String): ValueEventListener
    {
        val rgbLed = findViewById<ImageView>(R.id.rgbLed)

        return object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val redStart = snapshot.child(redStartPath).value
                val greenStart = snapshot.child(greenStartPath).value
                val blueStart = snapshot.child(blueStartPath).value

                if(redStart != null && greenStart != null && blueStart != null)
                {
                    if(redStart.toString().equals("1"))
                        rgbLed.setImageResource(R.drawable.rgb_led_red)
                    if(greenStart.toString().equals("1"))
                        rgbLed.setImageResource(R.drawable.rgb_led_green)
                    if(blueStart.toString().equals("1"))
                        rgbLed.setImageResource(R.drawable.rgb_led_blue)

                    if(redStart.toString().equals("1") && blueStart.toString().equals("1"))
                        rgbLed.setImageResource(R.drawable.rgb_led_magenta)
                    if(greenStart.toString().equals("1") && blueStart.toString().equals("1"))
                        rgbLed.setImageResource(R.drawable.rgb_led_cyan)
                    if(redStart.toString().equals("1") && greenStart.toString().equals("1"))
                        rgbLed.setImageResource(R.drawable.rgb_led_yellow)

                    if(redStart.toString().equals("1") && greenStart.toString().equals("1") && blueStart.toString().equals("1"))
                        rgbLed.setImageResource(R.drawable.rgb_led_white)
                    if(redStart.toString().equals("0") && greenStart.toString().equals("0") && blueStart.toString().equals("0"))
                        rgbLed.setImageResource(R.drawable.normal_led_off)
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        }
    }
    private fun setNormalLedValuesInDatabase(led: Int, value: Int)
    {
        if(led == 1)
            myDbReference.child("RaspberryPi/LED/LED1").setValue(value)
        else if(led == 2)
            myDbReference.child("RaspberryPi/LED/LED2").setValue(value)
    }
    private fun setNormalLedStartInDatabase(led: Int, value: Int)
    {
        if(led == 1)
            myDbReference.child("RaspberryPi/LED/StartNormal1").setValue(value)
        else if(led == 2)
            myDbReference.child("RaspberryPi/LED/StartNormal2").setValue(value)
    }
    private fun setRgbLedValuesInDatabase(red: Boolean, green: Boolean, blue: Boolean, disco: Boolean)
    {
        if(red)
            myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(1)
        else
            myDbReference.child("RaspberryPi/LED/rgbLED/Red").setValue(0)
        if(green)
            myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(1)
        else
            myDbReference.child("RaspberryPi/LED/rgbLED/Green").setValue(0)
        if(blue)
            myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(1)
        else
            myDbReference.child("RaspberryPi/LED/rgbLED/Blue").setValue(0)
        if(disco)
            myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(1)
        else
            myDbReference.child("RaspberryPi/LED/rgbLED/Disco").setValue(0)
    }
    private fun setRgbLedStartInDatabase(red: Boolean, green: Boolean, blue: Boolean, disco: Boolean)
    {
        if(red)
            myDbReference.child("RaspberryPi/LED/rgbLED/StartRed").setValue(1)
        else
            myDbReference.child("RaspberryPi/LED/rgbLED/StartRed").setValue(0)
        if(green)
            myDbReference.child("RaspberryPi/LED/rgbLED/StartGreen").setValue(1)
        else
            myDbReference.child("RaspberryPi/LED/rgbLED/StartGreen").setValue(0)
        if(blue)
            myDbReference.child("RaspberryPi/LED/rgbLED/StartBlue").setValue(1)
        else
            myDbReference.child("RaspberryPi/LED/rgbLED/StartBlue").setValue(0)
        if(disco)
            myDbReference.child("RaspberryPi/LED/rgbLED/StartLed").setValue(1)
        else
            myDbReference.child("RaspberryPi/LED/rgbLED/StartLed").setValue(0)
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