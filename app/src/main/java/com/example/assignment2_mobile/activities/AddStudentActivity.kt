package com.example.assignment2_mobile.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment2_mobile.R
import com.example.studentsapp.Student

class AddStudentActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextId: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var editTextAddress: EditText
    private lateinit var checkBoxChecked: CheckBox
    private lateinit var buttonSave: Button
    private lateinit var buttonCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        editTextName = findViewById(R.id.editTextName)
        editTextId = findViewById(R.id.editTextId)
        editTextPhone = findViewById(R.id.editTextPhone)
        editTextAddress = findViewById(R.id.editTextAddress)
        checkBoxChecked = findViewById(R.id.checkBoxChecked)
        buttonSave = findViewById(R.id.buttonSaveStudent)
        buttonCancel = findViewById(R.id.buttonCancelStudent)

        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            val id = editTextId.text.toString()
            val phone = editTextPhone.text.toString()
            val address = editTextAddress.text.toString()
            val isChecked = checkBoxChecked.isChecked

            val newStudent = Student(id, name, phone, address, isChecked)

            val resultIntent = Intent()
            resultIntent.putExtra("newStudent", newStudent)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        buttonCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}
