package ru.kfu.prettyprinted.data.remote.res

data class ProjectResponseItem(
    val id: Int,
    val name: String,
    val taskList: List<Any>
)