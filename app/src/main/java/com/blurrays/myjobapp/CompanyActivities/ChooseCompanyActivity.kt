package com.blurrays.myjobapp.CompanyActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import com.blurrays.myjobapp.Classes.Company
import com.blurrays.myjobapp.CustomAdapters.CompanyArrayAdapter
import com.blurrays.myjobapp.MainCompanyOwnerActivity
import com.blurrays.myjobapp.R
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
        val companys: MutableList<Company> = ArrayList()

        db.collection("companies").whereEqualTo(
            "ownerId",
            mAuth.currentUser?.uid
        ).get().addOnSuccessListener { documentSnapshot ->
            val dbcompanies = documentSnapshot.documents

            var i = 0
            for (item in dbcompanies) {
                companyIds.add(item.id)
                companys.add(item.toObject(Company::class.java)!!)
                companys[i].documentId = companyIds[i]
                i++
            }
            val arrayAdapter = CompanyArrayAdapter(this, companyIds.toTypedArray())
            listBoxCompanies.adapter = arrayAdapter
        }

        listBoxCompanies.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val intent = Intent(this, MainCompanyOwnerActivity::class.java)
                intent.putExtra("company", companys[position])
                intent.putExtra("companyId", companyIds[position])
                startActivity(intent)
            }

        fabCreateCompany.setOnClickListener()
        {
            startActivity(Intent(this, CreateCompanyActivity::class.java))
        }


    }
}
