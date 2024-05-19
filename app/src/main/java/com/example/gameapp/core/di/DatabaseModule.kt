package com.example.gameapp.core.di

import com.example.gameapp.core.db.DaoProvider
import com.example.gameapp.core.db.DatabaseProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @author Andika Bratadirja
 * @date 19/05/2024
 */
val databaseModule = module {
    single { DatabaseProvider(androidContext()) }
    single { DaoProvider(get()) }
}