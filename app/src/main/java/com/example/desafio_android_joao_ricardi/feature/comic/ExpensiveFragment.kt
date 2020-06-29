package com.example.desafio_android_joao_ricardi.feature.comic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.desafio_android_joao_ricardi.R
import kotlinx.android.synthetic.main.fragment_detalhe.*
import kotlinx.android.synthetic.main.fragment_expensive.*
import kotlinx.android.synthetic.main.fragment_expensive.descriptionTextViewId
import org.koin.android.ext.android.inject

class ExpensiveFragment : Fragment() {

    private val expensiveViewModel: ExpensiveViewModel by inject()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_expensive, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var args = ExpensiveFragmentArgs.fromBundle(requireArguments())

        expensiveViewModel.getMostExpensiveComicBook(args.id)
        setUpState()
    }

    private fun setUpState(){
        expensiveViewModel.state.observeForever {state->
            when(state){
                is ExpensiveViewModel.ScreenState.Loaded -> {
                    expensiveProgressId.visibility = View.GONE
                    constraintComicId.visibility = View.VISIBLE
                    setUpComic()
                }

                is ExpensiveViewModel.ScreenState.Error -> {
                    println("Error")
                    println(state.error)
                }

                is ExpensiveViewModel.ScreenState.Loading -> {
                    expensiveProgressId.visibility = View.VISIBLE
                    constraintComicId.visibility = View.GONE

                }

            }
        }
    }

    private fun setUpComic(){
        expensiveViewModel.comic.observeForever {
            println(it)

            comicTitleId.text = it.title
            precoTextId.text = it.getMostExpensive().toString()
            descriptionTextViewId.text = it.description
            Glide.with(requireView())
                .load(it.thumbnail.getRealPath())
                .into(comicImageId)

        }
    }

}