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

    companion object {
        const val ADD_STUDENT_REQUEST_CODE = 1
    }

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
                student.isChecked = isChecked
            }
        })

        recyclerView.adapter = adapter

        val fab: FloatingActionButton = findViewById(R.id.fab_add_student)
        fab.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivityForResult(intent, ADD_STUDENT_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_STUDENT_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.let {
                val newStudent = it.getParcelableExtra<Student>("newStudent")
                newStudent?.let { student ->
                    studentList.add(student)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun seedStudents() {
        studentList.add(Student("1", "Avi", "123456", "Tel Aviv",  false))
        studentList.add(Student("2", "Liron", "654321", "Haifa",  true))
    }
}
