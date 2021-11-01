package com.me.mvi.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.me.mvi.demo.databinding.ActivitySecondBinding
import com.yunzhu.frame.extensions.viewBinding

class SecondActivity : AppCompatActivity() {

//    private val binding by viewBinding(ActivitySecondBinding::inflate)

    private val binding:ActivitySecondBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        binding.text.text = "second activity"
    }
}