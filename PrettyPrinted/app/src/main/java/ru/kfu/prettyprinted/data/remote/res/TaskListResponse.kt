package ru.kfu.prettyprinted.data.remote.res

data class TaskListResponse(
    val assignee: Assignee,
    val author: Author,
    val content: String,
    val id: Int,
    val name: String,
    val priority: Int,
    val projectId: Int,
    val status: Int,
    val type: Int
)