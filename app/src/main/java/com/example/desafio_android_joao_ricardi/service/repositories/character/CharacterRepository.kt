package com.example.desafio_android_joao_ricardi.service.repositories.character

import com.example.desafio_android_joao_ricardi.models.characters.CharcterResponseModel
import com.example.desafio_android_joao_ricardi.service.api.CharacterApi
import kotlinx.coroutines.Deferred

class CharacterRepository(val characterApi: CharacterApi): CharacterRepositoryContract{

    override fun getAllCharacters(offset: Int): Deferred<CharcterResponseModel> {
        return characterApi.getCharacters(offset.toString())
    }

}