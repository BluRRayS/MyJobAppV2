package com.blurrays.myjobapp.ui.workfloors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.blurrays.myjobapp.R

class WorkfloorsFragment : Fragment() {

    private lateinit var workfloorsViewModel: WorkfloorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        workfloorsViewModel =
            ViewModelProviders.of(this).get(WorkfloorsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_workfloors, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        workfloorsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}