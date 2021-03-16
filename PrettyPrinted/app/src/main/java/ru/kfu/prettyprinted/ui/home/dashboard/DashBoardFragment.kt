package ru.kfu.prettyprinted.ui.home.dashboard


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_dash_board.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import ru.kfu.prettyprinted.data.remote.Resource
import ru.kfu.prettyprinted.data.remote.api.TaskApi
import ru.kfu.prettyprinted.data.remote.res.TaskListResItem
import ru.kfu.prettyprinted.data.repository.TaskRepository
import ru.kfu.prettyprinted.databinding.FragmentDashBoardBinding
import ru.kfu.prettyprinted.extensions.snackbar
import ru.kfu.prettyprinted.ui.base.BaseFragment
import ru.kfu.prettyprinted.viewmodels.home.TaskViewModel


class DashBoardFragment : BaseFragment<TaskViewModel, FragmentDashBoardBinding, TaskRepository>() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: DashBoardAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
    }

    private fun initRecycleView() {
        mRecyclerView = rv_task_user
        mAdapter = DashBoardAdapter()
        viewModel.getTaskList()
        viewModel.task.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {it.value.forEach {
                    item->
                     updateUI(item)
                }}
                is Resource.Failure -> requireView().snackbar("Упс, что-то пошло не так")
            }
        })

        mRecyclerView.adapter = mAdapter
    }

    private fun updateUI(item: TaskListResItem) {
        mAdapter.updateListTaskUser(item)
    }


    override fun getViewModel() = TaskViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?)
    = FragmentDashBoardBinding.inflate(inflater, container,false)

    override fun getFragmentRepository(): TaskRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataUserSource.buildTokenApi(TaskApi::class.java, token)
        return TaskRepository(api)
    }
}