package com.example.desafio_android_joao_ricardi.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_joao_ricardi.R
import com.example.desafio_android_joao_ricardi.feature.home.adapters.CharacterRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by inject()
    //private lateinit var scrollListener: RecyclerView.OnScrollListener
    private lateinit var manager: LinearLayoutManager

    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manager = LinearLayoutManager(context)
        val characterAdapter = CharacterRecyclerViewAdapter(CharacterRecyclerViewAdapter.OnClickListener{
            Navigation.findNavController(this.requireView()).navigate(HomeFragmentDirections.actionHomeFragmentToDetalheFragment(it))
        })

        btnGetCharactersId.setOnClickListener { homeViewModel.getAllCharacters(1) }

        with(characterRvId){
            layoutManager = manager
            adapter = characterAdapter
        }

//        characterRvId.addOnScrollListener(object : RecyclerView.OnScrollListener(){
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                var currentItens:Int =  manager.childCount
//                var totalItens:Int = manager.itemCount
//                var scrollOutItens:Int = manager.findFirstVisibleItemPosition()
//
//                if(isScrolling && (currentItens + scrollOutItens == totalItens)){
//                    isScrolling = false
//                    homeViewModel.updatePage()
//                }
//
//            }
//
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
//                    isScrolling = true
//                }
//            }
//        })

        setUpState(characterAdapter)
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
}
