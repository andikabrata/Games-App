package com.example.gameapp.feature.main.model

/**
 * @author Andika Bratadirja
 * @date 18/05/2024
 */
data class BaseResponse<T>(
    val results: List<T>?
)