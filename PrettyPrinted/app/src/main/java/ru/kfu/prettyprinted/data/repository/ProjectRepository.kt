package ru.kfu.prettyprinted.data.repository

import ru.kfu.prettyprinted.data.remote.api.ProjectApi
import ru.kfu.prettyprinted.data.remote.req.ProjectReq

class ProjectRepository(
    private val api: ProjectApi
) : BaseRepository() {

    suspend fun getProjectList() = safeApiCall {
        api.getProjectList()
    }

    suspend fun createProject(
        name: String
    ) = safeApiCall {
        api.createProject(ProjectReq(name))
    }
}