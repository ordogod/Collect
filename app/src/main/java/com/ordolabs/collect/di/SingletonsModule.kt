package com.ordolabs.collect.di

import com.ordolabs.collect.ui.navigation.Navigator
import org.koin.dsl.module

val singletonsModule = module {

    // Navigation
    single {
        Navigator()
    }

}