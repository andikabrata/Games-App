package com.example.gameapp.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gameapp.core.db.dao.GamesFavoriteDao
import com.example.gameapp.core.db.entity.GamesFavoriteEntity

/**
 * @author Andika Bratadirja
 * @date 19/05/2024
 */
/*@Database(entities = [
    GamesFavoriteEntity::class
], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "games-db"
    }

    abstract fun gamesFavoriteDao(): GamesFavoriteDao
}*/

@Database(entities = [GamesFavoriteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gamesFavoriteDao(): GamesFavoriteDao
}
