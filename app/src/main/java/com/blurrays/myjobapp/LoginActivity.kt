package com.blurrays.myjobapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import android.content.Intent
import com.blurrays.myjobapp.Classes.Account
import com.blurrays.myjobapp.Classes.UserType
import com.blurrays.myjobapp.CompanyActivities.ChooseCompanyActivity
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.login)

        register.setOnClickListener()
        {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }

        button.setOnClickListener()
        {
            if(formOk()) {
                LoginUser()
            }
        }



    }

    fun  formOk() :Boolean
    {
        if(login_email_input.text.toString().isEmpty() || login_password_input.text.toString().isEmpty())
        {
            Toast.makeText(this,"Fields cannot be empty!",Toast.LENGTH_SHORT).show()
            return false
        }
        else
        {
            return true
        }
    }

    fun LoginUser() {
        val db = FirebaseFirestore.getInstance()
        val email = findViewById<EditText>(R.id.login_email_input)
        val password = findViewById<EditText>(R.id.login_password_input)
        val mAuth = FirebaseAuth.getInstance()


        val Email = email.getText().toString().trim()
        val Password = password.getText().toString().trim()
        mAuth.signInWithEmailAndPassword(Email, Password)
            .addOnCompleteListener(this,
                OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this,"Login Successful!",Toast.LENGTH_SHORT).show()
                        //finish()
                    } else {
                        Toast.makeText(
                            this@LoginActivity, "couldn't login",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })

        var account = Account()
        val user = mAuth.currentUser
        if (user != null) {
            val userInfo = db.collection("users").document(user.uid)
            userInfo.get()
                .addOnSuccessListener { document ->
                    val dbaccount = document.toObject(Account::class.java)
                    if (dbaccount != null) {
                        account = dbaccount
                    }
                    if (account.type == UserType.CompanyOwner) {
                        startActivity(Intent(applicationContext, ChooseCompanyActivity::class.java))
                    } else if (account.type == UserType.Employee)
                        startActivity(Intent(applicationContext, ProfileActivity::class.java))
                }
        }


    }
}
