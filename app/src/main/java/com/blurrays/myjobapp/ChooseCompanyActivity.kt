package com.blurrays.myjobapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.blurrays.myjobapp.Classes.Company
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_choose_company.*

class ChooseCompanyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_company)

        val mAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        var userCompanies: MutableList<Company> = ArrayList()
        db.collection("companies").whereEqualTo("ownerId",
            mAuth.currentUser?.uid
        ).get().addOnSuccessListener { documentSnapshot ->
            var dbcompanies = documentSnapshot.toObjects(Company::class.java)
            userCompanies = dbcompanies
        }

        val arrayAdapter = ArrayAdapter<Company>(this,android.R.layout.simple_list_item_1,userCompanies)
        listBoxCompanies.adapter = arrayAdapter



        fabCreateCompany.setOnClickListener()
        {
            startActivity(Intent(this, CreateCompanyActivity::class.java))
        }


    }
}
