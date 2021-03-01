package ru.kfu.prettyprinted.ui.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_register.*
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.extensions.isValidEmail
import ru.kfu.prettyprinted.extensions.snackbar
import ru.kfu.prettyprinted.ui.auth.AuthActivity


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
                requireView().snackbar("Заполните данные, прежде чем продолжить")
                rp_et_name.requestFocus()
            }
            rp_et_name.text.toString().isEmpty() -> {
                requireView().snackbar("Поле Имя не должно быть пустым")
                rp_et_name.requestFocus()
            }
            rp_et_surname.text.toString().isEmpty() -> {
                requireView().snackbar("Поле Фамилия не должно быть пустым")
                rp_et_surname.requestFocus()
            }
            rp_et_email.text.toString().isEmpty() -> {
                requireView().snackbar("Поле Email не должно быть пустым")
                rp_et_email.requestFocus()
            }
//            rp_et_email.text.toString().isValidEmail() -> {
//                requireView().snackbar("Проверьте введенный вами email")
//                rp_et_email.requestFocus()
//            }
            else -> openNextPage()
        }
    }

    private fun openNextPage() {
        val bundle =  Bundle()
        bundle.putString("name", rp_et_name.text.toString().trim())
        bundle.putString("surname", rp_et_surname.text.toString().trim())
        bundle.putString("email", rp_et_email.text.toString().trim())
        (activity as AuthActivity).navController
            .navigate(R.id.action_registerFragment_to_registerSecondPageFragment, bundle)
    }
    private fun openSignIn() {
        (activity as AuthActivity).navController.navigate(R.id.action_registerFragment_to_loginFragment)
    }
}


