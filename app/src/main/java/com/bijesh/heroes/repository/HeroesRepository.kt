package com.bijesh.heroes.repository

import com.bijesh.heroes.api.HeroesService
import com.bijesh.heroes.model.HeroesResponse

class HeroesRepository(val heroesService: HeroesService = HeroesService()) {

    private var cachedHeroes = listOf<HeroesResponse>()

    companion object {
        @Volatile
        private var instance: HeroesRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: HeroesRepository().also { instance = it }
        }
    }

    suspend fun getAllHeroesList(): List<HeroesResponse> {
        cachedHeroes = heroesService.getAllHeroesList()
        return cachedHeroes
    }

    fun getHeroListFilteredByPublisher(publisher: String?): List<HeroesResponse> {
        return cachedHeroes.filter { it.biography?.publisher == publisher }
    }

    fun getHeroDetails(heroId: Int?): HeroesResponse? {
        return cachedHeroes.find { it.id == heroId }
    }

}