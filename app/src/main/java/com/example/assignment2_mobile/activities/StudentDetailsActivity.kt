package com.example.assignment2_mobile.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment2_mobile.R
import com.example.studentsapp.Student

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var imageViewPhoto: ImageView
    private lateinit var textViewName: TextView
    private lateinit var textViewId: TextView
    private lateinit var textViewPhone: TextView
    private lateinit var textViewAddress: TextView
    private lateinit var textViewChecked: TextView
    private lateinit var buttonEdit: Button

    private lateinit var student: Student

    private val editStudentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                setResult(Activity.RESULT_OK, result.data) // Forward result to StudentListActivity
                finish() // Close StudentDetailsActivity
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)
        supportActionBar?.title = "Student Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        imageViewPhoto = findViewById(R.id.imageViewStudentPhoto)
        textViewName = findViewById(R.id.textViewName)
        textViewId = findViewById(R.id.textViewId)
        textViewPhone = findViewById(R.id.textViewPhone)
        textViewAddress = findViewById(R.id.textViewAddress)
        textViewChecked = findViewById(R.id.textViewChecked)
        buttonEdit = findViewById(R.id.buttonEditStudent)

        student = intent.getParcelableExtra("student")!!

        textViewName.text = "Name: ${student.name}"
        textViewId.text = "ID: ${student.id}"
        textViewPhone.text = "Phone: ${student.phone}"
        textViewAddress.text = "Address: ${student.address}"
        textViewChecked.text = "Checked: ${if (student.isChecked) "Yes" else "No"}"

        buttonEdit.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("student", student)
            editStudentLauncher.launch(intent) // Launch EditStudentActivity
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
