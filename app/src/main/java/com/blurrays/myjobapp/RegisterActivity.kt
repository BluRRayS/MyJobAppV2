package com.blurrays.myjobapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import android.content.Intent
import android.text.TextUtils
import android.util.Patterns
import com.blurrays.myjobapp.Classes.Account
import com.blurrays.myjobapp.Classes.UserType
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Buttons
        val button_register = findViewById<Button>(R.id.button_register)
        val button_login = findViewById<Button>(R.id.button_login)

        //Form Elements
        val textboxEmail = findViewById<EditText>(R.id.signup_email_input)
        val textboxFirstname = findViewById<EditText>(R.id.signup_firstname_input)
        val textboxLastname = findViewById<EditText>(R.id.signup_lastname_input)
        val textboxPassword = findViewById<EditText>(R.id.signup_password_input)
        val textboxConfirmPassword = findViewById<EditText>(R.id.signup_repeatpassword_input)
        val textboxPhone = findViewById<EditText>(R.id.signup_phone_input)

        button_register.setOnClickListener()
        {
            //List to iterate all form elements
            var textboxList = listOf(
                textboxEmail,
                textboxFirstname,
                textboxLastname,
                textboxPassword,
                textboxConfirmPassword,
                textboxPhone
            )
            registerUser(textboxList)
        }
        button_login.setOnClickListener()
        {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }


    private fun registerUser(editTextList: List<EditText>) {
        val email = findViewById<EditText>(R.id.signup_email_input)
        val password = findViewById<EditText>(R.id.signup_password_input)

        val mAuth = FirebaseAuth.getInstance()

        val Email = email.getText().toString().trim()
        val Password = password.getText().toString().trim()

        if(formIsEmpty(editTextList))
        {
            Toast.makeText(this,"A field is Empty!",Toast.LENGTH_SHORT).show()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(signup_email_input.text).matches()) {
            signup_email_input.error = "Email address is not valid"
            return
        }
        if (signup_password_input.text.toString() != signup_repeatpassword_input.text.toString()) {
            signup_password_input.error = "Passwords don't match!"
            signup_repeatpassword_input.error = "Passwords don't match!"
            return
        }
        if(!checkBox_Agree.isChecked)
        {
            Toast.makeText(this,"You must agree to our terms and conditions!",Toast.LENGTH_SHORT).show()
            return
        }
        var usertype = UserType.Employee
        if(checkBox_CompanyOwner.isChecked)
        {
            usertype = UserType.CompanyOwner
        }


        mAuth.createUserWithEmailAndPassword(Email, Password)
            .addOnCompleteListener(
                this
            ) { task ->
                try {
                    //check if successful
                    if (task.isSuccessful) {
                        //User is successfully registered and logged in
                        //start Profile Activity here
                        val account =
                            Account(signup_firstname_input.text.toString(), signup_lastname_input.text.toString(),signup_phone_input.text.toString(), usertype  )
                        // Add a new document with a generated id.
                        var userUid = mAuth.uid
                        val db = FirebaseFirestore.getInstance()
                        if (userUid != null) {
                            db.collection("users")
                                .document(userUid).set(account)
                        }
                        Toast.makeText(
                            this@RegisterActivity, "registration successful",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                        val registerIntent = Intent(this, ProfileActivity::class.java)
                        startActivity(registerIntent)
                    } else {
                        Toast.makeText(
                            this@RegisterActivity, "Couldn't register, try again",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
    }

    private fun formIsEmpty (editTextList: List<EditText>): Boolean {
        //Check all editTexts
        var isEmpty = false
        for (item in editTextList) {
            if (item.text.isNullOrEmpty()) {
                item.error = "This field cannot be empty"
                isEmpty = true
            }
        }
        return isEmpty
    }


}



