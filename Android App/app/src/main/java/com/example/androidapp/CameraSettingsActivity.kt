package com.example.androidapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CameraSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_settings)

        var ipIsEmpty = true;
        val ipEdit = findViewById<EditText>(R.id.editTextIP)
        val loginEdit = findViewById<EditText>(R.id.editTextLogin)
        val passwordEdit = findViewById<EditText>(R.id.editTextPassword)

        val urlSharedPref = this.getSharedPreferences("URL", 0)
        val editor = urlSharedPref.edit()

        val settingsTest = findViewById<TextView>(R.id.settingsTest)

        //get data from shared Pref and set it in EditTexts
        ipEdit.setText(urlSharedPref.getString("ip", ""))
        loginEdit.setText(urlSharedPref.getString("login", ""))
        passwordEdit.setText(urlSharedPref.getString("password", ""))

        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener()
        {
            val ipText = ipEdit.text.toString()
            val loginText = loginEdit.text.toString()
            val passwordText = passwordEdit.text.toString()

            if (ipText.equals(""))
                ipIsEmpty = true;
            else
            {
                ipIsEmpty = false;
                editor.putString("ip", ipText)
            }
            if(!loginText.equals("") && !ipIsEmpty)
            {
                editor.putString("login", loginText)
            }

            if(!passwordText.equals("") && !ipIsEmpty)
            {
                editor.putString("password", passwordText)
            }

            if(!ipIsEmpty)
            {
                editor.apply();

                val ip = urlSharedPref.getString("ip", "")
                val login = urlSharedPref.getString("login", "")
                val password = urlSharedPref.getString("password", "")
                val cameraUrl = "http://$login:$password@$ip"

                settingsTest.text = cameraUrl
                Toast.makeText(this, "Data saved!", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this, "Ip is required!", Toast.LENGTH_SHORT).show();
        }

        val returnButton = findViewById<Button>(R.id.returnButtonCameraSettings)
        returnButton.setOnClickListener(){
            finish();
        }
    }
}