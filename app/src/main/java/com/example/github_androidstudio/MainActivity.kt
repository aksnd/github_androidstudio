package com.example.github_androidstudio

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.github_androidstudio.databinding.ActivityMainBinding

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
        binding.setTime.setOnClickListener(View.OnClickListener {
            TimeSetButtonClicked()
        })
        //setContentView(R.layout.activity_main)
        Log.d(TAG,"MainActivity - onCreate() called")

        binding.flashSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    torch.flashOn()
                } else {
                    torch.flashOff()
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
        binding.textView.setText("abcd")
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
}
class ActivityMainBinding {

}
