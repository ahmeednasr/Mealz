package com.example.mealzapp.di

import com.example.data.reomte.ApiService
import com.example.data.repo.MealsRepoImpl
import com.example.domain.repo.MealsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun provideRepo(apiService: ApiService): MealsRepo {
        return MealsRepoImpl(apiService)
    }
}