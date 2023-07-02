package com.example.homework4.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homework4.R
import com.example.homework4.databinding.FragmentABinding

class FragmentA : Fragment() {
    private lateinit var binding : FragmentABinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentABinding.inflate(inflater,container,false)
        binding.buttonAtoB.setOnClickListener { launchB() }
        return binding.root
    }

    companion object {
        fun newInstance(): FragmentA {
            val args = Bundle().apply { }
            val fragment = FragmentA()
            fragment.arguments = args
            return fragment
        }
    }
    private fun launchB(){
        val fragmentB = FragmentB.newInstance()
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainerView,fragmentB)
            .commit()
    }
}