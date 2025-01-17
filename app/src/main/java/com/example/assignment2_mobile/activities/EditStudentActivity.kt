package com.example.assignment2_mobile.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment2_mobile.R
import com.example.studentsapp.Student

class EditStudentActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextId: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var editTextAddress: EditText
    private lateinit var checkBoxChecked: CheckBox
    private lateinit var buttonSave: Button
    private lateinit var buttonCancel: Button
    private lateinit var buttonDelete: Button

    private lateinit var student: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)
        supportActionBar?.title = "Edit Student"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        editTextName = findViewById(R.id.editTextName)
        editTextId = findViewById(R.id.editTextId)
        editTextPhone = findViewById(R.id.editTextPhone)
        editTextAddress = findViewById(R.id.editTextAddress)
        checkBoxChecked = findViewById(R.id.checkBoxChecked)
        buttonSave = findViewById(R.id.buttonSaveStudent)
        buttonCancel = findViewById(R.id.buttonCancelEdit)
        buttonDelete = findViewById(R.id.buttonDeleteStudent)

        student = intent.getParcelableExtra("student")!!

        editTextName.setText(student.name)
        editTextId.setText(student.id)
        editTextPhone.setText(student.phone)
        editTextAddress.setText(student.address)
        checkBoxChecked.isChecked = student.isChecked

        buttonSave.setOnClickListener {
            val oldId = student.id

            student.name = editTextName.text.toString()
            student.id = editTextId.text.toString()
            student.phone = editTextPhone.text.toString()
            student.address = editTextAddress.text.toString()
            student.isChecked = checkBoxChecked.isChecked

            val resultIntent = Intent()
            resultIntent.putExtra("updatedStudent", student)
            resultIntent.putExtra("oldStudentId", oldId)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        buttonCancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        buttonDelete.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("deletedStudentId", student.id)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
