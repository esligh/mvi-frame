package com.yunzhu.frame.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.airbnb.mvrx.MavericksView
import com.yunzhu.frame.utils.inflateBindingWithGeneric

/**
 * base binding activity
 *
 * */
abstract class BaseBindingActivity<VB : ViewBinding> :AppCompatActivity(), MavericksView {

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateBindingWithGeneric(layoutInflater)
        setContentView(binding.root)

        setListener()
        initView()
    }

    open fun initView(){}

    abstract fun setListener()

    override fun invalidate() {}

}