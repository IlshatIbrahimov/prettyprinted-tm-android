package ru.kfu.prettyprinted.extensions

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ru.kfu.prettyprinted.data.remote.Resource
import ru.kfu.prettyprinted.ui.auth.login.LoginFragment
import ru.kfu.prettyprinted.ui.base.BaseFragment

fun <A : Activity> Activity.startNewActivity(
    activity: Class<A>
) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean) {
    isEnabled = enabled

    alpha = if (enabled) 1f else 0.5f
}

fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry"){
            it()
        }
    }
    snackbar.show()
}

fun Fragment.handleApiError(
    failure: Resource.Failure,
    retry: (() -> Unit)? = null
) {
    when{
        failure.isNetworkError ->{
            requireView().snackbar("Проверьте свое интернет соединение", retry)
        }
        failure.errorCode == 401 ->{
            if(this is LoginFragment){
                requireView().snackbar("Вы ввели неверный email или пароль")
            }else{
                (this as BaseFragment<*,*,*>).logout()
            }
        }
        else->{
            val error = failure.errorBody?.string().toString()
            requireView().snackbar(error)

        }

    }

}
