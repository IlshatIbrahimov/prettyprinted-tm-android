package ru.kfu.prettyprinted.data.repository

import ru.kfu.prettyprinted.data.remote.api.UserApi

class UserRepository(
    private val api: UserApi
) : BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }
}