package com.example.desafio_android_joao_ricardi.service.api

import com.example.desafio_android_joao_ricardi.models.characters.CharcterResponseModel
import com.example.desafio_android_joao_ricardi.models.comics.ComicsResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicApi {

    @GET("characters/{id}/comics")
    fun getAllComics(
        @Path("id") id: Int
    ): Deferred<ComicsResponseModel>
}