package com.me.home.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.MavericksState
import com.me.home.R
import com.me.home.databinding.FragmentSquareBinding
import com.yunzhu.frame.base.BaseBindingFragment

data class SquareState(val state:Int =0):MavericksState

class SquareFragment : BaseBindingFragment<FragmentSquareBinding>() {

    override fun initView() {

    }

    override fun setListener() {

    }

    override fun invalidate() {

    }

}