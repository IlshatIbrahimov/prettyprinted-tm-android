package ru.kfu.prettyprinted.data.remote.res

data class ProjectTaskResponse(
    val comments: List<String>,
    val id: Int,
    val name: String,
    val tasks: List<Task>
)