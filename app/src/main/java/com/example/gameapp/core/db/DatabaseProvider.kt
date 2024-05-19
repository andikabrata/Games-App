package com.example.gameapp.core.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * @author Andika Bratadirja
 * @date 19/05/2024
 */
class DatabaseProvider(private val context: Context) {

    private var database: AppDatabase? = null
    /**
     * Gets an instance of [AppDatabase].
     *
     * @return an instance of [AppDatabase]
     */
    fun getInstance(): AppDatabase =
        database ?: synchronized(this) {
            database ?: buildDatabase().also { database = it }
        }

    private fun buildDatabase(): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "games_db")
            .addCallback(onCreateDatabase())
            .fallbackToDestructiveMigration()
            .build()

    private fun onCreateDatabase() = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
        }
    }
}