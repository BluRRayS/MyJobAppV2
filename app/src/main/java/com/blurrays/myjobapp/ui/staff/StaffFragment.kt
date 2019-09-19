package com.blurrays.myjobapp.ui.staff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.blurrays.myjobapp.R

class StaffFragment : Fragment() {

    private lateinit var staffViewModel: StaffViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        staffViewModel =
            ViewModelProviders.of(this).get(StaffViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_staff, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        staffViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}