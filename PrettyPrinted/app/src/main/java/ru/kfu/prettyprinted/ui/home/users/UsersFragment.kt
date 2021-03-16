package ru.kfu.prettyprinted.ui.home.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_users.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import ru.kfu.prettyprinted.R
import ru.kfu.prettyprinted.data.models.User
import ru.kfu.prettyprinted.data.remote.Resource
import ru.kfu.prettyprinted.data.remote.api.UserApi
import ru.kfu.prettyprinted.data.repository.UserRepository
import ru.kfu.prettyprinted.databinding.FragmentUsersBinding
import ru.kfu.prettyprinted.extensions.MyOnClickListener
import ru.kfu.prettyprinted.extensions.snackbar
import ru.kfu.prettyprinted.ui.base.BaseFragment
import ru.kfu.prettyprinted.ui.home.HomeActivity
import ru.kfu.prettyprinted.viewmodels.home.UsersViewModel

class UsersFragment : BaseFragment<UsersViewModel, FragmentUsersBinding, UserRepository>() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: UsersAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
    }

    private fun initRecycleView() {
        mRecyclerView = rv_users
        mAdapter = UsersAdapter(object : MyOnClickListener{
            override fun onClicked(tag: Any) {
                val bundle = Bundle()
                bundle.putInt("id", 1)
                bundle.putString("name", "")
                bundle.putString("surname", "")
                (activity as HomeActivity).navController.navigate(R.id.action_page_users_to_nav_create_task, bundle)
            }

        })

        viewModel.getUser()
        viewModel.user.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    it.value.forEach { item ->
                        updateUI(item)
                    }

                }
                is Resource.Failure -> requireView().snackbar("Упс, что-то пошло не так")
            }
        })

        mRecyclerView.adapter = mAdapter
    }

    private fun updateUI(item: User) {
        mAdapter.updateListUsers(item)
    }


    override fun getViewModel() = UsersViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentUsersBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataUserSource.buildTokenApi(UserApi::class.java, token)
        return UserRepository(api)
    }

}