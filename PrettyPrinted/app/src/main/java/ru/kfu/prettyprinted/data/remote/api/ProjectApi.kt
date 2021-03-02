package ru.kfu.prettyprinted.data.remote.api

import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.kfu.prettyprinted.data.remote.req.ProjectReq
import ru.kfu.prettyprinted.data.remote.res.ProjectResponse

interface ProjectApi {
    @POST("project")
    suspend fun createProject(
        @Body projectReq: ProjectReq
    ): ProjectResponse

    @GET("project")
    suspend fun getProjectList():ProjectResponse
}