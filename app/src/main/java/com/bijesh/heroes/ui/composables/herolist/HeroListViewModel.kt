package com.bijesh.heroes.ui.composables.herolist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bijesh.heroes.model.HeroesResponse
import com.bijesh.heroes.repository.HeroesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HeroListViewModel(private val repo: HeroesRepository = HeroesRepository.getInstance()): ViewModel() {

    val heroesState = mutableStateOf(emptyList<HeroesResponse>())

    init {
        getAllHeroesList()
    }

    private fun getAllHeroesList() {
        viewModelScope.launch(Dispatchers.IO) {
            val heroes = repo.getAllHeroesList()
            heroesState.value = heroes
        }
    }

    fun filterHeroesList(keyword: String) {
        if (keyword == "") {
            getAllHeroesList()
        } else {
            heroesState.value = heroesState.value.filter { it ->
                it.name.contains(keyword)
            }
        }
    }

    fun getHeroListFilteredByPublisher(publisher: String?) {
        heroesState.value = repo.getHeroListFilteredByPublisher(publisher = publisher)
    }

}