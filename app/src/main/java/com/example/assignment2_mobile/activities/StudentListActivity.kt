package com.example.assignment2_mobile.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2_mobile.R
import com.example.assignment2_mobile.activities.AddStudentActivity
import com.example.assignment2_mobile.activities.StudentDetailsActivity
import com.example.studentsapp.Student
import com.example.studentsapp.StudentAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentListActivity : AppCompatActivity() {

    private val studentList = mutableListOf<Student>()
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        seedStudents()




        val recyclerView: RecyclerView = findViewById(R.id.recyclerView_students)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        adapter = StudentAdapter(studentList, object : StudentAdapter.OnItemClickListener {
            override fun onStudentClick(student: Student) {
                // Handle row click
                val intent = Intent(this@StudentListActivity, StudentDetailsActivity::class.java)
                intent.putExtra("studentId", student.id)
                startActivity(intent)
            }

            override fun onCheckChanged(student: Student, isChecked: Boolean) {
                // Update check status
                student.isChecked = isChecked
            }
        })

        recyclerView.adapter = adapter

        val fab: FloatingActionButton = findViewById(R.id.fab_add_student)
        fab.setOnClickListener {
            // Navigate to Add Student screen
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
    }

    private fun seedStudents() {
        studentList.add(Student("1", "avi", false))
        studentList.add(Student("2", "liron", true))
        studentList.add(Student("3", "omer", false))
    }
}
