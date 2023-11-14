package ru.netology.rickandmorty.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.netology.rickandmorty.dao.CharacterDao
import ru.netology.rickandmorty.dao.NextPageCharacterDao
import ru.netology.rickandmorty.db.AppDb
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DbModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): AppDb =
        Room.databaseBuilder(context, AppDb::class.java, "app.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providesCharacterDao(appDb: AppDb): CharacterDao = appDb.characterDao()

    @Provides
    fun providesNextPageCharacterDao(appDb: AppDb): NextPageCharacterDao = appDb.nextPageCharacterDao()
}