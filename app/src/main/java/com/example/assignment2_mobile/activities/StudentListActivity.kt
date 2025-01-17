package com.example.assignment2_mobile.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2_mobile.R
import com.example.studentsapp.Student
import com.example.studentsapp.StudentAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentListActivity : AppCompatActivity() {

    private val studentList = mutableListOf<Student>()
    private lateinit var adapter: StudentAdapter

    private val addStudentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val newStudent = result.data?.getParcelableExtra<Student>("newStudent")
                newStudent?.let {
                    studentList.add(it)
                    adapter.notifyItemInserted(studentList.size - 1)
                }
            }
        }

    private val editStudentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // Handle update (edited student)
                val updatedStudent = result.data?.getParcelableExtra<Student>("updatedStudent")
                val oldStudentId = result.data?.getStringExtra("oldStudentId")
                updatedStudent?.let { student ->
                    val index = studentList.indexOfFirst { it.id == oldStudentId ?: student.id }
                    if (index != -1) {
                        Log.d("StudentListActivity", "Updating student at index $index with ID ${student.id}")
                        studentList[index] = student
                        adapter.notifyItemChanged(index)
                    }
                }

                val deletedStudentId = result.data?.getStringExtra("deletedStudentId")
                deletedStudentId?.let { id ->
                    val index = studentList.indexOfFirst { it.id == id }
                    if (index != -1) {
                        studentList.removeAt(index)
                        adapter.notifyItemRemoved(index)
                    }
                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        supportActionBar?.title = "Students List"

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView_students)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        adapter = StudentAdapter(studentList, object : StudentAdapter.OnItemClickListener {
            override fun onStudentClick(student: Student) {
                val intent = Intent(this@StudentListActivity, StudentDetailsActivity::class.java)
                intent.putExtra("student", student)
                editStudentLauncher.launch(intent) // Use the launcher to navigate
            }

            override fun onCheckChanged(student: Student, isChecked: Boolean) {
                student.isChecked = isChecked
            }
        })

        recyclerView.adapter = adapter

        val fab: FloatingActionButton = findViewById(R.id.fab_add_student)
        fab.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            addStudentLauncher.launch(intent)
        }
    }
}
