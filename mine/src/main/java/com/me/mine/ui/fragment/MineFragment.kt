package com.me.mine.ui.fragment

import android.view.View
import android.widget.Toast
import com.airbnb.mvrx.*
import com.me.mine.databinding.FragmentMineBinding
import com.me.mine.vm.MineViewModel
import com.yunzhu.frame.base.BaseBindingFragment

data class MineState(@PersistState val count: Int = 0,
    val isLogin: Async<Boolean> = Uninitialized):MavericksState

class MineFragment :BaseBindingFragment<FragmentMineBinding>() {

    private val mViewModel: MineViewModel by activityViewModel()

    override fun invalidate()  = withState(mViewModel){

    }

    override fun initView() {
        binding.tvNameUser.setOnClickListener {
            mViewModel.mockLogin()
        }

        binding.tvLogout.setOnClickListener {
            mViewModel.mockLogout()
        }
    }

    override fun setListener() {
        mViewModel.onAsync(MineState::isLogin,onSuccess = {
            binding.tvNameUser.text = if(it) "已登录" else "去登录"
            binding.tvLogout.visibility = if(it) View.VISIBLE else View.GONE
        })

        mViewModel.onEach {
            if(it.isLogin is Loading){
                Toast.makeText(requireContext(),"waiting...", Toast.LENGTH_SHORT).show()
            }
        }
    }
}