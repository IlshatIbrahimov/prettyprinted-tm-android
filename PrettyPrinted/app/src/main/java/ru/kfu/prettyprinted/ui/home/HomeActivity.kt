package ru.kfu.prettyprinted.ui.home

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_home.*
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.ui.base.BaseActivity
import ru.kfu.prettyprinted.viewmodels.base.BaseViewModel

class HomeActivity : BaseActivity() {

    override val layout: Int = R.layout.activity_home


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appBarConfigurator = AppBarConfiguration(
            setOf(
                R.id.nav_project,
                R.id.nav_dash_board,
                R.id.nav_create_task,
                R.id.nav_chat,
                R.id.nav_profile
            )
        )

        setupActionBarWithNavController(navController, appBarConfigurator)

        nav_view.setupWithNavController(navController)


    }
}