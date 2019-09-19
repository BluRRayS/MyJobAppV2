package com.blurrays.myjobapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.blurrays.myjobapp.Classes.Account
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val mAuth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val user = mAuth.getCurrentUser()
        if(user != null)
        {
            val userInfo = db.collection("users").document(user.uid)
            userInfo.get()
                .addOnSuccessListener { document ->
                    val account = document.toObject(Account::class.java)
                    if (account != null) {
                        textViewDebug.text ="Welcome "+  account.firstname + " " + account.lastname
                    }
                }
        }


        val Email = findViewById<TextView>(R.id.profileEmail)
        val Uid = findViewById<TextView>(R.id.profileUid)

        val logout = findViewById<Button>(R.id.button_logout)


        if (user != null) {
            val email = user.getEmail()
            val uid = user.getUid()
            Email.setText(email)
            Uid.setText(uid)
        }

        logout.setOnClickListener()
        {
            mAuth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }




//        val docRef = db.collection("cities").document("SF")
//        docRef.get()
//            .addOnSuccessListener { document ->
//                if (document != null) {
//                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
//                } else {
//                    Log.d(TAG, "No such document")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d(TAG, "get failed with ", exception)
//            }
    }
}
