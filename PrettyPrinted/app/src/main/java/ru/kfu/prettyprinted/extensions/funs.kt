package ru.kfu.prettyprinted.extensions

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.kfu.prettyprinted.APP_ACTIVITY
import ru.kfu.prettyprinted.R


fun String.isValidEmail(): Boolean =
    Regex("""""").matches(this)