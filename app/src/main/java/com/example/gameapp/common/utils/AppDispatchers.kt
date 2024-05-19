package com.example.gameapp.common.utils

import kotlinx.coroutines.CoroutineDispatcher

/**
 * @author Andika Bratadirja
 * @date 16/05/2024
 */
class AppDispatchers(
    val main: CoroutineDispatcher,
    val io: CoroutineDispatcher
)