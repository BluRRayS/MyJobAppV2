package com.blurrays.myjobapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.blurrays.myjobapp.Classes.Company
import com.blurrays.myjobapp.CustomAdapters.CompanyArrayAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_choose_company.*

class ChooseCompanyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_company)

        val mAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        var userCompanies: MutableList<Company> = arrayListOf()
        val companyIds: MutableList<String> = ArrayList()

        db.collection("companies").whereEqualTo("ownerId",
            mAuth.currentUser?.uid
        ).get().addOnSuccessListener { documentSnapshot ->
            val dbcompanies = documentSnapshot.documents

            for(item in dbcompanies )
            {
                companyIds.add(item.id)
            }
            val arrayAdapter = CompanyArrayAdapter(this, companyIds.toTypedArray())
            listBoxCompanies.adapter = arrayAdapter
        }

        listBoxCompanies.onItemClickListener =  AdapterView.OnItemClickListener {parent,view, position, id ->
            val intent = Intent(this, MainCompanyOwnerActivity::class.java)
            intent.putExtra("companyId",companyIds[position])
            startActivity(intent)
        }

        fabCreateCompany.setOnClickListener()
        {
            startActivity(Intent(this, CreateCompanyActivity::class.java))
        }


    }
}
