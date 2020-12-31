package com.example.androidapp

import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
        val ledImageButton1 = findViewById<ImageButton>(R.id.normalLed1)
        val ledImageButton2 = findViewById<ImageButton>(R.id.normalLed2)

        val rgbLedRedSwitch = findViewById<Switch>(R.id.redSwitch)
        val rgbLedGreenSwitch = findViewById<Switch>(R.id.greenSwitch)
        val rgbLedBlueSwitch = findViewById<Switch>(R.id.blueSwitch)

        var pressedOnce1 = false;
        val handler = Handler();

        //click listeners to change Database value of normal led switches
        ledImageButton1.setOnClickListener(){
            //make an Alert Dialog that prompts the user to "wait" 3 seconds in order to compensate for the required time the RPI takes to get the request
            val alert = waitAlertDialogCreate("Wait for it...");
            val drawable = ledImageButton1.drawable as TransitionDrawable

            if(!pressedOnce1)
            {
                myDbReference.child("RaspberryPi/LED/LED1").setValue(1)
                val led1Runnable = Runnable {
                    run()
                    {
                        if(alert.isShowing){
                            alert.dismiss();
                            drawable.startTransition(400);
                        }
                    }
                }
                alert.setOnDismissListener(){
                    handler.removeCallbacks(led1Runnable);
                }
                handler.postDelayed(led1Runnable, 3500);
                pressedOnce1 = true;
            }
            else
            {
                myDbReference.child("RaspberryPi/LED/LED1").setValue(0)
                val openR = Runnable {
                    run()
                    {
                        if(alert.isShowing){
                            alert.dismiss();
                            drawable.reverseTransition(400);
                        }
                    }
                }
                alert.setOnDismissListener(){
                    handler.removeCallbacks(openR);
                }
                handler.postDelayed(openR, 3500);
                pressedOnce1 = false;
            }
        }

        var pressedOnce2 = false;
        ledImageButton2.setOnClickListener(){
            val alert = waitAlertDialogCreate("Wait for it...");
            val drawable = ledImageButton2.drawable as TransitionDrawable
            if(!pressedOnce2)
            {
                myDbReference.child("RaspberryPi/LED/LED2").setValue(1)
                val led2Runnable = Runnable {
                    run()
                    {
                        if(alert.isShowing){
                            alert.dismiss();
                            drawable.startTransition(400);
                        }
                    }
                }
                alert.setOnDismissListener(){
                    handler.removeCallbacks(led2Runnable);
                }
                handler.postDelayed(led2Runnable, 3500);
                pressedOnce2 = true;
            }
            else
            {
                myDbReference.child("RaspberryPi/LED/LED2").setValue(0)
                val led2Runnable = Runnable {
                    run()
                    {
                        if(alert.isShowing){
                            alert.dismiss();
                            drawable.reverseTransition(400);
                        }
                    }
                }
                alert.setOnDismissListener(){
                    handler.removeCallbacks(led2Runnable);
                }
                handler.postDelayed(led2Runnable, 3500);
                pressedOnce2 = false;
            }
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

    private fun waitAlertDialogCreate(message: String): AlertDialog {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        val dialog = builder.show()
        val messageText = dialog.findViewById<View>(android.R.id.message) as TextView?
        messageText!!.gravity = Gravity.CENTER
        messageText.textSize = 20F
        dialog.show()
        dialog.window?.setLayout(580,270)
        return dialog;
    }
}