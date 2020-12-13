package com.example.androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class LEDActivity : AppCompatActivity() {
    var database = FirebaseDatabase.getInstance()
    var myDbReference = database.getReference()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l_e_d)

        val switch1 = findViewById<Switch>(R.id.ledSwitch1);
        val switch2 = findViewById<Switch>(R.id.ledSwitch2);

        switch1.setOnClickListener(){
            if(switch1.isChecked)
                myDbReference.child("RaspberryPi/LED/LED1").setValue(1)
            else
                myDbReference.child("RaspberryPi/LED/LED1").setValue(0)
        }
        switch2.setOnClickListener(){
            if(switch2.isChecked)
                myDbReference.child("RaspberryPi/LED/LED2").setValue(1)
            else
                myDbReference.child("RaspberryPi/LED/LED2").setValue(0)
        }

        val returnButton = findViewById<Button>(R.id.returnButtonLED)
        returnButton.setOnClickListener(){
            finish();
        }
    }
}