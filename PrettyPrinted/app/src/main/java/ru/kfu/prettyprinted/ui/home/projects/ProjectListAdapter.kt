package ru.kfu.prettyprinted.ui.home.projects

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.project_item.view.*
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.data.remote.res.ProjectResponseItem
import ru.kfu.prettyprinted.data.remote.res.ProjectTaskResponse
import ru.kfu.prettyprinted.extensions.MyOnClickListener
import ru.kfu.prettyprinted.ui.home.HomeActivity

class ProjectListAdapter(val clickListener: MyOnClickListener): RecyclerView.Adapter<ProjectListAdapter.ProjectListHolder>() {
    private var listItems = mutableListOf<ProjectResponseItem>()

    val activity = HomeActivity()
    class ProjectListHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.pl_item_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectListHolder {
        val view =
                LayoutInflater.from(parent.context).inflate(R.layout.project_item, parent, false)

        val holder = ProjectListHolder(view)
        holder.itemView.setOnClickListener {
            clickListener.onClicked(view)
        }
        return ProjectListHolder(view)
    }

    fun updateListProject(item: ProjectResponseItem){
        listItems.add(item)
        notifyItemInserted(listItems.size)
    }

    override fun onBindViewHolder(holder: ProjectListHolder, position: Int) {
        holder.name.text = listItems[position].name
    }

    override fun getItemCount(): Int = listItems.size

}