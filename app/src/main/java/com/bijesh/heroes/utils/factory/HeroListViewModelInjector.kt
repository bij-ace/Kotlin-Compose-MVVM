package com.bijesh.heroes.utils.factory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.bijesh.heroes.repository.HeroesRepository

class HeroListViewModelInjector(application: Application) : AndroidViewModel(application) {

    fun provideHeroListViewModelFactory(): HeroListViewModelFactory =
        HeroListViewModelFactory(
            HeroesRepository.getInstance(),
            getApplication()
        )
}