package com.example.homework4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.load
import com.example.homework4.R
import com.example.homework4.databinding.FragmentEditBinding
import com.example.homework4.fragments.contract.SaveButtonListener
import com.example.homework4.model.User
import java.lang.RuntimeException

class FragmentEdit : Fragment(R.layout.fragment_edit) {
    private lateinit var user: User

    companion object {
        const val FRAGMENT_EDIT_TAG = "FRAGMENT_EDIT_TAG"
        fun newInstance(
            id: Long, avatar: String, name: String, lastName: String, phoneNumber: String
        ) = FragmentEdit().apply {
            arguments = bundleOf(
                "id" to id,
                "avatar" to avatar,
                "name" to name,
                "lastName" to lastName,
                "phoneNumber" to phoneNumber
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = arguments?.let {
            User(
                id = it.getLong("id"),
                avatar = it.getString("avatar").toString(),
                name = it.getString("name").toString(),
                lastName = it.getString("lastName").toString(),
                phoneNumber = it.getString("phoneNumber").toString()
            )
        } ?: throw RuntimeException("No arguments in FragmentEdit")
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEditBinding.inflate(inflater, container, false)
        binding.editAvatar.load(user.avatar)
        binding.editName.setText(user.name)
        binding.editLastName.setText(user.lastName)
        binding.editPhoneNumber.setText(user.phoneNumber)
        binding.editURL.setText(user.avatar)
        binding.editSave.setOnClickListener {
            (requireActivity() as? SaveButtonListener)?.onButtonSaveClicked(
                User(
                    user.id,
                    binding.editURL.text.toString(),
                    binding.editName.text.toString(),
                    binding.editLastName.text.toString(),
                    binding.editPhoneNumber.text.toString()
                )
            )
        }
        return binding.root
    }

//    interface SaveButtonListener {
//        fun onButtonSaveClicked(user: User)
//    }
}