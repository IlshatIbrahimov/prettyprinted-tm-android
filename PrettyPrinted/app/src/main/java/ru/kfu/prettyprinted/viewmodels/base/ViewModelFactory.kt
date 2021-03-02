package ru.kfu.prettyprinted.viewmodels.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.kfu.prettyprinted.data.repository.AuthRepository
import ru.kfu.prettyprinted.data.repository.BaseRepository
import ru.kfu.prettyprinted.data.repository.ProjectRepository
import ru.kfu.prettyprinted.data.repository.UserRepository
import ru.kfu.prettyprinted.viewmodels.auth.AuthViewModel
import ru.kfu.prettyprinted.viewmodels.home.HomeViewModel
import ru.kfu.prettyprinted.viewmodels.home.ProjectViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) ->
                AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) ->
                HomeViewModel(repository as UserRepository) as T
            modelClass.isAssignableFrom(ProjectViewModel::class.java) ->
                ProjectViewModel(repository as ProjectRepository) as T
            else ->
                throw IllegalArgumentException("ViewModelsClass is not found")
        }
    }
}


