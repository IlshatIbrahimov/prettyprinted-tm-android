package ru.kfu.prettyprinted.ui.home.task.create.helper

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_status.*
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.ui.home.HomeActivity

class StatusFragment : Fragment(R.layout.fragment_status) {

    override fun onStart() {
        val bundle = Bundle()
        super.onStart()
        sp_btn_open.setOnClickListener {
            bundle.putInt("id_status", 0)
            bundle.putString("name_status", "Open")
            replace(bundle)
        }
        sp_btn_progress.setOnClickListener {
            bundle.putInt("id_status", 1)
            bundle.putString("name_status", "In progress")
            replace(bundle)
        }
        sp_btn_fix.setOnClickListener {
            bundle.putInt("id_status", 2)
            bundle.putString("name_status", "Fixed")
            replace(bundle)
        }
        sp_btn_wont_fix.setOnClickListener {
            bundle.putInt("id_status", 3)
            bundle.putString("name_status", "Won\'t fix")
            replace(bundle)
        }

    }

    private fun replace(bundle: Bundle) {
        (activity as HomeActivity).navController
            .navigate(R.id.action_page_status_to_nav_create_task, bundle)
    }


}