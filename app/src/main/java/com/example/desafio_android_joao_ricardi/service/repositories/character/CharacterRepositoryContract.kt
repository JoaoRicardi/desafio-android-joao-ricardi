package com.example.desafio_android_joao_ricardi.service.repositories.character

import com.example.desafio_android_joao_ricardi.models.characters.CharcterResponseModel
import kotlinx.coroutines.Deferred

interface CharacterRepositoryContract {
    fun getAllCharacters(offset: Int): Deferred<CharcterResponseModel>
}