package com.blurrays.myjobapp.WorkfloorActivities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import com.blurrays.myjobapp.Classes.Company
import com.blurrays.myjobapp.Classes.Workfloor
import com.blurrays.myjobapp.MainCompanyOwnerActivity
import com.blurrays.myjobapp.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_workfloor.*
import kotlinx.android.synthetic.main.activity_edit_workfloor.*
import kotlinx.android.synthetic.main.activity_profile.view.*

class EditWorkfloorActivity : AppCompatActivity() {

    private var color = Color.MAGENTA
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_workfloor)
        val db = FirebaseFirestore.getInstance()
        val company: Company = intent.getSerializableExtra("company") as Company
        var workfloor : Workfloor = intent.getSerializableExtra("workfloor") as Workfloor
        val workfloorId : String = intent.getStringExtra("workfloorId")

        seekBarEditWorkfloorAlpha.progress = workfloor.alpha
        seekBarEditWorkfloorRed.progress = workfloor.red
        seekBarEditWorkfloorGreen.progress = workfloor.green
        seekBarEditWorkfloorBlue.progress = workfloor.blue
        editTextEditWorkfloor.setText(workfloor.name)
        imageViewEditWorkfloorColorPreview.setBackgroundColor(Color.argb(workfloor.alpha,workfloor.red,workfloor.green,workfloor.blue))

        seekBarEditWorkfloorAlpha.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                changeColor()
            }

        })
        seekBarEditWorkfloorRed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                changeColor()
            }

        })
        seekBarEditWorkfloorGreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                changeColor()
            }

        })
        seekBarEditWorkfloorBlue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                changeColor()
            }

        })

        FABReturnEditWorkfloor.setOnClickListener {
            val intent = Intent(
                this,
                MainCompanyOwnerActivity::class.java
            )
            intent.putExtra("company", company)
            startActivity(
                intent
            )
        }

        btnEditWorkfloorSave.setOnClickListener {
            workfloor.name = editTextEditWorkfloor.text.toString()
            workfloor.alpha = seekBarEditWorkfloorAlpha.progress
            workfloor.red = seekBarEditWorkfloorRed.progress
            workfloor.green = seekBarEditWorkfloorGreen.progress
            workfloor.blue =seekBarEditWorkfloorBlue.progress
            val docRef = db.collection("workfloors").document(workfloorId)
            docRef.set(workfloor)
            Toast.makeText(this,"Successfully updated " + workfloor.name,Toast.LENGTH_SHORT).show()
        }

    }

    private fun changeColor() {
        color = Color.argb(
            seekBarEditWorkfloorAlpha.progress,
            seekBarEditWorkfloorRed.progress,
            seekBarEditWorkfloorGreen.progress,
            seekBarEditWorkfloorBlue.progress
        )
        imageViewEditWorkfloorColorPreview.setBackgroundColor(color)
    }



}
