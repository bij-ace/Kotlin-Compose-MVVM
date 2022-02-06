package com.bijesh.heroes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bijesh.heroes.model.HeroesResponse

@Dao
interface HeroesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroes(heores: HeroesResponse)

    @Query("SELECT * FROM heroes")
    suspend fun getAllHeroes(): List<HeroesResponse>

}