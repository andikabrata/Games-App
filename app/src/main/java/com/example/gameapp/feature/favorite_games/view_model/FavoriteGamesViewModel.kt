package com.example.gameapp.feature.favorite_games.view_model

import androidx.lifecycle.LiveData
import com.example.gameapp.core.base.BaseViewModel
import com.example.gameapp.core.db.DaoProvider
import com.example.gameapp.core.db.entity.GamesFavoriteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author Andika Bratadirja
 * @date 19/05/2024
 */
class FavoriteGamesViewModel(
    private val daoProvider: DaoProvider
) : BaseViewModel() {

    fun getListFavorite(): LiveData<List<GamesFavoriteEntity>> {
        return daoProvider.gamesFavoriteDao.getAll()
    }

    fun deleteGamesFavorite(idGames: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            daoProvider.gamesFavoriteDao.deleteItemByIds(ids = idGames)
        }
    }
}