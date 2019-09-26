package com.blurrays.myjobapp.WorkfloorActivities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.WorkSource
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.graphics.*
import com.blurrays.myjobapp.Classes.Company
import com.blurrays.myjobapp.Classes.Workfloor
import com.blurrays.myjobapp.CompanyActivities.ChooseCompanyActivity
import com.blurrays.myjobapp.MainCompanyOwnerActivity
import com.blurrays.myjobapp.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_workfloor.*
import java.lang.Exception


class AddWorkfloorActivity : AppCompatActivity() {

    private var color = Color.MAGENTA
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_workfloor)
        val db = FirebaseFirestore.getInstance()
        val company: Company = intent.getSerializableExtra("company") as Company

        //Change current color with sliders
        seekBarAlpha.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                changeColor()
            }

        })
        seekBarRed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                changeColor()
            }

        })
        seekBarGreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                changeColor()
            }

        })
        seekBarBlue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                changeColor()
            }

        })

        btnAddWorkfloor.setOnClickListener {
            try {
                if (editTextWorkfloorName.text.isNotEmpty()) {
                    var newWorkfloor =
                        Workfloor(company.documentId, editTextWorkfloorName.text.toString(),color.alpha,color.red,color.green,color.blue)
                    db.collection("workfloors").document().set(newWorkfloor)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Workfloor created successfully", Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(
                                this,
                                MainCompanyOwnerActivity::class.java
                            )
                            intent.putExtra("company", company)
                            startActivity(
                                intent
                            )
                        }
                        .addOnFailureListener {
                            Toast.makeText(
                                this,
                                "An error occurred failed to create Workfloor",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                } else Toast.makeText(this, "Name cannot be empty!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {

            }

        }

        FABReturnToWorkfloors.setOnClickListener {
            val intent = Intent(
                this,
                MainCompanyOwnerActivity::class.java
            )
            intent.putExtra("company", company)
            startActivity(
                intent
            )

        }


    }

    private fun changeColor() {
        color = Color.argb(
            seekBarAlpha.progress,
            seekBarRed.progress,
            seekBarGreen.progress,
            seekBarBlue.progress
        )
        ColorPreview.setBackgroundColor(color)
    }
}
