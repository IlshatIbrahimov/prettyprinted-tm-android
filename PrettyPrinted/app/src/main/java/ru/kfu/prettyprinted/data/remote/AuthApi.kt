package ru.kfu.prettyprinted.data.remote

import retrofit2.http.*
import ru.kfu.prettyprinted.data.remote.req.LoginReq
import ru.kfu.prettyprinted.data.remote.req.RegisterReq
import ru.kfu.prettyprinted.data.remote.res.AuthResponse

interface AuthApi {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "Access-Control-Allow-Origin: http://192.168.0.169:8080/",
        "Access-Control-Allow-Methods: GET,PUT,POST,DELETE",
        "charset:utf-8"
    )
    @POST("auth")
    suspend fun login(
        @Body loginReq: LoginReq
    ): AuthResponse


    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "Access-Control-Allow-Origin: http://192.168.0.169:8080/",
        "Access-Control-Allow-Methods: GET,PUT,POST,DELETE",
        "charset:utf-8"
    )
    @POST("register")
    suspend fun register(
        @Body registerReq: RegisterReq
    ): AuthResponse


}
