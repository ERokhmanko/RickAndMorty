package ru.netology.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.netology.rickandmorty.databinding.CardCharacterBinding
import ru.netology.rickandmorty.dto.Character
import ru.netology.rickandmorty.utils.Utils

interface CharacterCallback {
    fun onDetails(character: Character)
}

class CharacterAdapter(
    private val characterCallback: CharacterCallback

) :
    PagingDataAdapter<Character, RecyclerView.ViewHolder>(CharacterDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            CardCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, characterCallback)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val character = getItem(position)
        if (character != null) {
            (holder as CharacterViewHolder).bind(character)
        } else {
            error("Unknown view type")
        }
    }
}


class CharacterViewHolder(
    private val binding: CardCharacterBinding,
    private val characterCallback: CharacterCallback
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character) {

        with(binding) {
            nameCharacterEdite.text = character.name
            Utils.uploadingAvatar(avatarCharacter, character.image)
            speciesEdite.text = character.species
            statusEdite.text = character.status
            genderEdite.text = character.gender

            cardCharacter.setOnClickListener {
                characterCallback.onDetails(character)
            }
        }
    }
}


class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}