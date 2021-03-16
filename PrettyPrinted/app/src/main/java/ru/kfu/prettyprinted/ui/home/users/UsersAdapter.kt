package ru.kfu.prettyprinted.ui.home.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_item.view.*
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.data.models.User
import ru.kfu.prettyprinted.extensions.MyOnClickListener

class UsersAdapter(val clickListener: MyOnClickListener) : RecyclerView.Adapter<UsersAdapter.UserHolder>() {
    private var listItems = mutableListOf<User>()

    class UserHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fullName = view.up_item_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)

        val holder = UserHolder(view)
        holder.itemView.setOnClickListener {
            clickListener.onClicked(view)
        }
        return UserHolder(view)
    }

    fun updateListUsers(item: User){
        listItems.add(item)
        notifyItemInserted(listItems.size)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.fullName.text = "${listItems[position].name} ${listItems[position].surname}"
    }

    override fun getItemCount(): Int = listItems.size

}