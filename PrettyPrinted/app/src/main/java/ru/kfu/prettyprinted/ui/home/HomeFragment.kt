package ru.kfu.prettyprinted.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import ru.kfu.prettyprinted.data.models.User
import ru.kfu.prettyprinted.data.remote.Resource
import ru.kfu.prettyprinted.data.remote.api.UserApi
import ru.kfu.prettyprinted.data.repository.UserRepository
import ru.kfu.prettyprinted.databinding.FragmentHomeBinding
import ru.kfu.prettyprinted.ui.base.BaseFragment
import ru.kfu.prettyprinted.viewmodels.home.HomeViewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    updateUI(it.value.user)
                }
            }
        })
        binding.hpButtonLogout.setOnClickListener{
            logout()
        }
    }

    private fun updateUI(user: User) {
        hp_tv_info.text = "Welcome ${user.id} ${user.name} ${user.surname}"
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        Toast.makeText(this.activity, token, Toast.LENGTH_SHORT).show()
        val api = remoteDataSource.buildApi(UserApi::class.java)
        return UserRepository(api)
    }

}