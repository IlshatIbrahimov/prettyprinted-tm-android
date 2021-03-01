package ru.kfu.prettyprinted.ui.auth.register

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_register.*
import ru.kfu.prettyprinted.AuthActivity
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.data.repository.AuthRepository
import ru.kfu.prettyprinted.databinding.FragmentRegisterBinding
import ru.kfu.prettyprinted.extensions.isValidEmail
import ru.kfu.prettyprinted.extensions.replaceFragment
import ru.kfu.prettyprinted.extensions.showToast
import ru.kfu.prettyprinted.ui.auth.login.LoginFragment
import ru.kfu.prettyprinted.ui.base.BaseFragment
import ru.kfu.prettyprinted.viewmodels.auth.AuthViewModel


class RegisterFragment :Fragment(R.layout.fragment_register) {

    private lateinit var mName: String
    private lateinit var mSurname: String
    private lateinit var mEmail: String

    override fun onStart() {
        super.onStart()
        rp_btn_login.setOnClickListener {
            openSignIn()
        }
        rp_btn_next.setOnClickListener {
            checkInfo()
        }
    }

    private fun checkInfo() {
        when {
            rp_et_name.text.toString()
                .plus(rp_et_surname.text.toString().plus(rp_et_email.text.toString()))
                .isEmpty() -> {
                showToast("Заполните данные, прежде чем продолжить")
                rp_et_name.requestFocus()
            }
            rp_et_name.text.toString().isEmpty() -> {
                showToast("Поле Имя не должно быть пустым")
                rp_et_name.requestFocus()
            }
            rp_et_surname.text.toString().isEmpty() -> {
                showToast("Поле Фамилия не должно быть пустым")
                rp_et_surname.requestFocus()
            }
            rp_et_email.text.toString().isEmpty() -> {
                showToast("Поле Email не должно быть пустым")
                rp_et_email.requestFocus()
            }
            rp_et_email.text.toString().isValidEmail() -> {
                showToast("Проверьте введенный вами email")
                rp_et_email.requestFocus()
            }
            else -> openNextPage()
        }
    }

    private fun openNextPage() {
        replaceFragment(
            RegisterSecondPageFragment(
                rp_et_name.text.toString(),
                rp_et_surname.text.toString(),
                rp_et_email.text.toString()
            ),
            true,
            AuthActivity()
        )
    }
    private fun openSignIn() {
        replaceFragment(LoginFragment(), true, AuthActivity())
    }
}


