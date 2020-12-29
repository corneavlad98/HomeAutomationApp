package com.example.androidapp

import android.R.attr
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class CameraActivity : AppCompatActivity() {
    var cameraUrl = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        Toast.makeText(this, "Make sure that the IP camera is plugged in and it's configured properly!", Toast.LENGTH_LONG).show();


        val webView = findViewById<WebView>(R.id.cameraWebView)
        cameraUrl = getAddress()
        webView.loadUrl(cameraUrl)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        val test1 = findViewById<TextView>(R.id.textViewTest1)
        test1.text = cameraUrl
        val settingsButton = findViewById<ImageButton>(R.id.gearButton)
        settingsButton.setOnClickListener(){
            val intent = Intent(this, CameraSettingsActivity::class.java).apply {}
            if (intent != null)
                startActivity(intent)
            else
                Toast.makeText(this, "Error at opening Camera Settings activity!", Toast.LENGTH_LONG).show();
        }

        val returnButton = findViewById<Button>(R.id.returnButtonCamera)
        returnButton.setOnClickListener(){
            finish();
        }
    }

    override fun onResume() {
        super.onResume()

        val webView = findViewById<WebView>(R.id.cameraWebView)
        cameraUrl = getAddress()
        webView.loadUrl(cameraUrl)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        val test1 = findViewById<TextView>(R.id.textViewTest1)
        test1.text = cameraUrl
    }


    fun getAddress(): String{
        val sharedPref = this.getSharedPreferences("URL", Context.MODE_PRIVATE)
        val ip = sharedPref.getString("ip", "0.0.0.0")
        val login = sharedPref.getString("login", "admin")
        val password = sharedPref.getString("password", "admin2")

        val cameraUrl = "http://$login:$password@$ip"
        return cameraUrl
    }

}