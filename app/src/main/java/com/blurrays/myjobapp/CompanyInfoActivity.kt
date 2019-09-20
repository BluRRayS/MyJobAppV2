package com.blurrays.myjobapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blurrays.myjobapp.Classes.Company
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_company_info.*

class CompanyInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_info)

        val mAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        var company :Company = Company()
        db.collection("companies").document(intent.getStringExtra("companyId")).get().addOnSuccessListener { documentSnapshot ->
            company = documentSnapshot.toObject(Company::class.java)!!
            textViewCompanyInfoTest.text = company.name + " " + company.email

        }

        FABReturnToChoosCompany.setOnClickListener{
            val intent = Intent(this, ChooseCompanyActivity::class.java)
            startActivity(intent)
        }
    }
}
