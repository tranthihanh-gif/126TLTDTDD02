package com.example.viewbinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viewbinding.databinding.ActivityMainBinding
import com.example.viewbinding.model.Student
import com.example.viewbinding.utils.toAcademicRanking

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Khởi tạo dữ liệu sinh viên ban đầu
    private var currentStudent = Student(
        id = "2415141122103",
        name = "Tran Thi Hanh",
        className = "24sk1",
        email = "2415141122103@ute.udn.vn",
        gpa = 3.8
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Gán dữ liệu ban đầu lên các Views
        bindStudentData(currentStudent)
    }

    // Hàm hiển thị thông tin sinh viên lên giao diện bằng ViewBinding
    private fun bindStudentData(student: Student) {
        with(binding) {
            tvTitle.text = student.name
            // Sử dụng các view ID thực tế mà bạn đã đặt trong layout XML
            edtName.setText(student.name)
            edtMssv.setText(student.id)

            val ranking = student.gpa.toAcademicRanking()
            tvResult.text = "MSSV: ${student.id} • Lớp: ${student.className}\n${student.gpa} GPA ($ranking)"

        }
    }
}