package ru.kfu.prettyprinted.viewmodels.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kfu.prettyprinted.data.remote.Resource
import ru.kfu.prettyprinted.data.repository.AuthRepository
import ru.kfu.prettyprinted.data.remote.res.AuthResponse

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _authResponse: MutableLiveData<Resource<AuthResponse>> = MutableLiveData()

    val authResponse: LiveData<Resource<AuthResponse>>
        get() = _authResponse

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _authResponse.value =  repository.login(email, password)
    }

    fun register(
        name:String,
        surname: String,
        email: String,
        password: String
    ) = viewModelScope.launch {
        _authResponse.value = repository.register(name, surname, email, password)
    }


    fun saveAuthToken(token:String) = viewModelScope.launch {
        repository.saveAuthToken(token)
    }
}