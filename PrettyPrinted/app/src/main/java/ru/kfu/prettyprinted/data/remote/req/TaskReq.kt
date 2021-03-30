package ru.kfu.prettyprinted.data.remote.req

data class TaskReq(
    val assigneeId: String,
    val content: String,
    val priority: String,
    val projectId: String,
    val status: String,
    val taskName: String,
    val type: String
)