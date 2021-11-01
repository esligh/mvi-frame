package com.me.mvi.demo.vm

import com.me.mvi.demo.CounterState
import com.yunzhu.frame.base.BaseViewModel

class CounterViewModel(state: CounterState) : BaseViewModel<CounterState>(state) {

    fun incrementCount() = setState { copy(count = count + 1) }


}