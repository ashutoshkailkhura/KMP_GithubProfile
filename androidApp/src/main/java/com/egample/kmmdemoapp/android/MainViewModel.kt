package com.egample.kmmdemoapp.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egample.kmmdemoapp.ApiClient
import com.egample.kmmdemoapp.android.data.UserDetail
import com.egample.kmmdemoapp.android.data.toUserDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val sdk = ApiClient()

    private val _uiStateUserDetail = MutableStateFlow(DetailUiState())
    val uiStateUserDetail: StateFlow<DetailUiState> = _uiStateUserDetail.asStateFlow()

    fun getDetail(name: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                sdk.getDetail(name)
            }.onSuccess {
                _uiStateUserDetail.value = DetailUiState(
                    detail = it.toUserDetail(),
                )
            }.onFailure {
                _uiStateUserDetail.value = DetailUiState(
                    detail = null,
                )
            }
        }
    }
}

data class DetailUiState(
    var detail: UserDetail? = null,
)