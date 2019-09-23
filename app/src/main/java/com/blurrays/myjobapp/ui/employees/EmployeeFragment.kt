package com.blurrays.myjobapp.ui.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.blurrays.myjobapp.R

class EmployeeFragment : Fragment() {

    private lateinit var employeeViewModel: EmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        employeeViewModel =
            ViewModelProviders.of(this).get(EmployeeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_employees, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        employeeViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}