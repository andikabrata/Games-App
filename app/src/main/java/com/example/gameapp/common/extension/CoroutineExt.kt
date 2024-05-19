package com.example.gameapp.common.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameapp.common.utils.AppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * @author Andika Bratadirja
 * @date 16/05/2024
 */
inline fun ViewModel.runOnUi(crossinline block: suspend (CoroutineScope.() -> Unit)): Job {
    return viewModelScope.launch(Dispatchers.Main) {
        block()
    }
}

inline fun ViewModel.runOnUi(dispatcher: AppDispatchers, crossinline block: suspend (CoroutineScope.() -> Unit)): Job {
    return viewModelScope.launch(dispatcher.main) {
        block()
    }
}