package com.example.desafio_android_joao_ricardi.service.interceptor

import android.content.Context
import com.example.desafio_android_joao_ricardi.R
import com.example.desafio_android_joao_ricardi.extensios.getMD5EncryptedString
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.time.Instant
import java.time.format.DateTimeFormatter

class CustomInterceptor(val context: Context) : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalUrl = chain.request().url()

        var timeStamp = Instant.now().toString()

        var hash: String = timeStamp + context.getString(R.string.api_private_key) + context.getString(R.string.api_public_key)

        var mb5 = getMD5EncryptedString(hash)

        var newUrl: HttpUrl = originalUrl.newBuilder()
            .addQueryParameter("apikey", context.getString(R.string.api_public_key))
            .addQueryParameter("ts", timeStamp)
            .addQueryParameter("hash", mb5)
            .addQueryParameter("limit", "20")
            .build()


        return chain.proceed(
            chain.request().newBuilder()
                .url(newUrl)
                .build()
        )

    }

}