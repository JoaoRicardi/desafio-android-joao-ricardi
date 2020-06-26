package com.example.desafio_android_joao_ricardi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.desafio_android_joao_ricardi.feature.home.HomeViewModel
import com.example.desafio_android_joao_ricardi.models.characters.CharacterResponseDataModel
import com.example.desafio_android_joao_ricardi.models.characters.CharcterResponseModel
import com.example.desafio_android_joao_ricardi.service.repositories.character.CharacterRepositoryContract
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private var characterRepositoryContract: CharacterRepositoryContract = Mockito.mock(CharacterRepositoryContract::class.java)


    @Before
    internal fun setUp() {
        homeViewModel = HomeViewModel(characterRepositoryContract)
        MockitoAnnotations.initMocks(this)

    }

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should emitt when request is sucessfull`(){
        val expected = listOf<HomeViewModel.ScreenState>(HomeViewModel.ScreenState.Loading, HomeViewModel.ScreenState.Loaded(emptyList()))
        val actual = mutableListOf<HomeViewModel.ScreenState>()

        runBlocking {
            whenever(characterRepositoryContract.getAllCharacters(0).await())
                .thenReturn(mockedCharacterResponse())

        }

        homeViewModel.state.observeForever {state->
            actual.add(state)
        }

        homeViewModel.updatePage()

        assertEquals(expected, actual)


    }


    private fun mockedCharacterResponse(): CharcterResponseModel {
        return  CharcterResponseModel(
            data = CharacterResponseDataModel(
                results = emptyList()
            )
        )
    }


}