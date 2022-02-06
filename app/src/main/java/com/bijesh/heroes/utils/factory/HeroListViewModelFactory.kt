package com.bijesh.heroes.utils.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bijesh.heroes.repository.HeroesRepository
import com.bijesh.heroes.ui.composables.herolist.HeroListViewModel

class HeroListViewModelFactory(private val repo: HeroesRepository, private val application: Application): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HeroListViewModel(repo, application) as T
    }

}