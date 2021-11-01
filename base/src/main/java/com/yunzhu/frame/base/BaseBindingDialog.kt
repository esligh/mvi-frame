
package com.yunzhu.frame.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.yunzhu.frame.utils.inflateBindingWithGeneric

abstract class BaseBindingDialog<VB : ViewBinding>(context: Context, themeResId: Int = 0)
  : Dialog(context, themeResId) {

  lateinit var binding: VB

  override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = inflateBindingWithGeneric(layoutInflater)
      setContentView(binding.root)
      initView()
  }

  abstract fun initView()

  abstract fun setListener()

}