package com.blurrays.myjobapp.ui.workfloors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorkfloorsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is workfloors Fragment"
    }
    val text: LiveData<String> = _text
}