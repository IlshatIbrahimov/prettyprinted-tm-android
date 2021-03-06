package ru.kfu.prettyprinted.ui.home.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_project_item.view.*
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.data.remote.res.ProjectTasksResponse
import ru.kfu.prettyprinted.data.remote.res.ProjectTasksResponseItem
import ru.kfu.prettyprinted.ui.home.HomeActivity

class TaskAdapter() : RecyclerView.Adapter<TaskAdapter.TaskListHolder>() {
    private var listItems = ProjectTasksResponse()

    class TaskListHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.tpi_item_name
        val priority = view.tpi_priority
        val type = view.tpi_type
        val status = view.tpi_status
        val user_name = view.tpi_user_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):TaskListHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.task_project_item, parent, false)

        val holder = TaskListHolder(view)
        holder.itemView.setOnClickListener {
            HomeActivity().navController.navigate(R.id.action_nav_create_task_to_page_users)
        }
        return TaskListHolder(view)
    }

    fun updateListProject(item: ProjectTasksResponse){
        listItems.add(item[1])
        notifyItemInserted(listItems.size)
    }

    override fun onBindViewHolder(holder: TaskListHolder, position: Int) {
        holder.name.text = listItems[position].name
        holder.priority.text = listItems[position].priority.name
        holder.type.text = listItems[position].type.name
        holder.status.text = listItems[position].status.name
        holder.user_name.text = "${listItems[position].assignee.name} ${listItems[position].assignee.surname} "
    }

    override fun getItemCount(): Int = listItems.size

}