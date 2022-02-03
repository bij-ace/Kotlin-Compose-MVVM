package com.bijesh.heroes.ui.composables.herodetails

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bijesh.heroes.model.HeroesResponse
import com.bijesh.heroes.repository.HeroesRepository

class HeroDetailsViewModel(private val repo: HeroesRepository = HeroesRepository.getInstance()): ViewModel() {

    val heroState = mutableStateOf(HeroesResponse())

    fun getHeroDetails(heroId: Int?) {
        heroState.value = repo.getHeroDetails(heroId)?:HeroesResponse()
    }

}