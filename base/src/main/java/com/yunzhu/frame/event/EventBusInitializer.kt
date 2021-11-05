package com.yunzhu.frame.event

import android.app.Application

object EventBusInitializer {

    lateinit var application: Application

    fun init(application: Application) {
        EventBusInitializer.application = application
    }

}