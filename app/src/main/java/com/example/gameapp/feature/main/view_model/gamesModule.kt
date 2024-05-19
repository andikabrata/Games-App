package com.example.gameapp.feature.main.view_model

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Andika Bratadirja
 * @date 18/05/2024
 */
val gamesModule = module {
    viewModel {
        GamesViewModel(get(), get())
    }
}