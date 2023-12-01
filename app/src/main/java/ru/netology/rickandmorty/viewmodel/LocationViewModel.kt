package ru.netology.rickandmorty.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.netology.rickandmorty.dto.Location
import ru.netology.rickandmorty.repository.LocationRepository
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    repo: LocationRepository
) : ViewModel() {

    val data: Flow<PagingData<Location>> = repo.data.flowOn(Dispatchers.Default)

}