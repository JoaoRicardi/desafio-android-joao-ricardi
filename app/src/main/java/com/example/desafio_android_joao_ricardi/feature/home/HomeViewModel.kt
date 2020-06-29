package com.example.desafio_android_joao_ricardi.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio_android_joao_ricardi.models.characters.CharacterModel
import com.example.desafio_android_joao_ricardi.service.repositories.character.CharacterRepository
import com.example.desafio_android_joao_ricardi.service.repositories.character.CharacterRepositoryContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(
    private var characterRepository: CharacterRepositoryContract
) : ViewModel(){

    private val _state = MutableLiveData<ScreenState>()
    val state: LiveData<ScreenState>
        get() = _state

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _page = MutableLiveData<Int>()

    init {
        getAllCharacters( 0)
        _page.postValue(0)
    }

    fun updatePage(){
        _page.value = _page.value!!.plus(1)
        getAllCharacters(_page.value!!)
    }


    private fun getAllCharacters(offset: Int){
        _state.postValue(ScreenState.Loading)
        coroutineScope.launch {
            val deferedCharacters = characterRepository.getAllCharacters(offset)
            try{
                val responseDef = deferedCharacters.await()
                _state.postValue(ScreenState.Loaded(responseDef.data.results))
            }catch (e: Exception){
                _state.postValue(ScreenState.Error(e.message ?: "Erro"))
            }
        }


    }


    sealed class ScreenState {
        object Loading: ScreenState()
        data class Loaded(val value:List<CharacterModel>): ScreenState()
        data class Error(val error: String): ScreenState()
    }
}