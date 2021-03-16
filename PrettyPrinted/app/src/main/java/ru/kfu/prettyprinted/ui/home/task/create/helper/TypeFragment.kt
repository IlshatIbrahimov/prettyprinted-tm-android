package ru.kfu.prettyprinted.ui.home.task.create.helper

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_type.*
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.ui.home.HomeActivity


class TypeFragment : Fragment(R.layout.fragment_type) {
    override fun onStart() {
        val bundle = Bundle()
        super.onStart()
        tp_btn_bug.setOnClickListener {
            bundle.putInt("id_type", 0)
            bundle.putString("name_type", "Bug")
            replace(bundle)
        }
        tp_btn_feature.setOnClickListener {
            bundle.putInt("id_type", 1)
            bundle.putString("name_type", "Feature")
            replace(bundle)
        }
        tp_btn_question.setOnClickListener {
            bundle.putInt("id_type", 2)
            bundle.putString("name_type", "Question")
            replace(bundle)
        }


    }

    private fun replace(bundle: Bundle) {
        (activity as HomeActivity).navController
            .navigate(R.id.action_page_type_to_nav_create_task, bundle)
    }
}