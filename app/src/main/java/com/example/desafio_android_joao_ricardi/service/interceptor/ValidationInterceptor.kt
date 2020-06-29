package com.example.desafio_android_joao_ricardi.service.interceptor

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class ValidationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        println("INSIDE VALIDATOR")

        val originalUrl = chain.request().url()
        println("=====================")
        println("ORIGINAL URL $originalUrl")
        println("=====================")

        var newUrl: HttpUrl = originalUrl.newBuilder()
            .addQueryParameter("apikey","5384a6e199c6db9b7f51478b732652b1")
            .addQueryParameter("ts", "2020/01/01")
            .addQueryParameter("hash", "9b605a0591e74176af41eb9c26e9d5ec")
            .build()

        println("=====================")
        println("newUrl URL $newUrl")
        println("=====================")

        println("ENDVALIDATOR")

        return chain.proceed(
            chain.request().newBuilder()
                .url(newUrl)
                .build()
        )



    }

}