package ru.kfu.prettyprinted.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_register_second_page.*
import kotlinx.coroutines.launch
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.data.remote.Resource
import ru.kfu.prettyprinted.data.remote.api.AuthApi
import ru.kfu.prettyprinted.data.repository.AuthRepository
import ru.kfu.prettyprinted.databinding.FragmentRegisterSecondPageBinding
import ru.kfu.prettyprinted.extensions.handleApiError
import ru.kfu.prettyprinted.extensions.snackbar
import ru.kfu.prettyprinted.extensions.startNewActivity
import ru.kfu.prettyprinted.ui.auth.AuthActivity
import ru.kfu.prettyprinted.ui.base.BaseFragment
import ru.kfu.prettyprinted.ui.home.HomeActivity
import ru.kfu.prettyprinted.viewmodels.auth.AuthViewModel


class RegisterSecondPageFragment :
    BaseFragment<AuthViewModel, FragmentRegisterSecondPageBinding, AuthRepository>() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.authResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.value.jwt)
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiError(it) { registerUser() }
            }
        })

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
                requireView().snackbar("Поле Password должно быть заполнено")
                rp_et_password.requestFocus()
            }

            rp_et_confirm_password.text.toString() != rp_et_password.text.toString() -> {
                requireView().snackbar("Пароли должны совпадать")
                rp_et_confirm_password.requestFocus()
            }
            else -> registerUser()

        }
    }

    private fun registerUser() {
        val name = arguments?.getString("name")
        val surname = arguments?.getString("surname")
        val email = arguments?.getString("email")
        val password = rp_et_password.text.toString().trim()
        viewModel.register(name!!, surname!!, email!!, password)
    }

    private fun openSignIn() {
        (activity as AuthActivity).navController.navigate(
            R.id.action_registerSecondPageFragment_to_loginFragment
        )
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegisterSecondPageBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)

}