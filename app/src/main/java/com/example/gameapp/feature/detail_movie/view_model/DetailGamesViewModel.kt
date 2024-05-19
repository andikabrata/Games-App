package com.example.gameapp.feature.detail_movie.view_model

import androidx.lifecycle.MutableLiveData
import com.example.gameapp.BuildConfig
import com.example.gameapp.common.extension.ViewState
import com.example.gameapp.common.extension.setError
import com.example.gameapp.common.extension.setLoading
import com.example.gameapp.common.extension.setSuccess
import com.example.gameapp.common.utils.AppDispatchers
import com.example.gameapp.core.api.ServiceApi
import com.example.gameapp.core.base.BaseViewModel
import com.example.gameapp.core.db.DaoProvider
import com.example.gameapp.core.db.entity.GamesFavoriteEntity
import com.example.gameapp.feature.detail_movie.model.DetailGamesModel
import com.example.gameapp.feature.main.model.Genres
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author Andika Bratadirja
 * @date 19/05/2024
 */
class DetailGamesViewModel(
    private val serviceUtil: ServiceApi,
    private val dispatchers: AppDispatchers,
    private val daoProvider: DaoProvider
) : BaseViewModel() {

    val detailGamesLiveData = MutableLiveData<ViewState<DetailGamesModel>>()
    val tempDetailGames = MutableLiveData<DetailGamesModel>()
    var idGames = MutableLiveData(0)

    fun insertGamesFavoriteDb(detailGamesModel: DetailGamesModel, genres: String) {
        CoroutineScope(Dispatchers.IO).launch {
//            daoProvider.gamesFavoriteDao.deleteAll()
            detailGamesModel.let {
                daoProvider.gamesFavoriteDao.insert(
                    GamesFavoriteEntity(
                        idGame = it.id,
                        gameName = it.name,
                        releaseDate = it.released,
                        genres = genres,
                        image = it.background_image
                    )
                )
            }
        }
    }

    fun getListGames() {
        detailGamesLiveData.setLoading()
        launchOnUi(
            dispatcher = dispatchers,
            onRequest = {
                val result = serviceUtil.geDetailGame(id = idGames.value, BuildConfig.API_KEY)
                detailGamesLiveData.setSuccess(result)
            },
            onError = {
                detailGamesLiveData.setError(it)
            }
        )
    }
}