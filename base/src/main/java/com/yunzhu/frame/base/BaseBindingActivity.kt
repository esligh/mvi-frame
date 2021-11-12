package com.yunzhu.frame.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.airbnb.mvrx.MavericksView
import com.yunzhu.frame.utils.inflateBindingWithGeneric
import com.yunzhu.frame.widget.LoadingDialog

/**
 * base binding activity
 * */
abstract class BaseBindingActivity<VB : ViewBinding> :AppCompatActivity(), MavericksView {

    lateinit var binding: VB

    private lateinit var mLoadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateBindingWithGeneric(layoutInflater)
        mLoadingDialog = LoadingDialog(this,true)
        setContentView(binding.root)
        setListener()
        init()
    }

    abstract fun init()

    abstract fun setListener()

    override fun invalidate() {}

    /**d
     * show loading dialog
     */
    fun showLoading() {
        mLoadingDialog.showDialog(this, false)
    }

    /**
     * dismiss loading dialog
     */
    fun dismissLoading() {
        mLoadingDialog.dismissDialog()
    }

}