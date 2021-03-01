package ru.kfu.prettyprinted.ui.auth.register

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_register_second_page.*
import ru.kfu.prettyprinted.AuthActivity
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.data.repository.AuthRepository
import ru.kfu.prettyprinted.databinding.FragmentLoginBinding
import ru.kfu.prettyprinted.databinding.FragmentRegisterSecondPageBinding
import ru.kfu.prettyprinted.extensions.replaceFragment
import ru.kfu.prettyprinted.extensions.showToast
import ru.kfu.prettyprinted.ui.auth.login.LoginFragment
import ru.kfu.prettyprinted.ui.base.BaseFragment
import ru.kfu.prettyprinted.viewmodels.auth.AuthViewModel


class RegisterSecondPageFragment(val name: String, val surname: String, val email: String) :
    BaseFragment<AuthViewModel, FragmentRegisterSecondPageBinding, AuthRepository>() {

    override fun onStart() {
        super.onStart()

        rp_btn_login.setOnClickListener {
            openSignIn()
        }
        rp_btn_register.setOnClickListener {
            checkInfo()

        }
    }

    private fun checkInfo() {
        when {
            rp_et_password.text.toString().isEmpty() -> {
                showToast("Поле Password должно быть заполнено")
                rp_et_password.requestFocus()
            }

            rp_et_confirm_password.text.toString() != rp_et_password.text.toString() -> {
                showToast("Пароли должны совпадать")
                rp_et_confirm_password.requestFocus()
            }
            else -> registerUser()

        }
    }

    private fun registerUser() {

    }

    private fun openSignIn() {
        replaceFragment(LoginFragment(), true, AuthActivity())
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegisterSecondPageBinding {
        TODO("Not yet implemented")
    }

    override fun getFragmentRepository(): AuthRepository {
        TODO("Not yet implemented")
    }

}