package ru.kfu.prettyprinted.data.remote.api

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST
import ru.kfu.prettyprinted.data.remote.res.UserResponse

interface UserApi {
    @GET("user")
    suspend fun getUser(): UserResponse

    @POST()
    suspend fun logout(): ResponseBody
}