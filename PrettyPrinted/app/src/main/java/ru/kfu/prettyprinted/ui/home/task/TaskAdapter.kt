package ru.kfu.prettyprinted.ui.home.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.project_item.view.*
import kotlinx.android.synthetic.main.task_item.view.*
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.data.remote.res.ProjectResponseItem
import ru.kfu.prettyprinted.data.remote.res.TaskListResItem
import ru.kfu.prettyprinted.data.remote.res.TaskListResponse
import ru.kfu.prettyprinted.ui.home.HomeActivity

class TaskAdapter() : RecyclerView.Adapter<TaskAdapter.TaskListHolder>() {
    private var listItems = mutableListOf<TaskListResItem>()

    class TaskListHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.db_item_name
        val priority = view.priority
        val type = view.type
        val status = view.status
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):TaskListHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)

        val holder = TaskListHolder(view)
        holder.itemView.setOnClickListener {
            HomeActivity().navController.navigate(R.id.action_nav_create_task_to_page_users)
        }
        return TaskListHolder(view)
    }

    fun updateListProject(item: TaskListResItem){
        listItems.add(item)
        notifyItemInserted(listItems.size)
    }

    override fun onBindViewHolder(holder: TaskListHolder, position: Int) {
        holder.name.text = listItems[position].name
        holder.priority.text = listItems[position].priority.name
        holder.type.text = listItems[position].type.name
        holder.status.text = listItems[position].status.name
    }

    override fun getItemCount(): Int = listItems.size

}