package com.bijesh.heroes.ui.composables.herolist

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bijesh.heroes.model.HeroesResponse
import com.bijesh.heroes.repository.HeroesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HeroListViewModel(
    private val repo: HeroesRepository,
    private val application: Application
) : ViewModel() {

    val heroesState = mutableStateOf(emptyList<HeroesResponse>())

    init {
        getAllHeroesList()
    }

    fun getAllHeroesList() {
        viewModelScope.launch(Dispatchers.IO) {
            val heroes = repo.getAllHeroesList(application)
            heroesState.value = heroes
        }
    }

    fun getHeroListFilteredByPublisher(publisher: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            heroesState.value = repo.getHeroListFilteredByPublisher(application, publisher = publisher)
        }
    }

    fun favoriteClicked(index: Int, selected: Boolean, hero: HeroesResponse) {
        hero.favorite = selected
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateHeroes(application, hero)
        }
    }

}