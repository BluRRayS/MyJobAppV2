package com.blurrays.myjobapp.ui.workfloors

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.graphics.toColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.blurrays.myjobapp.Classes.Workfloor
import com.blurrays.myjobapp.CustomAdapters.CompanyArrayAdapter
import com.blurrays.myjobapp.CustomAdapters.WorkfloorArrayAdapter

import com.blurrays.myjobapp.WorkfloorActivities.AddWorkfloorActivity
import kotlinx.android.synthetic.main.fragment_workfloors.*

import com.blurrays.myjobapp.MainCompanyOwnerActivity
import com.blurrays.myjobapp.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_choose_company.*
import java.lang.Exception


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
        val textView: TextView = root.findViewById(R.id.text_workfloors)
        workfloorsViewModel.text.observe(this, Observer {
            textView.text = it


        })
        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val db = FirebaseFirestore.getInstance()
        var workfloorIds = ArrayList<String>()

        FABAddWorkfloor.setOnClickListener { view ->
            val intent = Intent(context, AddWorkfloorActivity::class.java)
            intent.putExtra("company", (activity as MainCompanyOwnerActivity).getCompany())
            startActivity(intent)
        }

        try {
            val docRef = db.collection("workfloors").whereEqualTo(
                "companyId",
                (activity as MainCompanyOwnerActivity).getCompany().documentId
            )
            docRef.get().addOnSuccessListener { documents ->
                for (item in documents) {
                    workfloorIds.add(item.id)
                    val arrayAdapter = WorkfloorArrayAdapter(context, workfloorIds.toTypedArray())
                    ListViewWorkfloors.adapter = arrayAdapter
                }
            }


        } catch (e: Exception) {
            Log.e("ERROR", e.toString())
        }

    }


}