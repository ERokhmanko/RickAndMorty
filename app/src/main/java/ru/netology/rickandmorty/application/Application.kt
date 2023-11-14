package ru.netology.rickandmorty.application

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import dagger.Lazy
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class Application : Application() {

    @Inject
    lateinit var workerFactory: Lazy<HiltWorkerFactory>

}