package ru.kfu.prettyprinted.viewmodels.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.kfu.prettyprinted.data.delegates.UserPreferences
import ru.kfu.prettyprinted.data.remote.Resource
import ru.kfu.prettyprinted.data.remote.res.AuthResponse
import ru.kfu.prettyprinted.data.remote.res.UserResponse
import ru.kfu.prettyprinted.data.repository.UserRepository
import ru.kfu.prettyprinted.viewmodels.base.BaseViewModel

class HomeViewModel(
    private val repository: UserRepository
): BaseViewModel(repository) {
    private lateinit var userPreferences: UserPreferences
    private val _user: MutableLiveData<Resource<UserResponse>> = MutableLiveData()

    val  user: LiveData<Resource<UserResponse>>
        get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = repository.getUser()
    }
}