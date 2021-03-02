package ru.kfu.prettyprinted.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.activity_home.*
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.ui.home.projects.ProjectListFragment
import ru.kfu.prettyprinted.ui.home.task.TaskListFragment
import ru.kfu.prettyprinted.ui.home.task.create.CreateTaskFragment

class HomeActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigationView.background = null

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_project ->
                    setCurrentFragment(ProjectListFragment())
                R.id.nav_dashboard ->
                    setCurrentFragment(TaskListFragment())
                R.id.nav_profile ->
                    setCurrentFragment(HomeFragment())
            }
            true
        }

        fab.setOnClickListener {
            setCurrentFragment(CreateTaskFragment())
        }

        bottomNavigationView.getOrCreateBadge(R.id.nav_message).apply {
            number = 10
            isVisible = true
        }


    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_home, fragment)
            commit()
        }

}