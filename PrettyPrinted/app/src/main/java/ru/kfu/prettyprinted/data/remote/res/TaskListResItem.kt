package ru.kfu.prettyprinted.data.remote.res

data class TaskListResItem(
    val assignee: Assignee,
    val author: Author,
    val comments: List<String>,
    val content: String,
    val id: Int,
    val name: String,
    val priority: Priority,
    val project: Project,
    val status: Status,
    val type: Type
)