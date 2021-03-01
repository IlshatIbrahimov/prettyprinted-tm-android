package ru.kfu.prettyprinted.data.remote.req

data class RegisterReq(
    val name: String,
    val surname: String,
    val email: String,
    val password: String
)