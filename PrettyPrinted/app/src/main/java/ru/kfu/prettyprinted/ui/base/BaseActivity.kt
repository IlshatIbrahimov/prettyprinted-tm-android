package ru.kfu.prettyprinted.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_home.*
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.viewmodels.base.BaseViewModel

abstract class BaseActivity : AppCompatActivity() {
    protected abstract val layout: Int
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        setSupportActionBar(toolbar)

        navController = findNavController(R.id.nav_host_home)
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}