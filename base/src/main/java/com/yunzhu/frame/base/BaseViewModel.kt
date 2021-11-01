package com.yunzhu.frame.base

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel

abstract class BaseViewModel<T: MavericksState>(state:T):MavericksViewModel<T>(state)
