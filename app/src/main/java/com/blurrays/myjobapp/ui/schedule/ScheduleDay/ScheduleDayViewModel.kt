package com.blurrays.myjobapp.ui.schedule.ScheduleDay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScheduleDayViewModel: ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the scheduleDay Fragment"
    }
    val text: LiveData<String> = _text
}