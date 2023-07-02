package com.example.homework4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework4.databinding.ActivityMainBinding
import com.example.homework4.fragments.FragmentA

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState==null){
            val fragmentA = FragmentA.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView,fragmentA)
                .commit()
        }
    }

}