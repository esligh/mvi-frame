package com.me.home.di

import com.me.repo.api.HomeService
import com.me.repo.dao.db.AppDatabase
import com.me.repo.module.HomeRepo
import com.yunzhu.frame.net.ApiFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val moduleHome = module {
    single {
        ApiFactory.initFactory().getService(HomeService::class.java)
    }

    single {
        AppDatabase.get(androidApplication())
    }

    single {
        HomeRepo(get(),get())
    }

}