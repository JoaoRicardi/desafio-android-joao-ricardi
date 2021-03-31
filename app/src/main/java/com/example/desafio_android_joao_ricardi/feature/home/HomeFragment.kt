package com.example.desafio_android_joao_ricardi.feature.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_joao_ricardi.R
import com.example.desafio_android_joao_ricardi.feature.home.adapters.CharacterRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by inject()
    private lateinit var manager: LinearLayoutManager

    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manager = LinearLayoutManager(context)
        val characterAdapter = CharacterRecyclerViewAdapter(CharacterRecyclerViewAdapter.OnClickListener{

            //todo exemplo 1
            homeViewModel.showCharcterDetail(it)

            //todo exemplo 2
            homeViewModel.navigaetToDetail(it)
        })

        btnGetCharactersId.setOnClickListener { homeViewModel.getAllCharacters(1) }

        with(characterRvId){
            layoutManager = manager
            adapter = characterAdapter
        }

        setUpState(characterAdapter)
        //todo ver exemplo 1
        setUpNavigationExemplo()
        //todo ver exemplo 2
        sideeeffect()
    }

    private fun setUpState(characterRecyclerViewAdapter: CharacterRecyclerViewAdapter){
        homeViewModel.state.observeForever {state ->
           when(state){

               is HomeViewModel.ScreenState.Loaded -> {
                   progressBarId.visibility = View.GONE
                   characterRvId.visibility = View.VISIBLE
                   if(characterRecyclerViewAdapter.characterList.isEmpty()) {
                       characterRecyclerViewAdapter.characterList = state.value
                   }
                   else{
                       characterRecyclerViewAdapter.updateList(state.value)
                   }
               }

               is HomeViewModel.ScreenState.Error -> {
                   progressBarId.visibility = View.GONE
                   characterRvId.visibility = View.GONE
                   Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
               }

               is HomeViewModel.ScreenState.Loading -> {
                   progressBarId.visibility = View.VISIBLE
                   characterRvId.visibility = View.GONE

               }
           }
        }
    }

    //todo primeiro exemplo de navegcaco
    private fun setUpNavigationExemplo(){
        homeViewModel.navigateToSelect.observe(viewLifecycleOwner, Observer {
            it?.let {
                Navigation.findNavController(this.requireView()).navigate(HomeFragmentDirections.actionHomeFragmentToDetalheFragment(it))
                homeViewModel.clearewsDetailNavigate()
            }
        })
    }

    //todo segundo exemplo de navegacao
    private fun sideeeffect(){
        homeViewModel.event.observe(viewLifecycleOwner, Observer {
            it?.let {
                when(it){
                    is HomeViewModel.SideEffect.NavigateToDetail -> {
                        Navigation.findNavController(this.requireView()).navigate(HomeFragmentDirections.actionHomeFragmentToDetalheFragment(it.characterModel))
                    }
                }
            }
        })
    }

}
