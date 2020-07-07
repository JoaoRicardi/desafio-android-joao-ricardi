package com.example.desafio_android_joao_ricardi.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
//        getAllCharacters( 1)
        _page.postValue(0)
    }

    fun updatePage(){
        _page.value = _page.value!!.plus(1)
        getAllCharacters(_page.value!!)
    }


    fun getAllCharacters(offset: Int){
        _state.value = ScreenState.Loading
        coroutineScope.launch {
            val charactersListResponse = characterRepository.getAllCharacters(offset)
            if(charactersListResponse.isSuccessful){
                charactersListResponse.body()?.let {
                    _state.postValue(ScreenState.Loaded(it.data.results))
                }
            }
            else{
                _state.postValue(ScreenState.Error( "Erro"))
            }
        }
    }


    sealed class ScreenState {
        object Loading: ScreenState()
        data class Loaded(val value:List<CharacterModel>): ScreenState()
        data class Error(val error: String): ScreenState()
    }
}