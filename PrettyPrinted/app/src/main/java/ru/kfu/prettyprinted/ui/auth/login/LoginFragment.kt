package ru.kfu.prettyprinted.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.launch
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.data.remote.Resource
import ru.kfu.prettyprinted.data.remote.api.AuthApi
import ru.kfu.prettyprinted.data.repository.AuthRepository
import ru.kfu.prettyprinted.databinding.FragmentLoginBinding
import ru.kfu.prettyprinted.extensions.handleApiError
import ru.kfu.prettyprinted.extensions.startNewActivity
import ru.kfu.prettyprinted.extensions.visible
import ru.kfu.prettyprinted.ui.auth.AuthActivity
import ru.kfu.prettyprinted.ui.base.BaseFragment
import ru.kfu.prettyprinted.ui.home.HomeActivity
import ru.kfu.prettyprinted.viewmodels.auth.AuthViewModel

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lpProgressBar.visible(false)
//        binding.lpBtnLogin.enable(false)


        viewModel.authResponse.observe(viewLifecycleOwner, Observer {
            binding.lpProgressBar.visible(false)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.value.jwt)
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiError(it) { login() }
            }
        })

//        binding.lpEtEmail.addTextChangedListener {
//            binding.lpBtnLogin.enable(
//                lp_et_email.text.toString().isNotEmpty() && lp_et_password.text.toString()
//                    .isNotEmpty()
//            )
//        }

        binding.lpBtnLogin.setOnClickListener {
            login()
        }

        binding.lpBtnSignIn.setOnClickListener{
            (activity as AuthActivity).navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun login() {
        val email = lp_et_email.text.toString().trim()
        val password = lp_et_password.text.toString().trim()
        binding.lpProgressBar.visible(true)
        viewModel.login(email, password)
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)


}
