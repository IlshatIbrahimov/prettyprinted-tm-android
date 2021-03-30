package ru.kfu.prettyprinted.ui.home.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_task_list.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import ru.kfu.prettyprinted.data.remote.Resource
import ru.kfu.prettyprinted.data.remote.api.TaskApi
import ru.kfu.prettyprinted.data.remote.res.ProjectTasksResponse
import ru.kfu.prettyprinted.data.remote.res.ProjectTasksResponseItem
import ru.kfu.prettyprinted.data.repository.TaskRepository
import ru.kfu.prettyprinted.databinding.FragmentTaskListBinding
import ru.kfu.prettyprinted.extensions.snackbar
import ru.kfu.prettyprinted.ui.base.BaseFragment
import ru.kfu.prettyprinted.viewmodels.home.TaskViewModel


class TaskListFragment :
    BaseFragment<TaskViewModel, FragmentTaskListBinding, TaskRepository>() {


    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: TaskAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
    }

    private fun initRecycleView() {
        mRecyclerView = rv_task
        mAdapter = TaskAdapter()

        viewModel.getProjectTasks(1)
        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> updateUI(it.value)
                is Resource.Failure -> requireView().snackbar("Упс, что-то пошло не так")
            }
        })

        mRecyclerView.adapter = mAdapter
    }

    private fun updateUI(item: ProjectTasksResponse) {
        mAdapter.updateListProject(item)
    }

    override fun getViewModel() = TaskViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentTaskListBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): TaskRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataUserSource.buildTokenApi(TaskApi::class.java, token)
        return TaskRepository(api)
    }

}