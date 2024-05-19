package com.example.gameapp.feature.favorite_games.view_model

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Andika Bratadirja
 * @date 19/05/2024
 */
val favoriteGamesModule = module {
    viewModel {
        FavoriteGamesViewModel(get())
    }
}