package com.ordolabs.collect.di

import com.ordolabs.collect.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MainViewModel(
            // there will be usecases
        )
    }
}