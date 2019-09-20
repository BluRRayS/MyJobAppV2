package com.blurrays.myjobapp.ui.manage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.blurrays.myjobapp.Classes.Company
import com.blurrays.myjobapp.R
import kotlinx.android.synthetic.main.fragment_manage.*


class ManageFragment : Fragment() {

    private lateinit var manageViewModel: ManageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        manageViewModel =
            ViewModelProviders.of(this).get(ManageViewModel::class.java)
        val root = inflater.inflate(com.blurrays.myjobapp.R.layout.fragment_manage, container, false)
        val textView: TextView = root.findViewById(com.blurrays.myjobapp.R.id.text_manage)
        manageViewModel.text.observe(this, Observer {
            textView.text = it
        })

        val company = arguments?.getSerializable("userCompany") as Company
        textViewCompanyInfo.text = company.name
        return root




    }

}