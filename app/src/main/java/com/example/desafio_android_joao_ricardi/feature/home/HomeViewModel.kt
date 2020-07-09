package com.example.desafio_android_joao_ricardi.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_android_joao_ricardi.models.characters.CharacterModel
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


    fun getAllCharacters(offset: Int){
        _state.value = ScreenState.Loading

        viewModelScope.launch(Dispatchers.IO) {
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


    //todo exemplo 1 de navegacao
    private val _navigateToSelect = MutableLiveData<CharacterModel>()
    val navigateToSelect: LiveData<CharacterModel>
        get() = _navigateToSelect

    fun showCharcterDetail(characterModel: CharacterModel){
        _navigateToSelect.value = characterModel
    }

    fun clearewsDetailNavigate(){
        //todo do exemplo 2
        _navigateToSelect.value = null
        //todo do exemplo 1
        _event.postValue(null)
    }

    //todo exemplo 2
    private val _event = MutableLiveData<SideEffect>()
    val event: LiveData<SideEffect>
        get() = _event

    fun navigaetToDetail(characterModel: CharacterModel){
        _event.postValue(SideEffect.NavigateToDetail(characterModel))
    }



    sealed class ScreenState {
        object Loading: ScreenState()
        data class Loaded(val value:List<CharacterModel>): ScreenState()
        data class Error(val error: String): ScreenState()
    }


    sealed class SideEffect{
        data class NavigateToDetail(val characterModel: CharacterModel): SideEffect()
    }
}