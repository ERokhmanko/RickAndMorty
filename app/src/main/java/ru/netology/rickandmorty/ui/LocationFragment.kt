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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.netology.rickandmorty.R
import ru.netology.rickandmorty.adapter.LocationAdapter
import ru.netology.rickandmorty.adapter.LocationCallback
import ru.netology.rickandmorty.databinding.FragmentLocationsBinding
import ru.netology.rickandmorty.dto.Location
import ru.netology.rickandmorty.viewmodel.LocationViewModel

@AndroidEntryPoint
class LocationFragment : Fragment() {
    private var _binding: FragmentLocationsBinding? = null
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
        _binding = FragmentLocationsBinding.inflate(inflater, container, false)

        val viewModel: LocationViewModel by viewModels()

        val adapter = LocationAdapter(
            object : LocationCallback {
                override fun onDetails(location: Location) {
                    TODO("Not yet implemented")
                }
            }
        )

        val gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)


        binding.listLocations.adapter = adapter
        binding.listLocations.layoutManager = gridLayoutManager

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