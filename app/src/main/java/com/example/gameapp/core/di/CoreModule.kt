package com.example.gameapp.core.di

import com.example.gameapp.common.utils.AppDispatchers
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

/**
 * @author Andika Bratadirja
 * @date 16/05/2024
 */
val coreModule = module {
    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
}