package com.example.gameapp.core.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gameapp.core.db.entity.GamesFavoriteEntity

/**
 * @author Andika Bratadirja
 * @date 19/05/2024
 */
@Dao
interface GamesFavoriteDao {
    @Query("SELECT * FROM games_favorite")
    fun getAll(): LiveData<List<GamesFavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg records: GamesFavoriteEntity)

    @Delete
    fun delete(record: GamesFavoriteEntity)

    @Query("DELETE FROM games_favorite WHERE idGame IN (:ids)")
    fun deleteItemByIds(ids: Int)
}