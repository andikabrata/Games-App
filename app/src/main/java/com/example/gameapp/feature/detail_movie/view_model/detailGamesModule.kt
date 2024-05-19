package com.example.gameapp.feature.detail_movie.view_model

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Andika Bratadirja
 * @date 19/05/2024
 */
val detailGamesModule = module {
    viewModel {
        DetailGamesViewModel(get(), get(), get())
    }
}