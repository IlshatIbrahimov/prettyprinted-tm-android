package ru.kfu.prettyprinted.data.remote.res

data class ProjectTasksResponseItem(
    val assignee: AssigneeX,
    val author: AuthorX,
    val comments: List<Any>,
    val content: String,
    val id: Int,
    val name: String,
    val priority: PriorityX,
    val project: ProjectX,
    val status: StatusX,
    val type: TypeX
)