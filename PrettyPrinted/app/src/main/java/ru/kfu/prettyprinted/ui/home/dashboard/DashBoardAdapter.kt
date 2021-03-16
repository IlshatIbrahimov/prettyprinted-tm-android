package ru.kfu.prettyprinted.ui.home.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_item.view.*
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.data.remote.res.TaskListResItem

class DashBoardAdapter : RecyclerView.Adapter<DashBoardAdapter.DashBoardHolder>() {
    private var listItems = mutableListOf<TaskListResItem>()

    class DashBoardHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.db_item_name
        val priority = view.priority
        val type = view.type
        val status = view.status
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)

        val holder = DashBoardHolder(view)
        holder.itemView.setOnClickListener {

        }
        return DashBoardHolder(view)
    }

    fun updateListTaskUser(item: TaskListResItem){
        listItems.add(item)
        notifyItemInserted(listItems.size)
    }

    override fun onBindViewHolder(holder: DashBoardHolder, position: Int) {
        holder.name.text = listItems[position].name
        holder.priority.text = listItems[position].priority.name
        holder.type.text = listItems[position].type.name
        holder.status.text = listItems[position].status.name

    }

    override fun getItemCount(): Int = listItems.size

}