package com.blurrays.myjobapp.ui.schedule.ScheduleDay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.blurrays.myjobapp.R
import com.blurrays.myjobapp.ui.schedule.ScheduleViewModel

class ScheduleDayFragment : Fragment() {

    private lateinit var scheduleDayViewModel: ScheduleDayViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scheduleDayViewModel =
            ViewModelProviders.of(this).get(ScheduleDayViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_scheduleday, container, false)
        val textView: TextView = root.findViewById(R.id.ScheduleDaysText)
        scheduleDayViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}