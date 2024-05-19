package com.example.gameapp.core.db

import com.example.gameapp.core.db.dao.GamesFavoriteDao

/**
 * @author Andika Bratadirja
 * @date 19/05/2024
 */
class DaoProvider(database: DatabaseProvider) {
    val gamesFavoriteDao: GamesFavoriteDao = database.getInstance().gamesFavoriteDao()
}