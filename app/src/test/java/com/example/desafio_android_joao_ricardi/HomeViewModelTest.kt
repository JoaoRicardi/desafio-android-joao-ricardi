package com.example.desafio_android_joao_ricardi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.desafio_android_joao_ricardi.feature.home.HomeViewModel
import com.example.desafio_android_joao_ricardi.models.characters.CharacterResponseDataModel
import com.example.desafio_android_joao_ricardi.models.characters.CharcterResponseModel
import com.example.desafio_android_joao_ricardi.service.repositories.character.CharacterRepositoryContract
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@RunWith(MockitoJUnitRunner.Silent::class)
@ExperimentalCoroutinesApi
class TestClass {

    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var characterRepositoryContract: CharacterRepositoryContract


    @Before
    internal fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        homeViewModel = HomeViewModel(characterRepositoryContract)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getUCharacterList() = TestCoroutineDispatcher().runBlockingTest {
        val expected = listOf(HomeViewModel.ScreenState.Loading, HomeViewModel.ScreenState.Loaded(emptyList()))
        val actual = mutableListOf<HomeViewModel.ScreenState>()

        Mockito.`when`(characterRepositoryContract.getAllCharacters(0))
            .thenReturn(Response.success(mockedCharacterResponse()))

        homeViewModel.state.observeForever {
            actual.add(it)
        }

        homeViewModel.getAllCharacters(0)
        assertEquals(expected,actual)

    }


    private fun mockedCharacterResponse(): CharcterResponseModel {
        return  CharcterResponseModel(
            data = CharacterResponseDataModel(
                results = emptyList()
        ))
    }
}