package com.example.github_androidstudio

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.github_androidstudio.databinding.ActivityMainBinding
import kotlin.concurrent.timer

class secondactivity : AppCompatActivity() {
    val TAG: String = "로그"
    private var mBinding: ActivityMainBinding? = null
    private val binding get()= mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val torch = Torch(this)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        timer(period = 1000, initialDelay = 1000){
            var isChecked=true
            if (isChecked) {
                torch.flashOn()
                isChecked=false
            } else {
                torch.flashOff()
                isChecked=true
            }
            binding.cancelBtn.setOnClickListener{
                Log.d(TAG,"secondactivity - onBackButtonClicked() called")
                finish()
                cancel()
            }
        }
    }
}