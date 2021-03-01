package ru.kfu.prettyprinted.extensions

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.kfu.prettyprinted.APP_ACTIVITY
import ru.kfu.prettyprinted.R

fun replaceFragment(fragment: Fragment, addStack: Boolean = true, appActivity: AppCompatActivity) {
    if (addStack) {
        appActivity.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.data_container,
                fragment
            ).commit()
    } else {
        appActivity.supportFragmentManager.beginTransaction()
            .replace(
                R.id.data_container,
                fragment
            ).commit()
    }

}

fun showToast(message: String) {
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_SHORT).show()
}

fun String.isValidEmail(): Boolean = Regex("""\w+@[a-z]+\.[a-z]{2,4}""").matches(this)