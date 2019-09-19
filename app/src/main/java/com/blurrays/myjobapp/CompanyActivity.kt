package com.blurrays.myjobapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blurrays.myjobapp.Classes.Company
import com.blurrays.myjobapp.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_company_owner.*

class CompanyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_owner)



    }
}
