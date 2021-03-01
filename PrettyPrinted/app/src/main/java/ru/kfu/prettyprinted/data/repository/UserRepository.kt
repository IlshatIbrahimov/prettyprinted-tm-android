package ru.kfu.prettyprinted.data.repository

import ru.kfu.prettyprinted.data.remote.UserApi

class UserRepository(
    private val api: UserApi
) : BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }
}