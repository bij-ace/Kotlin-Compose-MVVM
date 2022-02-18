package com.bijesh.heroes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bijesh.heroes.model.HeroesResponse

@Dao
interface HeroesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateHeroes(heroes: HeroesResponse): Long

    @Query("SELECT * FROM heroes")
    suspend fun getAllHeroes(): List<HeroesResponse>

    @Query("SELECT * FROM heroes WHERE name LIKE '%'||:param||'%'")
    suspend fun filterHeroesList(param: String): List<HeroesResponse>

}