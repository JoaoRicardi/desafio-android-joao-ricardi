package com.example.desafio_android_joao_ricardi.service.repositories.comic

import com.example.desafio_android_joao_ricardi.models.comics.ComicsResponseModel
import com.example.desafio_android_joao_ricardi.service.api.ComicApi
import kotlinx.coroutines.Deferred

class ComicRepository(private val comicApi: ComicApi): ComicRepositoryContract{
    override fun getAllComics(id: Int): Deferred<ComicsResponseModel> {
        return comicApi.getAllComics(id)
    }

}