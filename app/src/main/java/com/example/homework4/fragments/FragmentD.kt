package com.example.homework4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.example.homework4.databinding.FragmentDBinding

class FragmentD : Fragment() {
    private lateinit var binding: FragmentDBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDBinding.inflate(inflater, container, false)
        binding.buttonDtoB.setOnClickListener { backToB() }
        return binding.root
    }

    companion object {
        fun newInstance(): FragmentD {
            val args = Bundle().apply {
            }
            val fragment = FragmentD()
            fragment.arguments = args
            return fragment
        }
    }

    private fun backToB() {
        parentFragmentManager.popBackStackImmediate("FragmentB",POP_BACK_STACK_INCLUSIVE)
    }
}