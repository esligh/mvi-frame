package com.me.mvi.demo.vm

import android.util.Log
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.airbnb.mvrx.sample.network.DadJokeService
import com.me.mvi.demo.ThirdState
import com.yunzhu.frame.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject
import kotlin.random.Random

class ThirdViewModel(state: ThirdState,
                     private val dadJokeService: DadJokeService) : com.yunzhu.frame.base.BaseViewModel<ThirdState>(state) {

    fun incrementCount() = setState { copy(count = count + 1) }

    fun queryWeather() {
        suspend {
            Log.d("sss","Thread 1="+Thread.currentThread().name)
            loadSomeThing()
        }.execute(Dispatchers.IO){
            Log.d("sss","Thread 2="+Thread.currentThread().name)
            copy(name = it)
        }
    }

    fun fetchRandomJoke() {
        suspend {
            dadJokeService.random()
        }.execute(Dispatchers.IO) {
            copy(joke = it)
        }
    }

    private fun loadSomeThing():String
    {
        Thread.sleep(3000)
        return Random.nextInt().toString()
    }

    /**
     * If you implement MvRxViewModelFactory in your companion object, MvRx will use that to create
     * your ViewModel. You can use this to achieve constructor dependency injection with Mavericks.
     *
     * @see MavericksViewModelFactory
     */
    companion object : MavericksViewModelFactory<ThirdViewModel, ThirdState> {

        override fun create(viewModelContext: ViewModelContext, state: ThirdState): ThirdViewModel {
            val service: DadJokeService by viewModelContext.activity.inject()
            return ThirdViewModel(state, service)
        }
    }
}
