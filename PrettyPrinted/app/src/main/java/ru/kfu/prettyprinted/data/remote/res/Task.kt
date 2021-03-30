package ru.kfu.prettyprinted.data.remote.res

data class Task(
    val assignee: Assignee,
    val author: Author,
    val comments: List<Comment>,
    val content: String,
    val id: Int,
    val name: String,
    val priority: Priority,
    val project: Project,
    val status: Status,
    val type: Type
)