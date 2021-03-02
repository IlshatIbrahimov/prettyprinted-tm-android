package ru.kfu.prettyprinted.ui.home.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.data.remote.Resource
import ru.kfu.prettyprinted.data.remote.api.ProjectApi
import ru.kfu.prettyprinted.data.remote.res.ProjectResponseItem
import ru.kfu.prettyprinted.data.repository.ProjectRepository
import ru.kfu.prettyprinted.databinding.FragmentProjectListBinding
import ru.kfu.prettyprinted.ui.base.BaseFragment
import ru.kfu.prettyprinted.ui.home.HomeActivity
import ru.kfu.prettyprinted.ui.home.projects.create.CreateProjectFragment
import ru.kfu.prettyprinted.viewmodels.home.ProjectViewModel


class ProjectListFragment :
        BaseFragment<ProjectViewModel, FragmentProjectListBinding, ProjectRepository>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProjectList()
        viewModel.project.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
//                    if (it.value[0] != null) {
//                        updateUI(it.value[0])
//                    }
                }
            }
        })

        binding.plBtnCreateProject.setOnClickListener {
            setCurrentFragment(CreateProjectFragment())
        }
    }


    private fun updateUI(project: ProjectResponseItem) {
        binding.testTv.text = project.name
    }


    override fun getViewModel() = ProjectViewModel::class.java

    override fun getFragmentBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
    ) = FragmentProjectListBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): ProjectRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataUserSource.buildTokenApi(ProjectApi::class.java, token)
        return ProjectRepository(api)
    }

    private fun setCurrentFragment(fragment: BaseFragment<*, *, *>) = HomeActivity()
            .supportFragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_home, fragment)
                commit()
            }
}