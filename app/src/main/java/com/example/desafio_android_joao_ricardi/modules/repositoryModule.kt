package com.example.desafio_android_joao_ricardi.modules

import com.example.desafio_android_joao_ricardi.service.repositories.character.CharacterRepository
import com.example.desafio_android_joao_ricardi.service.repositories.character.CharacterRepositoryContract
import com.example.desafio_android_joao_ricardi.service.repositories.comic.ComicRepository
import com.example.desafio_android_joao_ricardi.service.repositories.comic.ComicRepositoryContract
import org.koin.dsl.module.module

val repositoryModule = module {

    single<CharacterRepositoryContract> { CharacterRepository(get()) as CharacterRepositoryContract}
    single<ComicRepositoryContract> { ComicRepository(get()) as ComicRepositoryContract}

}