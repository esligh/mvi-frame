package com.me.mvi.demo.vm

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.PersistState
import com.yunzhu.frame.base.BaseViewModel

/**
 * [PersistState] will persist the count if Android kills the process in the background
 * and restores it in a new process.
 */
data class FlowState(@PersistState val count: Int = 0) : MavericksState

class FlowViewModel(initialState: FlowState) : BaseViewModel<FlowState>(initialState) {

    fun setCount(count: Int) = setState { copy(count = count) }
}
