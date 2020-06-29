package com.example.desafio_android_joao_ricardi.modules

import com.example.desafio_android_joao_ricardi.feature.comic.ExpensiveViewModel
import com.example.desafio_android_joao_ricardi.feature.home.HomeViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { ExpensiveViewModel(get()) }

}