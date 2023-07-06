package com.example.homework4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homework4.R
import com.example.homework4.UserAdapter
import com.example.homework4.UserAdapterCallbacks
import com.example.homework4.databinding.FragmentRvBinding
import com.example.homework4.fragments.contract.EditButtonClickListener
import com.example.homework4.fragments.contract.SaveButtonListener
import com.example.homework4.model.User
import com.github.javafaker.Faker
import kotlin.random.Random


class FragmentRV : Fragment(R.layout.fragment_rv), UserAdapterCallbacks,
    SaveButtonListener {
    private lateinit var binding: FragmentRvBinding
    private val adapter by lazy { UserAdapter(this) }

    private val _list: MutableLiveData<List<User>> = MutableLiveData(emptyList())
    private val list: LiveData<List<User>> get() = _list
    private val faker = Faker.instance()

    private val users = (1..50).map {
        User(
            id = it.toLong(),
            name = faker.name().name(),
            lastName = faker.name().lastName(),
            phoneNumber = faker.phoneNumber().phoneNumber(),
            avatar = "https://picsum.photos/id/${Random.nextInt(1, 999)}/200/300"
        )
    }.toMutableList()

    init {
        updateList()
    }


    companion object {
        const val FRAGMENT_RV_TAG = "FRAGMENT_RV_TAG"
        fun newInstance() = FragmentRV().apply {
            // arguments = bundleOf( to adapter)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRvBinding.inflate(inflater, container, false)
        initViews()
        observeData()
        return binding.root
    }

    override fun onUserSave(user: User) {
        val currentIndex = users.indexOfFirst { it.id == user.id }
        val updatedUser = users[currentIndex].copy(
            name = user.name,
            lastName = user.lastName,
            phoneNumber = user.phoneNumber,
            avatar = user.avatar
        )
        users[currentIndex] = updatedUser
        updateList()
    }

    override fun onUserEdit(user: User) {
        (requireActivity() as? EditButtonClickListener)?.onEditButtonClicked(user)
    }

    override fun onButtonSaveClicked(user: User) {
        onUserSave(user)
    }

    private fun initViews() {
        binding.recyclerView.adapter = adapter
    }

    private fun observeData() {
        this.list.observe(viewLifecycleOwner) { adapter.items = it }
    }

    private fun updateList() {
        _list.value = users.map { it }
    }
}