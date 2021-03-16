package ru.kfu.prettyprinted.viewmodels.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kfu.prettyprinted.data.remote.Resource
import ru.kfu.prettyprinted.data.remote.res.UserResponse
import ru.kfu.prettyprinted.data.repository.UserRepository
import ru.kfu.prettyprinted.viewmodels.base.BaseViewModel

class UsersViewModel(
    private val repository: UserRepository
): BaseViewModel(repository) {
    private val _user: MutableLiveData<Resource<UserResponse>> = MutableLiveData()

    val  user: LiveData<Resource<UserResponse>>
        get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = repository.getUser()
    }
}