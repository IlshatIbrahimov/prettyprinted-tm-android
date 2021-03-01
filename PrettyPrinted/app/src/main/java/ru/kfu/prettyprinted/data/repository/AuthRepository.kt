package ru.kfu.prettyprinted.data.repository

import ru.kfu.prettyprinted.data.delegates.UserPreferences
import ru.kfu.prettyprinted.data.remote.AuthApi
import ru.kfu.prettyprinted.data.remote.req.LoginReq
import ru.kfu.prettyprinted.data.remote.req.RegisterReq

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
): BaseRepository() {

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
      api.login(LoginReq(email, password))
    }

    suspend fun register(
        name:String,
        surname: String,
        email: String,
        password: String
    ) = safeApiCall {
        api.register(RegisterReq(name, surname, email, password))
    }

    suspend fun saveAuthToken(token:String){
        preferences.saveAuthToken(token)
    }
}