package com.yunzhu.frame.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.airbnb.mvrx.MavericksView
import com.yunzhu.frame.utils.inflateBindingWithGeneric

abstract class BaseBindingFragment<VB:ViewBinding> : Fragment(), MavericksView
{
    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBindingWithGeneric(layoutInflater, container, false)
        setListener()
        initView()
        return binding.root
    }

    abstract fun initView()

    abstract fun setListener()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}