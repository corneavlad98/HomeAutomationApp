package com.example.androidapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

var database = FirebaseDatabase.getInstance()
var myDbReference = database.getReference()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var temperatureTextView = findViewById<TextView>(R.id.textViewTemperature)
        var humidityTextView = findViewById<TextView>(R.id.textViewHumidity)

        val valueListener = object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val humidity = snapshot.child("RaspberryPi/DHT11/Humidity/Value%").getValue();
                val temperature = snapshot.child("RaspberryPi/DHT11/Temperature/Value*C").getValue();

                if( humidity != null) {
                    val humidityText = "Humidity: $humidity % ";
                    humidityTextView.setText(humidityText);
                }
                else {
                    val humidityText = "Error at getting humidity!";
                    humidityTextView.setText(humidityText);
                }

                if( temperature != null) {
                    val temperatureText = "Temperature: $temperature *C"
                    temperatureTextView.setText(temperatureText);
                }
                else {
                    val temperatureText = "Error at getting temperature!";
                    temperatureTextView.setText(temperatureText);
                }

            }
            override fun onCancelled(error: DatabaseError) {
            }
        }
        myDbReference.addValueEventListener(valueListener);
    }
}