package ru.kfu.prettyprinted.data.remote.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.kfu.prettyprinted.data.remote.req.TaskReq
import ru.kfu.prettyprinted.data.remote.res.ProjectTasksResponse
import ru.kfu.prettyprinted.data.remote.res.TaskListResponse

interface TaskApi {
    @POST("task")
    suspend fun createTask(
            @Body taskReq: TaskReq
    ): TaskListResponse

    @GET("task/user")
    suspend fun getTaskList(): TaskListResponse


    @GET("project/{id}/tasks")
    suspend fun getProjectTasks(@Path("id") id: Int):ProjectTasksResponse



}