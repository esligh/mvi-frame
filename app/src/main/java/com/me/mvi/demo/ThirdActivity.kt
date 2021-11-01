package com.me.mvi.demo

import android.widget.Toast
import com.airbnb.mvrx.*
import com.yunzhu.frame.base.BaseBindingActivity
import com.me.mvi.demo.databinding.ActivityThirdBinding
import com.me.mvi.demo.models.Joke
import com.me.mvi.demo.vm.ThirdViewModel

data class ThirdState(@PersistState val count: Int = 0,
                      val name: Async<String> = Uninitialized,
                      val joke: Async<Joke> = Uninitialized) : MavericksState

class ThirdActivity : BaseBindingActivity<ActivityThirdBinding>() {

    private val viewModel: ThirdViewModel by viewModel()

    override fun setListener() {
        binding.btn.setOnClickListener {
//            viewModel.incrementCount()
//            viewModel.queryWeather()
            viewModel.fetchRandomJoke()
            Toast.makeText(this,"click",Toast.LENGTH_SHORT).show()
        }

//        viewModel.onEach {
//            binding.tv.text = it.count.toString()
//        }

        viewModel.onAsync(ThirdState::name,onSuccess = {
            binding.tv.text = it
        },onFail = {
            binding.tv.text = "error"
        })

        viewModel.onAsync(ThirdState::joke,onSuccess = {
            binding.tv.text = it.joke
        },onFail = {
            binding.tv.text = "load joke error"
        })

        viewModel.onEach {
            if(it.joke is Loading){
                Toast.makeText(this,"loading joke",Toast.LENGTH_SHORT).show()
            }
        }

    }



}