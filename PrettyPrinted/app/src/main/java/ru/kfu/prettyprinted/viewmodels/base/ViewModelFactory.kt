package ru.kfu.prettyprinted.viewmodels.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.kfu.prettyprinted.data.repository.*
import ru.kfu.prettyprinted.viewmodels.auth.AuthViewModel
import ru.kfu.prettyprinted.viewmodels.home.UsersViewModel
import ru.kfu.prettyprinted.viewmodels.home.ProjectViewModel
import ru.kfu.prettyprinted.viewmodels.home.TaskViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) ->
                AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(UsersViewModel::class.java) ->
                UsersViewModel(repository as UserRepository) as T
            modelClass.isAssignableFrom(ProjectViewModel::class.java) ->
                ProjectViewModel(repository as ProjectRepository) as T
            modelClass.isAssignableFrom(TaskViewModel::class.java) ->
                TaskViewModel(repository as TaskRepository) as T
            else ->
                throw IllegalArgumentException("ViewModelsClass is not found")
        }
    }
}


