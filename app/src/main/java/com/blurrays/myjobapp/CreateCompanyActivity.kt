package com.blurrays.myjobapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.blurrays.myjobapp.Classes.Company
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_create_company.*
import kotlinx.android.synthetic.main.activity_register.*
import java.lang.Exception
import java.util.regex.Pattern

class CreateCompanyActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    val user = mAuth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_company)


        FABBackToChooseCompany.setOnClickListener {
            startActivity(Intent(this, ChooseCompanyActivity::class.java))
        }

        BtnCreateCompany.setOnClickListener {
            var editTextList = listOf<EditText>(
                TxtBoxCompanyEmail,
                TxtBoxCompanyName
            )
            createCompany(editTextList)
        }
    }

    private fun formIsEmpty(textboxList: List<EditText>): Boolean {
        var isEmpty = false
        for (item in textboxList) {
            if (item.text.isNullOrEmpty()) {
                item.error = "This field cannot be empty"
                isEmpty = true
            }
        }
        return isEmpty
    }

    private fun createCompany(textboxList: List<EditText>) {
        if (formIsEmpty(textboxList)) {
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(TxtBoxCompanyEmail.text).matches()) {
            TxtBoxCompanyEmail.error = "Email address is not valid"
            return
        }
        try {
            var newCompany = Company(
                user?.uid,
                TxtBoxCompanyName.text.toString(),
                TxtBoxCompanyEmail.text.toString(),
                TxtBoxCompanyPhone.text.toString(),
                TxtBoxCompanyWebsite.text.toString(),
                TxtBoxCompanyCity.text.toString(),
                TxtBoxCompanyStreetname.text.toString(),
                TxtBoxCompanyHouseNumber.text.toString().toInt(),
                TxtBoxCompanySubPremise.text.toString()
            )
            db.collection("companies").document().set(newCompany)
                .addOnSuccessListener {
                    Toast.makeText(this,"Company created successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,ChooseCompanyActivity::class.java))
                }
                .addOnFailureListener {
                    Toast.makeText(this,"An error occurred failed to create company", Toast.LENGTH_LONG).show()
                }

        } catch (e: Exception) {
            Log.e(e.toString(), "Couldn't create company in Firebase")
        }

    }

}
