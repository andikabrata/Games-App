package com.example.gameapp.core.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Andika Bratadirja
 * @date 19/05/2024
 */
@Entity(tableName = "games_favorite")
data class GamesFavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = "idGame") var idGame: Int?,
    @ColumnInfo(name = "game_name") var gameName: String?,
    @ColumnInfo(name = "release_date") var releaseDate: String?,
    @ColumnInfo(name = "genres") var genres: String?,
    @ColumnInfo(name = "image") var image: String?
)