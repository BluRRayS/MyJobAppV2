package com.blurrays.myjobapp.ui.employees

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EmployeeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is employee Fragment"
    }
    val text: LiveData<String> = _text
}