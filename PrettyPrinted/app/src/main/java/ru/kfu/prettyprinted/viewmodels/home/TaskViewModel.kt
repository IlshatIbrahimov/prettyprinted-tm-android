package ru.kfu.prettyprinted.viewmodels.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kfu.prettyprinted.data.remote.Resource
import ru.kfu.prettyprinted.data.remote.res.ProjectResponse
import ru.kfu.prettyprinted.data.remote.res.ProjectTasksResponse
import ru.kfu.prettyprinted.data.remote.res.TaskListResponse
import ru.kfu.prettyprinted.data.repository.ProjectRepository
import ru.kfu.prettyprinted.data.repository.TaskRepository
import ru.kfu.prettyprinted.viewmodels.base.BaseViewModel

class TaskViewModel (private val repository: TaskRepository) : BaseViewModel(repository) {

    private val _task: MutableLiveData<Resource<TaskListResponse>> = MutableLiveData()
    private val _tasks: MutableLiveData<Resource<ProjectTasksResponse>> = MutableLiveData()

    val task: LiveData<Resource<TaskListResponse>>
        get() = _task

    val tasks: LiveData<Resource<ProjectTasksResponse>>
        get() = _tasks

    fun createTask(
            assigneeId: String,
            content: String,
            priority: String,
            projectId: String,
            status: String,
            taskName: String,
            type: String
    ) = viewModelScope.launch {
        _task.value = repository.createTask(assigneeId, content, priority, projectId, status, taskName, type)
    }

    fun getProjectTasks(id: Int) = viewModelScope.launch {
        _tasks.value = repository.getProjectTasks(id)
    }

    fun getTaskList() = viewModelScope.launch {
        _task.value = repository.getTaskList()
    }


}