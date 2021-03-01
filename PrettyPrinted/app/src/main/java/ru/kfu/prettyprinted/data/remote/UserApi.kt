package ru.kfu.prettyprinted.data.remote

import retrofit2.http.GET
import ru.kfu.prettyprinted.data.remote.res.UserResponse

interface UserApi {
    @GET("user")
    suspend fun getUser(): UserResponse
}