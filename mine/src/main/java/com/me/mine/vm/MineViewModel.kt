package com.me.mine.vm

import com.me.mine.ui.fragment.MineState
import com.yunzhu.frame.base.BaseViewModel
import kotlinx.coroutines.Dispatchers

class MineViewModel(state:MineState):BaseViewModel<MineState>(state) {

    fun mockLogin()
    {
        suspend {
            tryLogin()
        }.execute(Dispatchers.IO){
            copy(isLogin = it)
        }
    }

    fun mockLogout()
    {
        suspend {
            tryLogout()
        }.execute(Dispatchers.IO){
            copy(isLogin = it)
        }

    }

    private fun tryLogin():Boolean
    {
        Thread.sleep(3000)
        return true
    }

    private fun tryLogout():Boolean
    {
        Thread.sleep(3000)
        return false
    }
}