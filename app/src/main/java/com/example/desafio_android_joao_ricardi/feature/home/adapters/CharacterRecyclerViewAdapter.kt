package com.example.desafio_android_joao_ricardi.feature.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafio_android_joao_ricardi.R
import com.example.desafio_android_joao_ricardi.models.characters.CharacterModel
import kotlinx.android.synthetic.main.character_home_list_item.view.*

class CharacterRecyclerViewAdapter(private val onClick:OnClickListener): RecyclerView.Adapter<CharacterRecyclerViewAdapter.ViewHolder>(){

    var characterList = listOf<CharacterModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun updateList(value: List<CharacterModel>){
        var newList: MutableList<CharacterModel> = value as MutableList<CharacterModel>

        newList.addAll(0, characterList)
        newList.addAll(0,value)

        characterList = newList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.character_home_list_item,parent, false)

        return  ViewHolder(view)
    }

    override fun getItemCount(): Int = characterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characterList[position]

        holder.itemView.setOnClickListener {
            onClick.onClick(character)
        }

        holder.bind(character)
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(character: CharacterModel){

            Glide.with(itemView.context)
                .load(character.thumbnail.getRealPath())
                .into(itemView.characterImageId)

            itemView.characterTextTitleId.text = character.name
        }

    }

    class OnClickListener(val clickListener: (character: CharacterModel) -> Unit){
        fun onClick(character: CharacterModel) = clickListener(character)
    }


}