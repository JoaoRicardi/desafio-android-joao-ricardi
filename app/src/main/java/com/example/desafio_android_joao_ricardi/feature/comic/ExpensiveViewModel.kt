package com.example.desafio_android_joao_ricardi.feature.comic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio_android_joao_ricardi.feature.home.HomeViewModel
import com.example.desafio_android_joao_ricardi.models.characters.CharacterModel
import com.example.desafio_android_joao_ricardi.models.comics.ComicModel
import com.example.desafio_android_joao_ricardi.models.comics.ComicsResponseModel
import com.example.desafio_android_joao_ricardi.service.repositories.comic.ComicRepository
import com.example.desafio_android_joao_ricardi.service.repositories.comic.ComicRepositoryContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ExpensiveViewModel (
    val comicRepository: ComicRepositoryContract
): ViewModel(){

    private val _state = MutableLiveData<ExpensiveViewModel.ScreenState>()
    val state: LiveData<ExpensiveViewModel.ScreenState>
        get() = _state

    private val _mostExpensiveComic = MutableLiveData<ComicModel>()
    val comic: LiveData<ComicModel>
        get() = _mostExpensiveComic



    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    fun getMostExpensiveComicBook(id: Int){
        _state.postValue(ScreenState.Loading)
        coroutineScope.launch {
            val deferedComics = comicRepository.getAllComics(id)

            try{
                val comicsReponseModel = deferedComics.await()
                getFilterfromResponse(comicsReponseModel)
                _state.postValue(ScreenState.Loaded)

            }catch (e: Exception){

                println(e)
                _state.postValue(ScreenState.Error(e.message ?: "Erro"))
            }
        }

    }


    private fun getFilterfromResponse(response: ComicsResponseModel){
        response.data.results.map {
            if(_mostExpensiveComic.value == null){
                _mostExpensiveComic.value = it
            }
            else if(_mostExpensiveComic.value!!.getMostExpensive() > it.getMostExpensive()){
                _mostExpensiveComic.value = it
            }
        }
    }



    sealed class ScreenState {
        object Loading: ScreenState()
        object Loaded: ScreenState()
        data class Error(val error: String): ScreenState()
    }


}