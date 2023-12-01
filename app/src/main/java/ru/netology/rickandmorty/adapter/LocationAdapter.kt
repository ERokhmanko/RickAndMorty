package ru.netology.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.netology.rickandmorty.databinding.CardLocationBinding
import ru.netology.rickandmorty.dto.Location

interface LocationCallback {
    fun onDetails(location: Location)
}

class LocationAdapter(
    private val locationCallback: LocationCallback
) :
    PagingDataAdapter<Location, RecyclerView.ViewHolder>(
        LocationDiffCallback()
    ) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val location = getItem(position)
        if (location != null) {
            (holder as LocationViewHolder).bind(location)
        } else {
            error("Unknown view type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding =
            CardLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding, locationCallback)
    }
}

class LocationViewHolder(
    private val binding: CardLocationBinding,
    private val locationCallback: LocationCallback
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(location: Location) {
        with(binding) {
            nameLocation.text = location.name
            typeEdite.text = location.type
            dimensionEdite.text = location.dimension

            cardLocation.setOnClickListener {
                locationCallback.onDetails(location)
            }
        }
    }
}

class LocationDiffCallback : DiffUtil.ItemCallback<Location>() {
    override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem == newItem
    }

}