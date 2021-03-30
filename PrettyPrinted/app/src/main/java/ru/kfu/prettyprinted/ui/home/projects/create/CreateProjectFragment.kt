package ru.kfu.prettyprinted.ui.home.projects.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_create_project.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.data.remote.api.ProjectApi
import ru.kfu.prettyprinted.data.repository.ProjectRepository
import ru.kfu.prettyprinted.databinding.FragmentCreateProjectBinding
import ru.kfu.prettyprinted.databinding.FragmentProjectListBinding
import ru.kfu.prettyprinted.extensions.snackbar
import ru.kfu.prettyprinted.ui.base.BaseFragment
import ru.kfu.prettyprinted.ui.home.HomeActivity
import ru.kfu.prettyprinted.viewmodels.home.ProjectViewModel

class CreateProjectFragment : BaseFragment<ProjectViewModel, FragmentCreateProjectBinding, ProjectRepository>() {

    lateinit var name: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cp_btn_create.setOnClickListener {
            name = cp_et_name.text.toString().trim()
            checkName()
        }
    }


    private fun checkName() {
        when {
            name.isEmpty() ->
                requireView().snackbar(" Введите имя проекта")
            else ->
                create()
        }

    }

    fun create() {
        viewModel.createProject(name)
        (activity as HomeActivity).navController.navigate(R.id.action_page_create_project_to_nav_project)

    }

    override fun getViewModel() = ProjectViewModel::class.java



    override fun getFragmentRepository(): ProjectRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataUserSource.buildTokenApi(ProjectApi::class.java, token)
        return ProjectRepository(api)
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCreateProjectBinding.inflate(inflater, container, false)


}