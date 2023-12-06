package com.egample.kmmdemoapp.android

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egample.kmmdemoapp.ApiClient
import com.egample.kmmdemoapp.AppSDK
import com.egample.kmmdemoapp.android.data.UserDetail
import com.egample.kmmdemoapp.android.data.toUserDetail
import com.egample.kmmdemoapp.cache.DatabaseDriverFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val sdk = AppSDK(DatabaseDriverFactory(application))

    private val _uiStateHomeScreen = MutableStateFlow(HomeUiState())
    val uiStateHomeScreen: StateFlow<HomeUiState> = _uiStateHomeScreen.asStateFlow()

    private val _uiStateUserDetail = MutableStateFlow(DetailUiState())
    val uiStateUserDetail: StateFlow<DetailUiState> = _uiStateUserDetail.asStateFlow()


    init {
        viewModelScope.launch {
            kotlin.runCatching {
                sdk.getHistory()
            }.onSuccess {
                _uiStateHomeScreen.value = HomeUiState(
                    history = it.executeAsList()
                )
            }.onFailure {

            }

        }
    }

    fun getDetail(name: String) {
        viewModelScope.launch {
            _uiStateUserDetail.value = DetailUiState(
                detail = null,
                loading = true,
                error = null
            )
            delay(2_000)
            kotlin.runCatching {
                sdk.getUserInfo(name)
            }.onSuccess {
                _uiStateUserDetail.value = DetailUiState(
                    detail = it.data?.toUserDetail(),
                    loading = false,
                    error = null
                )
                sdk.saveInHistory(name)
            }.onFailure {
                _uiStateUserDetail.value = DetailUiState(
                    detail = null,
                    loading = false,
                    error = "Something went wrong"
                )
            }
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            sdk.clearHistory()
            _uiStateHomeScreen.value = HomeUiState(
                history = emptyList()
            )
        }
    }

}


data class HomeUiState(
    var history: List<String> = emptyList()
)

data class DetailUiState(
    var detail: UserDetail? = null,
    var loading: Boolean? = null,
    var error: String? = null
)
