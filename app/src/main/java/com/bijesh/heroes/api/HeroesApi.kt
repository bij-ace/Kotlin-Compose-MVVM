package com.bijesh.heroes.api

import com.bijesh.heroes.model.HeroesResponse
import retrofit2.http.GET

interface HeroesApi {
    @GET("all.json")
    suspend fun getAllHeroesList(): List<HeroesResponse>
}