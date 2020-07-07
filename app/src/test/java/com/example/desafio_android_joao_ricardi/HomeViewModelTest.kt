package com.example.desafio_android_joao_ricardi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.desafio_android_joao_ricardi.feature.home.HomeViewModel
import com.example.desafio_android_joao_ricardi.models.characters.CharacterResponseDataModel
import com.example.desafio_android_joao_ricardi.models.characters.CharcterResponseModel
import com.example.desafio_android_joao_ricardi.service.api.CharacterApi
import com.example.desafio_android_joao_ricardi.service.repositories.character.CharacterRepository
import com.example.desafio_android_joao_ricardi.service.repositories.character.CharacterRepositoryContract
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner.Silent::class)
@ExperimentalCoroutinesApi
class HomeViewModelTest {


    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private var characterRepositoryContract: CharacterRepositoryContract = Mockito.mock(CharacterRepositoryContract::class.java)


    @Before
    internal fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        homeViewModel = HomeViewModel()
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /*@Test
fun `when getWeather is called with valid location, then observer is updated with success`() = runBlocking {
    viewModel.weather.observeForever(weatherObserver)
    viewModel.getWeather(validLocation)
    delay(10)
    verify(weatherRepository).getWeather(validLocation)
    verify(weatherObserver, timeout(50)).onChanged(Resource.loading(null))
    verify(weatherObserver, timeout(50)).onChanged(successResource)
}*/

    @Test
    fun getCharacterListTest() = TestCoroutineScope().runBlockingTest {
        val actual = mutableListOf<HomeViewModel.ScreenState>()
        val expected = listOf<HomeViewModel.ScreenState>()
        homeViewModel.state.observeForever {
            actual.add(it)
        }

        Mockito.`when`(characterRepositoryContract.getAllCharacters(1))
            .thenReturn(async { mockedCharacterResponse() })

        assertEquals(expected,actual)


    }
//        runBlocking {

//    }


//    @Test
//    fun `should emitt when request is sucessfull`(){
//        val expected = listOf<HomeViewModel.ScreenState>(HomeViewModel.ScreenState.Loading, HomeViewModel.ScreenState.Loaded(emptyList()))
//        val actual = mutableListOf<HomeViewModel.ScreenState>()
//
//        runBlocking {
//            whenever(characterRepositoryContract.getAllCharacters(0))
//                .thenReturn(mockedCharacterResponse())
//
//            homeViewModel.state.observeForever {state->
//                actual.add(state)
//            }
//
//            homeViewModel.getAllCharacters(1)
//            assertEquals(expected, actual)
//
//
//        }
//
//    }


    private fun mockedCharacterResponse(): CharcterResponseModel {
        return  CharcterResponseModel(
            data = CharacterResponseDataModel(
                results = emptyList()
        ))
    }


}