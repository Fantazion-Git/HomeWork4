package com.example.homework4

import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.example.homework4.databinding.ItemUserBinding
import com.example.homework4.model.User
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

interface UserAdapterCallbacks {
    fun onUserEdit(user: User)
    fun onUserSave(user: User)
}

class UserAdapter(
    callbacks: UserAdapterCallbacks
) : AsyncListDifferDelegationAdapter<User>(UserDiffUtilCallback) {

    private object UserDiffUtilCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem

    }

    init {
        delegatesManager.addDelegate(createUserAdapterDelegate(callbacks))
    }

    private fun createUserAdapterDelegate(
        callbacks: UserAdapterCallbacks
    ) = adapterDelegateViewBinding<User, User, ItemUserBinding>(
        { inflater, parent -> ItemUserBinding.inflate(inflater, parent, false) }
    ) {
        with(binding) {
            itemEdit.setOnClickListener { callbacks.onUserEdit(item) }
        }

        bind {
            binding.itemName.text = item.name
            binding.itemLastName.text = item.lastName
            binding.itemPhoneNumber.text = item.phoneNumber
            binding.itemAvatar.load(item.avatar)
        }
    }
}