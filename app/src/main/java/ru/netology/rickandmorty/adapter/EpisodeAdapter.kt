package ru.netology.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.netology.rickandmorty.databinding.CardEpisodeBinding
import ru.netology.rickandmorty.dto.Episode


interface EpisodeCallback {
    fun onDetails(episode: Episode)
}

class EpisodeAdapter(
    private val episodeCallback: EpisodeCallback
) :
    PagingDataAdapter<Episode, RecyclerView.ViewHolder>(EpisodeDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding = CardEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding, episodeCallback)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val episode = getItem(position)
        if (episode != null) {
            (holder as EpisodeViewHolder).bind(episode)
        } else {
            error("Unknown view type")
        }
    }
}

class EpisodeViewHolder(
    private val binding: CardEpisodeBinding,
    private val callback: EpisodeCallback
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(episode: Episode) {

        with(binding) {
            nameEpisode.text = episode.name
            episodeEdite.text = episode.episode
            airDateEdite.text = episode.airDate

            cardEpisode.setOnClickListener {
                callback.onDetails(episode)
            }
        }
    }
}

class EpisodeDiffCallback : DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem == newItem
    }
}