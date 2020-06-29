package com.example.desafio_android_joao_ricardi

import android.app.Application
import com.example.desafio_android_joao_ricardi.modules.netWorkModule
import com.example.desafio_android_joao_ricardi.modules.repositoryModule
import com.example.desafio_android_joao_ricardi.modules.viewModelModule
import org.koin.android.ext.android.startKoin

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(
            netWorkModule, repositoryModule, viewModelModule
        ))
    }
}