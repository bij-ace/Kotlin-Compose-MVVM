package com.bijesh.heroes.utils.factory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.bijesh.heroes.repository.HeroesRepository

class ViewModelInjector(application: Application) : AndroidViewModel(application) {

    fun provideHeroListViewModelFactory(): HeroListViewModelFactory =
        HeroListViewModelFactory(
            HeroesRepository.getInstance(),
            getApplication()
        )

    fun provideSearchViewModelFactory(): SearchViewModelFactory =
        SearchViewModelFactory(
            HeroesRepository.getInstance(),
            getApplication()
        )
}