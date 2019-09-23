package com.blurrays.myjobapp.ui.roles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.blurrays.myjobapp.R


class RolesFragment : Fragment(){

    private lateinit var rolesViewModel: RolesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rolesViewModel =
            ViewModelProviders.of(this).get(RolesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_roles, container, false)
        val textView: TextView = root.findViewById(R.id.text_roles)
        rolesViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}