package com.bijesh.heroes.repository

import android.app.Application
import com.bijesh.heroes.api.HeroesService
import com.bijesh.heroes.database.HeroesDatabase
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

    suspend fun getAllHeroesList(application: Application): List<HeroesResponse> {
        val dao = HeroesDatabase.getDatabaseInstance(application).heroesDao
        if (dao.getAllHeroes().isEmpty()) {
            cachedHeroes = heroesService.getAllHeroesList()
            for (hero in cachedHeroes) {
                dao.insertHeroes(hero)
            }
        }
        cachedHeroes = dao.getAllHeroes()
        return cachedHeroes
    }

    fun getHeroListFilteredByPublisher(publisher: String?): List<HeroesResponse> {
        return cachedHeroes.filter { it.biography?.publisher == publisher }
    }

    fun getHeroDetails(heroId: Int?): HeroesResponse? {
        return cachedHeroes.find { it.id == heroId }
    }

}