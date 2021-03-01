package ru.kfu.prettyprinted.data.remote.res

import ru.kfu.prettyprinted.data.models.User

data class AuthResponse(
    val jwt: String,
    val user: User
)