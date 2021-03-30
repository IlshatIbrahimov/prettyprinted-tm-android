package ru.kfu.prettyprinted.ui.home.projects

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_project_list.*
import kotlinx.android.synthetic.main.project_item.view.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.data.remote.Resource
import ru.kfu.prettyprinted.data.remote.api.ProjectApi
import ru.kfu.prettyprinted.data.remote.res.ProjectResponseItem
import ru.kfu.prettyprinted.data.repository.ProjectRepository
import ru.kfu.prettyprinted.databinding.FragmentProjectListBinding
import ru.kfu.prettyprinted.extensions.MyOnClickListener
import ru.kfu.prettyprinted.extensions.snackbar
import ru.kfu.prettyprinted.ui.base.BaseFragment
import ru.kfu.prettyprinted.ui.home.HomeActivity
import ru.kfu.prettyprinted.viewmodels.home.ProjectViewModel


class ProjectListFragment :
        BaseFragment<ProjectViewModel, FragmentProjectListBinding, ProjectRepository>() {

    private val args: ProjectListFragmentArgs by navArgs()
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: ProjectListAdapter
//    private var mListItems = listOf<ProjectItemData>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.project_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.project_create -> {
               (activity as HomeActivity).navController.navigate(R.id.action_nav_project_to_page_create_project)
            }
        }
        return true
    }



    private fun initRecycleView() {
        mRecyclerView = rv_project
        mAdapter = ProjectListAdapter(object : MyOnClickListener{
            override fun onClicked(tag: Any) {
                (activity as HomeActivity).navController.navigate(R.id.action_nav_project_to_nav_task)
            }
        })

        viewModel.getProjectList()
        viewModel.project.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    it.value.forEach {
                        item->updateUI(item)
                    }

                }
                is Resource.Failure -> requireView().snackbar("Упс, что-то пошло не так")
            }
        })

        mRecyclerView.adapter = mAdapter
    }

    private fun updateUI(item: ProjectResponseItem) {
        mAdapter.updateListProject(item)
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
}
