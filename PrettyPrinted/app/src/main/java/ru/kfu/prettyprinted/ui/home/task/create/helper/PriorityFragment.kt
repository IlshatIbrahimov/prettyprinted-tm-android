package ru.kfu.prettyprinted.ui.home.task.create.helper

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_priority.*
import kotlinx.android.synthetic.main.fragment_status.*
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.ui.auth.AuthActivity
import ru.kfu.prettyprinted.ui.home.HomeActivity


class PriorityFragment : Fragment(R.layout.fragment_priority) {

    override fun onStart() {
        val bundle = Bundle()
        super.onStart()
        pp_btn_minor.setOnClickListener {
            bundle.putInt("id_priority", 0)
            bundle.putString("name_priority", "Minor")
            replace(bundle)

        }
        pp_btn_normal.setOnClickListener {
            bundle.putInt("id_priority", 1)
            bundle.putString("name_priority", "Normal")
            replace(bundle)
        }
        pp_btn_major.setOnClickListener {
            bundle.putInt("id_priority", 2)
            bundle.putString("name_priority", "Major")
            replace(bundle)
        }
        pp_btn_critical.setOnClickListener {
            bundle.putInt("id_priority", 3)
            bundle.putString("name_priority", "Critical")
            replace(bundle)
        }

    }

    private fun replace(bundle: Bundle) {
        (activity as HomeActivity).navController
            .navigate(R.id.action_page_priority_to_nav_create_task, bundle)
    }

}