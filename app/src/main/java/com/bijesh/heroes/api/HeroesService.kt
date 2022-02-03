package com.bijesh.heroes.api

import com.bijesh.heroes.model.HeroesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HeroesService {
    lateinit var api: HeroesApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://akabab.github.io/superhero-api/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(HeroesApi::class.java)
    }

    suspend fun getAllHeroesList(): List<HeroesResponse> {
        return api.getAllHeroesList()
    }
}