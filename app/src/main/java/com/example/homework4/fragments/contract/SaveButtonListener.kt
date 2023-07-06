package com.example.homework4.fragments.contract

import com.example.homework4.model.User

interface SaveButtonListener {
    fun onButtonSaveClicked(user: User)
}