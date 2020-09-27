package com.ordolabs.collect.di

import com.ordolabs.collect.viewmodel.CreateItemViewModel
import com.ordolabs.collect.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        HomeViewModel(
            // there will be usecases
        )
        CreateItemViewModel(
            // there will be usecases
        )
    }
}