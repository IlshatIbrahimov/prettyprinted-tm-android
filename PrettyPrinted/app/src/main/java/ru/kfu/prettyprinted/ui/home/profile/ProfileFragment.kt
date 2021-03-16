package ru.kfu.prettyprinted.ui.home.profile

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.data.models.User
import ru.kfu.prettyprinted.data.remote.Resource
import ru.kfu.prettyprinted.data.remote.api.UserApi
import ru.kfu.prettyprinted.data.repository.UserRepository
import ru.kfu.prettyprinted.databinding.FragmentProfileBinding
import ru.kfu.prettyprinted.ui.base.BaseFragment
import ru.kfu.prettyprinted.viewmodels.home.UsersViewModel

class ProfileFragment : BaseFragment<UsersViewModel, FragmentProfileBinding, UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    updateUI(it.value[0])
                }
            }
        })
    }

    private fun updateUI(user: User) {
        with(binding) {
            hpTvId.text = user.id.toString()
            hpTvName.text = user.name
            hpTvSurname.text = user.surname
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.profile_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_exit -> {
               logout()
            }
        }
        return true
    }

    override fun getViewModel() = UsersViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProfileBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataUserSource.buildTokenApi(UserApi::class.java, token)
        return UserRepository(api)
    }

}