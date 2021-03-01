package ru.kfu.prettyprinted.ui.auth.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_login.*
import ru.kfu.prettyprinted.AuthActivity
import ru.kfu.prettyprinted.data.remote.AuthApi
import ru.kfu.prettyprinted.data.remote.Resource
import ru.kfu.prettyprinted.data.repository.AuthRepository
import ru.kfu.prettyprinted.databinding.FragmentLoginBinding
import ru.kfu.prettyprinted.extensions.replaceFragment
import ru.kfu.prettyprinted.ui.base.BaseFragment
import ru.kfu.prettyprinted.ui.home.HomeActivity
import ru.kfu.prettyprinted.extensions.startNewActivity
import ru.kfu.prettyprinted.extensions.visible
import ru.kfu.prettyprinted.ui.auth.register.RegisterFragment
import ru.kfu.prettyprinted.viewmodels.auth.AuthViewModel

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lpProgressBar.visible(false)
//        binding.lpBtnLogin.enable(false)


        viewModel.authResponse.observe(viewLifecycleOwner, Observer {

            lp_progress_bar.visible(false)
            when (it) {
                is Resource.Success -> {
                    viewModel.saveAuthToken(it.value.jwt)
                    Log.d("M_LoginFragment", it.value.jwt)
                    requireActivity().startNewActivity(HomeActivity::class.java)
                }
                is Resource.Failure -> {
                    println(it.toString())
                    Toast.makeText(requireContext(),"Login Failure",  Toast.LENGTH_LONG).show()
                }
            }
        })

//        binding.lpEtEmail.addTextChangedListener {
//            binding.lpBtnLogin.enable(
//                lp_et_email.text.toString().isNotEmpty() && lp_et_password.text.toString()
//                    .isNotEmpty()
//            )
//        }

        binding.lpBtnLogin.setOnClickListener {
            val email = lp_et_email.text.toString().trim()
            val password = lp_et_password.text.toString().trim()
            //@todo validation
            binding.lpProgressBar.visible(true)
            viewModel.login(email, password)
        }

        lp_btn_sign_in.setOnClickListener{
            replaceFragment(RegisterFragment(), true, AuthActivity())
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)


}
