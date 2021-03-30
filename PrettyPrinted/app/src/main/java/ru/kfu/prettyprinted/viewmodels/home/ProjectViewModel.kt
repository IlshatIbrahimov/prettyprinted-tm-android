package ru.kfu.prettyprinted.viewmodels.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kfu.prettyprinted.data.remote.Resource
import ru.kfu.prettyprinted.data.remote.res.ProjectResponse
import ru.kfu.prettyprinted.data.repository.ProjectRepository
import ru.kfu.prettyprinted.viewmodels.base.BaseViewModel

class ProjectViewModel(private val repository: ProjectRepository) : BaseViewModel(repository) {


    private val _project: MutableLiveData<Resource<ProjectResponse>> = MutableLiveData()

    val project: LiveData<Resource<ProjectResponse>>
        get() = _project

    fun createProject(
            name: String
    ) = viewModelScope.launch {
        _project.value = repository.createProject(name)
    }

    fun getProjectList() = viewModelScope.launch {
        _project.value = repository.getProjectList()
    }


}
