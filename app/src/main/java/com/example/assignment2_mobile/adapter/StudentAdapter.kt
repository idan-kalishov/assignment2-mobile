package com.example.studentsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2_mobile.R

class StudentAdapter(
    private val studentList: List<Student>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    interface OnItemClickListener {
        fun onStudentClick(student: Student)
        fun onCheckChanged(student: Student, isChecked: Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.bind(student)
    }

    override fun getItemCount(): Int = studentList.size

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewStudent: ImageView = itemView.findViewById(R.id.imageViewStudent)
        private val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        private val textViewId: TextView = itemView.findViewById(R.id.textViewId)
        private val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)

        fun bind(student: Student) {
            imageViewStudent.setImageResource(R.drawable.student_placeholder)
            textViewName.text = student.name
            textViewId.text = student.id
            checkBox.isChecked = student.isChecked

            itemView.setOnClickListener {
                onItemClickListener.onStudentClick(student)
            }

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                student.isChecked = isChecked
                onItemClickListener.onCheckChanged(student, isChecked)
            }
        }
    }
}
