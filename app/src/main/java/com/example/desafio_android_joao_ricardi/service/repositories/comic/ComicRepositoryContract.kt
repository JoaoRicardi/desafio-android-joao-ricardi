package com.example.desafio_android_joao_ricardi.service.repositories.comic

import com.example.desafio_android_joao_ricardi.models.comics.ComicsResponseModel
import kotlinx.coroutines.Deferred

interface ComicRepositoryContract {
    fun getAllComics(id: Int): Deferred<ComicsResponseModel>
}