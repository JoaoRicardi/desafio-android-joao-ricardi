package com.example.desafio_android_joao_ricardi.service.repositories.character

import com.example.desafio_android_joao_ricardi.models.characters.CharcterResponseModel
import com.example.desafio_android_joao_ricardi.service.api.CharacterApi
import kotlinx.coroutines.Deferred
import retrofit2.Response

class CharacterRepository(val characterApi: CharacterApi): CharacterRepositoryContract{

    override suspend fun getAllCharacters(offset: Int): Response<CharcterResponseModel> {
        return characterApi.getCharacters(offset.toString())
    }

}