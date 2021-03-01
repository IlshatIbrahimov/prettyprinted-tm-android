package ru.kfu.prettyprinted.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.kfu.prettyprinted.ui.auth.AuthActivity
import ru.kfu.prettyprinted.data.delegates.UserPreferences
import ru.kfu.prettyprinted.data.remote.api.UserApi
import ru.kfu.prettyprinted.data.remote.managers.NetworkManager
import ru.kfu.prettyprinted.data.remote.managers.NetworkUserManager
import ru.kfu.prettyprinted.data.repository.BaseRepository
import ru.kfu.prettyprinted.extensions.startNewActivity
import ru.kfu.prettyprinted.viewmodels.base.BaseViewModel
import ru.kfu.prettyprinted.viewmodels.base.ViewModelFactory

abstract class BaseFragment<VM : BaseViewModel, B : ViewBinding, R : BaseRepository> : Fragment() {

    protected lateinit var userPreferences: UserPreferences
    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected val remoteDataSource = NetworkManager()
    protected val remoteDataUserSource = NetworkUserManager()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userPreferences = UserPreferences(requireContext())
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())

        lifecycleScope.launch { userPreferences.authToken.first() }
        return binding.root
    }

    fun logout() = lifecycleScope.launch {
        val authToken = userPreferences.authToken.first()
        val api  = remoteDataUserSource.buildUserApi(UserApi::class.java, authToken)
        viewModel.logout(api)
        userPreferences.clear()
        requireActivity().startNewActivity(AuthActivity::class.java)
    }

    abstract fun getViewModel(): Class<VM>


    abstract fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): B

    abstract fun getFragmentRepository(): R
}