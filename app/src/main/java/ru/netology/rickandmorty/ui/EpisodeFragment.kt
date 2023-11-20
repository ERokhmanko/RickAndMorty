package ru.netology.rickandmorty.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.netology.rickandmorty.R
import ru.netology.rickandmorty.adapter.EpisodeAdapter
import ru.netology.rickandmorty.adapter.EpisodeCallback
import ru.netology.rickandmorty.databinding.FragmentEpisodesBinding
import ru.netology.rickandmorty.dto.Episode
import ru.netology.rickandmorty.viewmodel.EpisodeViewModel

@AndroidEntryPoint
class EpisodeFragment : Fragment() {
    private var _binding: FragmentEpisodesBinding? = null
    private val binding get() = _binding!!

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "inflater.inflate(R.menu.menu_list_object, menu)",
            "ru.netology.rickandmorty.R"
        )
    )
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list_object, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {
                //TODO
                return true
            }

            R.id.filter -> {
                //TODO
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodesBinding.inflate(inflater, container, false)

        val viewModel: EpisodeViewModel by viewModels()

        val adapter = EpisodeAdapter(
            object : EpisodeCallback {
                override fun onDetails(episode: Episode) {
                    TODO("Not yet implemented")
                }
            }
        )

        val gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)

        binding.listEpisodes.adapter = adapter
        binding.listEpisodes.layoutManager = gridLayoutManager

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.data.collect {
                    adapter.submitData(it)
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}