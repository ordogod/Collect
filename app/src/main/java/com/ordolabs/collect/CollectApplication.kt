package com.ordolabs.collect

import android.app.Application
import android.content.Context
import com.ordolabs.collect.di.DBSourceModule
import com.ordolabs.collect.di.useCaseModule
import com.ordolabs.collect.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CollectApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        setKoin()
    }



    private fun setKoin() {
        startKoin {
            androidContext(this@CollectApplication)
            modules(
                DBSourceModule,
                useCaseModule,
                viewModelModule
            )
        }
    }

    companion object {
        lateinit var context: Context
    }
}