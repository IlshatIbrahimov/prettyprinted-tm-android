package ru.kfu.prettyprinted.viewmodels.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kfu.prettyprinted.data.remote.api.UserApi
import ru.kfu.prettyprinted.data.repository.BaseRepository

abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {
    suspend fun logout(api: UserApi) = withContext(Dispatchers.IO) { repository.logout(api) }
}