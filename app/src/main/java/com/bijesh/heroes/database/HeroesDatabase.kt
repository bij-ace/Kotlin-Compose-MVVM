package com.bijesh.heroes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bijesh.heroes.model.HeroesResponse
import com.bijesh.heroes.utils.typeconverters.ListStringConverter

@Database(
    version = 1,
    entities = [
        HeroesResponse::class
    ]
)
@TypeConverters(ListStringConverter::class)
abstract class HeroesDatabase : RoomDatabase() {

    abstract val heroesDao: HeroesDao

    companion object {
        @Volatile
        private var instance: HeroesDatabase? = null

        fun getDatabaseInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: Room
                .databaseBuilder(
                    context.applicationContext,
                    HeroesDatabase::class.java,
                    "heroes_database"
                )
                .build()
                .also { instance = it }
        }
    }

}