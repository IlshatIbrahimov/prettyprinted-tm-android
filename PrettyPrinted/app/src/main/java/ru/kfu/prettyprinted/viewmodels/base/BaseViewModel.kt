package ru.kfu.prettyprinted.viewmodels.base

import android.app.usage.UsageEvents
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kfu.prettyprinted.data.remote.api.UserApi
import ru.kfu.prettyprinted.data.repository.BaseRepository


abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {
    suspend fun logout(api: UserApi) = withContext(Dispatchers.IO) { repository.logout(api) }


}



