package com.example.gameapp.core.api

import com.example.gameapp.feature.detail_movie.model.DetailGamesModel
import com.example.gameapp.feature.main.model.BaseResponse
import com.example.gameapp.feature.main.model.GamesModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Andika Bratadirja
 * @date 16/05/2024
 */
interface ServiceApi {
    @GET("games")
    suspend fun getListGame(
        @Query("key") apiKey: String?
    ): BaseResponse<GamesModel>

    @GET("games/{id}")
    suspend fun geDetailGame(
        @Path("id") id: Int?,
        @Query("key") apiKey: String?,
    ): DetailGamesModel
}