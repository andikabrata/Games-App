package com.example.gameapp.feature.main.view_model

import androidx.lifecycle.MutableLiveData
import com.example.gameapp.BuildConfig
import com.example.gameapp.common.extension.ViewState
import com.example.gameapp.common.extension.setError
import com.example.gameapp.common.extension.setLoading
import com.example.gameapp.common.extension.setSuccess
import com.example.gameapp.common.utils.AppDispatchers
import com.example.gameapp.core.api.ServiceApi
import com.example.gameapp.core.base.BaseViewModel
import com.example.gameapp.feature.main.model.BaseResponse
import com.example.gameapp.feature.main.model.GamesModel

/**
 * @author Andika Bratadirja
 * @date 18/05/2024
 */
class GamesViewModel(
    private val serviceUtil: ServiceApi,
    private val dispatchers: AppDispatchers,
) : BaseViewModel() {

    val listGamesLiveData = MutableLiveData<ViewState<BaseResponse<GamesModel>>>()

    fun getListGames() {
        listGamesLiveData.setLoading()
        launchOnUi(
            dispatcher = dispatchers,
            onRequest = {
                val result = serviceUtil.getListGame(BuildConfig.API_KEY)
                listGamesLiveData.setSuccess(result)
            },
            onError = {
                listGamesLiveData.setError(it)
            }
        )
    }

}