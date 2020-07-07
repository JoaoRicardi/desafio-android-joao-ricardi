package com.example.desafio_android_joao_ricardi.service.api

import com.example.desafio_android_joao_ricardi.models.characters.CharcterResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET("characters")
    suspend fun getCharacters(
        @Query("offset") page: String
    ): Response<CharcterResponseModel>
}