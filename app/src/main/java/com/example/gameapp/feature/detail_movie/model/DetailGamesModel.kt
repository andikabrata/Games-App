package com.example.gameapp.feature.detail_movie.model

import com.example.gameapp.feature.main.model.Genres

/**
 * @author Andika Bratadirja
 * @date 19/05/2024
 */
data class DetailGamesModel(
    val id: Int,
    val name: String,
    val released: String,
    val background_image: String,
    val genres: List<Genres>,
    val developers: List<Developers>,
    val description: String
)

data class Developers(
    val id: Int,
    val name: String,
)