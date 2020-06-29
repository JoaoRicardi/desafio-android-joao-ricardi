package com.example.desafio_android_joao_ricardi.models.characters

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CharacterModel (
    val id: Int,
    val name: String,
    val thumbnail: Thumbnail,
    val description: String

):Parcelable

@Parcelize
class Thumbnail(
    val path: String,
    val extension: String
): Parcelable{

    fun getRealPath(): String = this.path +".${this.extension}"
}
