package com.example.desafio_android_joao_ricardi.modules

import com.example.desafio_android_joao_ricardi.service.interceptor.CustomInterceptor
import com.example.desafio_android_joao_ricardi.service.api.CharacterApi
import com.example.desafio_android_joao_ricardi.service.api.ComicApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.math.sin

val netWorkModule = module {

    single {CustomInterceptor(get()) }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<CustomInterceptor>())
            .build()
    }


    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://gateway.marvel.com/v1/public/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single { get<Retrofit>().create(CharacterApi::class.java) }
    single { get<Retrofit>().create(ComicApi::class.java) }
}