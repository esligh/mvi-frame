package com.yunzhu.frame.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.airbnb.mvrx.MavericksView
import com.yunzhu.frame.utils.inflateBindingWithGeneric
import com.yunzhu.frame.widget.LoadingDialog

abstract class BaseBindingFragment<VB:ViewBinding> : Fragment(), MavericksView
{
    private var _binding: VB? = null
    val binding: VB get() = _binding!!
    private lateinit var mLoadingDialog: LoadingDialog

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

    open fun initView(){}

    abstract fun setListener()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * show loading dialog
     */
    fun showLoading() {
        mLoadingDialog.showDialog(requireContext(), false)
    }

    /**
     * dismiss loading dialog
     */
    fun dismissLoading() {
        mLoadingDialog.dismissDialog()
    }

}