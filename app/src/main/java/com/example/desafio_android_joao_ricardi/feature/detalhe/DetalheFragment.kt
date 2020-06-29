package com.example.desafio_android_joao_ricardi.feature.detalhe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.desafio_android_joao_ricardi.R
import com.example.desafio_android_joao_ricardi.models.characters.CharacterModel
import kotlinx.android.synthetic.main.character_home_list_item.view.*
import kotlinx.android.synthetic.main.fragment_detalhe.*

class DetalheFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detalhe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var args = DetalheFragmentArgs.fromBundle(requireArguments())

        Glide.with(requireView())
            .load(args.characterModel.thumbnail.getRealPath())
            .into(characterImageId)
        characterNameDetalheId.text = args.characterModel.name
        descriptionTextViewId.text = args.characterModel.description
        buttonExpensiveId.setOnClickListener {
            Navigation.findNavController(it).navigate(DetalheFragmentDirections.detalheToExpensive(args.characterModel.id))
        }
    }

}