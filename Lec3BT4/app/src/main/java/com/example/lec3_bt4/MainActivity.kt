package com.example.lec3_bt4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // 1. Khai báo biến Launcher ở cấp độ thuộc tính lớp
    private lateinit var editProfileLauncher: ActivityResultLauncher<Intent>

    // Sinh viên đang hiển thị (dữ liệu mẫu)
    private var currentStudent = Student("2415141122103", "Trần Hạnh", 3.2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hiển thị dữ liệu ban đầu
        bindStudentData(currentStudent)

        // 2. Đăng ký Launcher NGAY TRONG onCreate()
        editProfileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            // Callback tự động chạy khi Activity con trả kết quả về
            if (result.resultCode == Activity.RESULT_OK) {
                val updatedStudent =
                    result.data?.getSerializableExtra("UPDATED_STUDENT") as? Student
                updatedStudent?.let {
                    currentStudent = it
                    bindStudentData(currentStudent)
                    Toast.makeText(this, "Đã lưu thông tin mới của ${it.name}!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // 3. Bấm "Sửa hồ sơ" thì launch
        findViewById<Button>(R.id.btnEditProfile).setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java).apply {
                putExtra("STUDENT", currentStudent)
            }
            editProfileLauncher.launch(intent)
        }
    }

    private fun bindStudentData(student: Student) {
        findViewById<TextView>(R.id.tvId).text = "MSSV: ${student.id}"
        findViewById<TextView>(R.id.tvName).text = "Họ tên: ${student.name}"
        findViewById<TextView>(R.id.tvGpa).text = "GPA: ${student.gpa}"
    }
}