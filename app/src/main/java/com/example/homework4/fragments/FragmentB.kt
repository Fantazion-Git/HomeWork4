package com.example.homework4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homework4.R
import com.example.homework4.databinding.FragmentBBinding

class FragmentB:Fragment() {
    private lateinit var binding : FragmentBBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentBBinding.inflate(inflater,container,false)
        binding.buttonBtoC.setOnClickListener { launchC() }
        binding.buttonBtoA.setOnClickListener { back() }
        return binding.root
    }


    companion object {
        fun newInstance(): FragmentB {
            val args = Bundle().apply {
            }
            val fragment = FragmentB()
            fragment.arguments = args
            return fragment
        }
    }

    private fun launchC(){
        val fragmentC = FragmentC.newInstance("Hello Fragment C")
        parentFragmentManager
            .beginTransaction()
            .addToBackStack("FragmentB")
            .replace(R.id.fragmentContainerView,fragmentC)
            .commit()
    }
    private fun back(){
        parentFragmentManager
            .popBackStack()
    }
}