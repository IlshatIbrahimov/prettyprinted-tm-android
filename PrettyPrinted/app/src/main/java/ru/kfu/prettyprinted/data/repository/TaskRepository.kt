package ru.kfu.prettyprinted.data.repository

import ru.kfu.prettyprinted.data.remote.api.TaskApi
import ru.kfu.prettyprinted.data.remote.req.TaskReq

class TaskRepository (
    private val api: TaskApi
    ) : BaseRepository()
    {

        suspend fun getTaskList() = safeApiCall {
            api.getTaskList()
        }

        suspend fun createTask(
                assigneeId: String,
                content: String,
                priority: String,
                projectId: String,
                status: String,
                taskName: String,
                type: String
        ) = safeApiCall {
            api.createTask(TaskReq(assigneeId, content, priority, projectId, status, taskName, type))
        }
    }