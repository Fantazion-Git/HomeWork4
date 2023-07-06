package com.example.homework4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.homework4.databinding.ActivityMainBinding
import com.example.homework4.fragments.FragmentEdit
import com.example.homework4.fragments.FragmentRV
import com.example.homework4.fragments.contract.EditButtonClickListener
import com.example.homework4.fragments.contract.SaveButtonListener
import com.example.homework4.model.User

class MainActivity : AppCompatActivity(), EditButtonClickListener, SaveButtonListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            val fragmentRV = FragmentRV.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, fragmentRV)
                .commit()
        }
    }

    override fun onEditButtonClicked(user: User) {
        supportFragmentManager.commit {
            replace(
                binding.fragmentContainerViewEdit.id, FragmentEdit.newInstance(
                    user.id,
                    user.avatar,
                    user.name,
                    user.lastName,
                    user.phoneNumber
                ),
                FragmentEdit.FRAGMENT_EDIT_TAG
            )
            addToBackStack(FragmentEdit.FRAGMENT_EDIT_TAG)
        }
    }

    override fun onButtonSaveClicked(user: User) {
        (binding.fragmentContainerView.getFragment<FragmentRV>() as? SaveButtonListener)?.onButtonSaveClicked(
            user
        )
        supportFragmentManager.popBackStack()
    }
}

