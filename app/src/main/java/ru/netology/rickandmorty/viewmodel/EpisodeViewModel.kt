package ru.netology.rickandmorty.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.netology.rickandmorty.dto.Episode
import ru.netology.rickandmorty.repository.EpisodeRepository
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    repo: EpisodeRepository
) : ViewModel() {

    val data: Flow<PagingData<Episode>> = repo.data.flowOn(Dispatchers.Default)

}