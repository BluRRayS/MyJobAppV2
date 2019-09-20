package com.blurrays.myjobapp.CompanyActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.blurrays.myjobapp.Classes.Company
import com.blurrays.myjobapp.R
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_edit_company.*

class EditCompanyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_company)
        val db = FirebaseFirestore.getInstance()
        val originalCompany  = intent.getSerializableExtra("company") as Company

        editCompanyName.setText(originalCompany.name)
        editCompanyEmail.setText(originalCompany.email)
        editCompanyPhone.setText(originalCompany.phone)
        editCompanyWebsite.setText(originalCompany.website)
        editComanycCity.setText(originalCompany.city)
        editCompanyStreetname.setText(originalCompany.streetname)
        editCompanyHousenumber.setText(originalCompany.housenumber.toString())
        editCompanySubpremise.setText(originalCompany.subpremise)

        FABBackToCompanyInfo.setOnClickListener{
            val intent = Intent(this,CompanyInfoActivity::class.java)
            intent.putExtra("companyId",originalCompany.documentId)
            startActivity(intent)
        }

        btnEditCompanySave.setOnClickListener {
            var document : DocumentReference = db.collection("companies").document(originalCompany.documentId)
            document.update("name", editCompanyName.text.toString())
            document.update("website",editCompanyWebsite.text.toString())
            document.update("email",editCompanyEmail.text.toString())
            document.update("phone",editCompanyPhone.text.toString())
            document.update("city",editComanycCity.text.toString())
            document.update("streetname",editCompanyStreetname.text.toString())
            document.update("housenumber",editCompanyHousenumber.text.toString().toInt())
            document.update("subpremise",editCompanySubpremise.text.toString()).addOnSuccessListener {

                Toast.makeText(this,"Changes successfully saved",Toast.LENGTH_LONG).show()

            }

        }

        //Todo: Validate forms before updating database

    }
}
