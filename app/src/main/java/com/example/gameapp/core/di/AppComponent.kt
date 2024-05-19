package com.example.gameapp.core.di

import com.example.gameapp.feature.detail_movie.view_model.detailGamesModule
import com.example.gameapp.feature.favorite_games.view_model.favoriteGamesModule
import com.example.gameapp.feature.main.view_model.gamesModule

/**
 * @author Andika Bratadirja
 * @date 16/05/2024
 */
val appComponent = listOf(
    coreModule,
    retrofitModule,
    databaseModule,
    gamesModule,
    detailGamesModule,
    favoriteGamesModule
)