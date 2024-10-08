package com.example.github_androidstudio

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.github_androidstudio.databinding.ActivityMainBinding
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    val TAG: String = "로그"
    private var mBinding: ActivityMainBinding? = null
    private val binding get()= mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val torch = Torch(this) //손전등 관련
        // 뷰 바인딩 클래스의 인스턴스를 생성합니다.
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        // 생성된 뷰를 액티비티에 표시합니다.
        setContentView(binding.root)
        //레이아웃을 설정
        /*
        binding.setTime.setOnClickListener(View.OnClickListener {
            TimeSetButtonClicked()
        })*/
        //setContentView(R.layout.activity_main)
        Log.d(TAG,"MainActivity - onCreate() called")

        /*
        binding.flashSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    torch.flashOn()
                } else {
                    torch.flashOff()
                }
        }*/

        binding.countdownButton.setOnClickListener{
            Log.d(TAG,"timerstart")
            var hourtxt=binding.hour.text.toString()
            var mintxt=binding.min.text.toString()
            var sectxt=binding.second.text.toString()
            var hour:Int=0
            var min:Int=0
            var sec:Int=0
            var isChecked=true
            var turnonalarm=false
            hourtxt=hourtxt.trim()
            mintxt=mintxt.trim()
            sectxt=sectxt.trim()
            if(hourtxt.length>0) {
                hour = Integer.parseInt(binding.hour.text.toString())
            }
            if(mintxt.length>0){
                min = Integer.parseInt(binding.min.text.toString())
            }
            if(sectxt.length>0){
                sec = Integer.parseInt(binding.second.text.toString())
            }
            if(sec==0&&min==0&&hour==0){
                return@setOnClickListener
            }

            /*
            binding.hText.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_2))
            binding.mText.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_2))
            binding.sText.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_2))

            binding.hour.setHintTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_2))
            binding.min.setHintTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_2))
            binding.second.setHintTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_2))

            binding.cancelBtn.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_2))
            binding.countdownText.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_2))

            binding.imgMiniLight.visibility=View.VISIBLE
            binding.countdownButton.visibility=View.INVISIBLE

            binding.imgBody1.setImageResource(R.drawable.on_light_body5)
            binding.imgBodyEnd.setImageResource(R.drawable.on_light_body6)
            */
            // on 색으로 변경
            color_change_on()
            
            timer(period = 1000, initialDelay = 1000) {
                runOnUiThread {
                    binding.countdownText.text = String.format("%02d:%02d:%02d", hour, min, sec)
                }
                if (turnonalarm == true) {
                    if (isChecked) {
                        torch.flashOn()
                        isChecked=false
                    } else {
                        torch.flashOff()
                        isChecked=true
                    }
                    binding.cancelBtn.setOnClickListener {
                        torch.flashOff()
                        binding.countdownText.text = String.format("%02d:%02d:%02d", 0,0,0)
                        color_change_off()
                        cancel()
                    }
                }
                else {
                    if (sec == 0 && min == 0 && hour == 0) {
                        turnonalarm = true
                        sec++
                    }
                    if (min == 0 && sec == 0) {
                        hour--
                        min = 59
                        sec = 60
                    }
                    if (sec == 0) {
                        min--
                        sec = 60
                    }
                    sec--
                    binding.cancelBtn.setOnClickListener {
                        binding.countdownText.text = String.format("%02d:%02d:%02d", 0,0,0)
                        color_change_off()
                        cancel()
                    }
                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG,"MainActivity - onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"MainActivity - onResume() called")
    }

    override fun onPause() {
        super.onPause()
        //binding.textView.setText("abcd")
        Log.d(TAG,"MainActivity - onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"MainActivity - onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"MainActivity - onDestroy() called")
    }

    fun TimeSetButtonClicked(){
        Log.d(TAG,"MainActivity - TimeSetButtonClicked() called")
        val intent = Intent(this, secondactivity::class.java)

        startActivity(intent)
    }

    // 켰을 때 색 바꾸기
    fun color_change_on(){
        //binding.hText.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_2))
        //binding.mText.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_2))
        //binding.sText.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_2))
        //binding.alarmSetText.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_2))

        binding.hour.setHintTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_2))
        binding.min.setHintTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_2))
        binding.second.setHintTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_2))

        binding.cancelBtn.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_2))
        binding.countdownText.setTextColor(ContextCompat.getColor(applicationContext!!, R.color.main_2))

        binding.imgMiniLight.visibility=View.VISIBLE
        binding.countdownButton.visibility=View.INVISIBLE
        binding.cancelBtn.setText("cancel")
        //binding.cancelBtn.visibility=View.VISIBLE

        binding.imgBody1.setImageResource(R.drawable.on_light_body5)
        binding.imgBodyEnd.setImageResource(R.drawable.on_light_body6)
    }

    // 껐을 때 색 바꾸기
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

        binding.imgMiniLight.visibility=View.INVISIBLE
        binding.countdownButton.visibility=View.VISIBLE
        binding.cancelBtn.setText("reset")
        //binding.cancelBtn.visibility=View.INVISIBLE

        binding.imgBody1.setImageResource(R.drawable.off_light_body5)
        binding.imgBodyEnd.setImageResource(R.drawable.off_light_body6)
    }
}
class ActivityMainBinding {

}
