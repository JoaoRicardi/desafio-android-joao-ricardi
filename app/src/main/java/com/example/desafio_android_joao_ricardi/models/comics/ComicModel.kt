package com.example.desafio_android_joao_ricardi.models.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.desafio_android_joao_ricardi.models.characters.Thumbnail

class ComicModel(

    val title: String?,
    val description: String?,
    val prices: List<Price>,
    val thumbnail: Thumbnail
){
    fun getMostExpensive(): Double {
        var currentList = mutableListOf<Double>()

        this.prices.map {
            currentList.add(it.price)
        }

        return currentList.max() ?: 0.0

    }
}

class Price(
    val price: Double
)