package com.me.mvi.demo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.*
import com.me.mvi.demo.databinding.CounterFragmentBinding
import com.me.mvi.demo.vm.CounterViewModel
import com.yunzhu.frame.extensions.viewBinding

data class CounterState(@PersistState val count: Int = 0) : MavericksState

class CounterFragment : Fragment(R.layout.counter_fragment), MavericksView {

    private val binding: CounterFragmentBinding by viewBinding()
    private val viewModel: CounterViewModel by activityViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.counterText.setOnClickListener {
            viewModel.incrementCount()
        }

        binding.btn1.setOnClickListener {
            startActivity(Intent(requireContext(),SecondActivity::class.java))
        }

        binding.btn2.setOnClickListener {
            startActivity(Intent(requireContext(),ThirdActivity::class.java))
        }


    }

    override fun invalidate() = withState(viewModel) { state ->
        binding.counterText.text = "${state.count}"
    }

}
