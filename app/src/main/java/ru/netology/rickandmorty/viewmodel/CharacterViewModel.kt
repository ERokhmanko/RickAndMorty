package ru.netology.rickandmorty.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.netology.rickandmorty.dto.Character
import ru.netology.rickandmorty.repository.CharacterRepository
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
     repo: CharacterRepository
) : ViewModel() {

    val data: Flow<PagingData<Character>> = repo.data.flowOn(Dispatchers.Default)

}