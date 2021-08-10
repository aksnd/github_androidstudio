package com.example.github_androidstudio

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
                // Main에서 이미지 바꾸기
                color_change_off()
                finish()
                cancel()

            }
        }
    }

    // 껐을때, MainActivity에도 존재함.
    fun color_change_off(){
        //binding.hText.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_1))
        //binding.mText.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_1))
        //binding.sText.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_1))
        //binding.alarmSetText.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_1))

        binding.hour.setHintTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_4))
        binding.min.setHintTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_4))
        binding.second.setHintTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_4))

        binding.cancelBtn.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_4))
        binding.countdownText.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_3))

        binding.imgMiniLight.visibility= View.INVISIBLE
        binding.countdownButton.visibility= View.VISIBLE
        binding.cancelBtn.setText("reset")
        //binding.cancelBtn.visibility=View.INVISIBLE

        binding.imgBody1.setImageResource(R.drawable.off_light_body5)
        binding.imgBodyEnd.setImageResource(R.drawable.off_light_body6)
    }
}

