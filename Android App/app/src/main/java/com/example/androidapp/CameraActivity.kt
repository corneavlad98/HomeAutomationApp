package com.example.androidapp

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.VideoView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class CameraActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val video = findViewById<VideoView>(R.id.videoViewCamera)

        val usernamme = "admin"
        val password = "7A8s9d4f"

        val header:HashMap<String, String> = HashMap<String, String>(1)
        val cred = usernamme + ":" + password
        val auth = "Basic" + Base64.encodeToString(cred.toByteArray(Charsets.UTF_8), Base64.URL_SAFE)
        header.put("Authorization", auth)
        video.setVideoURI(Uri.parse("http://192.168.1.123/video.cgi"), header)

        //video.setVideoPath("http://192.168.1.123/video.cgi")
        //video.setVideoPath("https://developers.google.com/training/images/tacoma_narrows.mp4")
        video.start();

        val returnButton = findViewById<Button>(R.id.returnButtonCamera)
        returnButton.setOnClickListener() {
            finish();
        }
    }
}