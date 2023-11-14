package ru.netology.rickandmorty.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.netology.rickandmorty.R
import ru.netology.rickandmorty.adapter.CharacterAdapter
import ru.netology.rickandmorty.adapter.CharacterCallback
import ru.netology.rickandmorty.databinding.FragmentCharactersBinding
import ru.netology.rickandmorty.dto.Character
import ru.netology.rickandmorty.viewmodel.CharacterViewModel

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
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
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)

        val viewModel: CharacterViewModel by activityViewModels()


        val adapter = CharacterAdapter(
            object : CharacterCallback {
                override fun onDetails(character: Character) {
                    TODO("Not yet implemented")
                }
            }
        )

        val gridLayoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)

        binding.listCharacter.adapter = adapter
        binding.listCharacter.layoutManager = gridLayoutManager


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