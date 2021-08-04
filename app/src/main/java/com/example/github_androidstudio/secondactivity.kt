package com.example.github_androidstudio

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class secondactivity : AppCompatActivity() {
    val TAG: String = "로그"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondactivity)
    }
    fun onBackButtonClicked(view: View){
        Log.d(TAG,"secondactivity - onBackButtonClicked() called")
        finish()
    }
}