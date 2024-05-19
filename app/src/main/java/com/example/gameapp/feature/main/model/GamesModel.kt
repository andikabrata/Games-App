package com.example.gameapp.feature.main.model

/**
 * @author Andika Bratadirja
 * @date 18/05/2024
 */
data class GamesModel(
    val id: Int,
    val name: String,
    val released: String,
    val background_image: String,
    val genres: List<Genres>
)

data class Genres(
    val id: Int,
    val name: String
)
