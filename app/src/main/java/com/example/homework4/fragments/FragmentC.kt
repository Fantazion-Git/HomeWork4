package com.example.homework4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.example.homework4.R
import com.example.homework4.databinding.FragmentCBinding

class FragmentC : Fragment() {
    private lateinit var binding: FragmentCBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCBinding.inflate(inflater, container, false)
        binding.buttonCtoA.setOnClickListener { backToA() }
        binding.buttonCtoD.setOnClickListener { launchD() }
        binding.textC.text = getTextFromFragment()
        return binding.root
    }

    companion object {
        fun newInstance(string: String): FragmentC {
            val args = Bundle().apply {
                putString("stringFromB", string)
            }
            val fragment = FragmentC()
            fragment.arguments = args
            return fragment
        }
    }

    private fun getTextFromFragment(): String? = requireArguments().getString("stringFromB")


    private fun backToA() {
        parentFragmentManager
            .popBackStack(null, POP_BACK_STACK_INCLUSIVE)
    }

    private fun launchD() {
        val fragmentD = FragmentD.newInstance()
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainerView, fragmentD)
            .commit()
    }
}