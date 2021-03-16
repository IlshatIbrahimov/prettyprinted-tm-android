package ru.kfu.prettyprinted.data.remote.res

data class Comment(
    val author: Author,
    val date: String,
    val id: Int,
    val message: String,
    val type: String
)