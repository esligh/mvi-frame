package com.yunzhu.frame.event.post

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.yunzhu.frame.event.core.EventBusCore
import com.yunzhu.frame.event.store.ApplicationScopeViewModelProvider

//_______________________________________
//          post event
//_______________________________________

//Application范围的事件
inline fun <reified T> postEvent(event: T, timeMillis: Long = 0L) {
    ApplicationScopeViewModelProvider.getApplicationScopeViewModel(EventBusCore::class.java)
        .postEvent(T::class.java.name, event!!, timeMillis)
}

//限定范围的事件
inline fun <reified T> postEvent(scope: ViewModelStoreOwner, event: T, timeMillis: Long = 0L) {
    ViewModelProvider(scope).get(EventBusCore::class.java)
        .postEvent(T::class.java.name, event!!, timeMillis)
}