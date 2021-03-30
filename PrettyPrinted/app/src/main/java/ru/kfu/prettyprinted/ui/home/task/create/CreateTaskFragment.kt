package ru.kfu.prettyprinted.ui.home.task.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_create_task.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.data.remote.api.TaskApi
import ru.kfu.prettyprinted.data.remote.res.*
import ru.kfu.prettyprinted.data.repository.TaskRepository
import ru.kfu.prettyprinted.databinding.FragmentCreateTaskBinding
import ru.kfu.prettyprinted.extensions.snackbar
import ru.kfu.prettyprinted.ui.base.BaseFragment
import ru.kfu.prettyprinted.ui.home.HomeActivity
import ru.kfu.prettyprinted.viewmodels.home.TaskViewModel


class CreateTaskFragment :
        BaseFragment<TaskViewModel, FragmentCreateTaskBinding, TaskRepository>() {

    lateinit var name: String
    lateinit var content: String
    var projectId: Int = 0
    lateinit var assigneeId: Assignee
    lateinit var priority: Priority
    lateinit var status: Status
    lateinit var type: Type

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var priorityText: String? = ""
        var statusText: String? = ""
        var typeText: String? = ""
        priorityText = arguments?.getString("name_priority")
        statusText = arguments?.getString("name_status")
        typeText = arguments?.getString("name_type")

        ct_tv_status.text = statusText
        ct_tv_priority.text = priorityText
        ct_tv_type.text = typeText


        ct_btn_project.setOnClickListener {
            replace(R.id.action_nav_create_task_to_nav_project, savedInstanceState)
        }

        ct_btn_user.setOnClickListener {
            replace(R.id.action_nav_create_task_to_page_users, savedInstanceState)
        }
        ct_btn_priority.setOnClickListener {
            replace(R.id.action_nav_create_task_to_page_priority, savedInstanceState)
        }

        ct_btn_status.setOnClickListener {
            replace(R.id.action_nav_create_task_to_page_status, savedInstanceState)
        }
        ct_btn_type.setOnClickListener {
            replace(R.id.action_nav_create_task_to_page_type, savedInstanceState)
        }

        ct_btn_create.setOnClickListener {
            name = ct_et_name.text.toString().trim()
            content = ct_et_content.text.toString().trim()
//            projectId = arguments?.getInt("id_project")!!
            priority.id = arguments?.getInt("id_priority")!!
            priority.name = arguments?.getString("name_priority")!!
            status.id = arguments?.getInt("id_status")!!
            type.id = arguments?.getInt("id_type")!!
            status.name = arguments?.getString("name_status")!!
            type.name = arguments?.getString("name_type")!!


            checkInfo()
        }
    }

    private fun replace(id: Int, bundle: Bundle?) {
        (activity as HomeActivity).navController.navigate(id, bundle)
    }


    private fun checkInfo() {
        when {
            name.isEmpty() ->
                requireView().snackbar(" Введите имя проекта")
            content.isEmpty() ->
                requireView().snackbar("Введите описание проекта")

            else ->
                create()
        }

    }

    fun create() {
//        viewModel.createTask(name)
        (activity as HomeActivity).navController.navigate(R.id.action_page_create_project_to_nav_project)

    }

    override fun getViewModel() = TaskViewModel::class.java


    override fun getFragmentRepository(): TaskRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataUserSource.buildTokenApi(TaskApi::class.java, token)
        return TaskRepository(api)
    }

    override fun getFragmentBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
    ) = FragmentCreateTaskBinding.inflate(inflater, container, false)


}