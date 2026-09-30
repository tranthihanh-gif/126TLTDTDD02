package com.example.bitp2_lab3

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.bitp2_lab3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Dữ liệu ban đầu (bạn đổi thành tên/MSSV của mình)
    private var student = Student("2415141122103", "Trần Thị Hạnh", 3.2)

    // Nhận kết quả trả về từ EditProfileActivity
    private val editLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val updated = result.data?.getSerializableExtra("EXTRA_STUDENT_OBJECT") as? Student
            if (updated != null) {
                student = updated
                showStudent()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showStudent()

        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra("EXTRA_STUDENT_ID", student.id)
            intent.putExtra("EXTRA_STUDENT_NAME", student.name)
            intent.putExtra("EXTRA_STUDENT_GPA", student.gpa)
            intent.putExtra("EXTRA_STUDENT_OBJECT", student)
            editLauncher.launch(intent)
        }
    }

    private fun showStudent() {
        binding.tvId.text = "MSSV: ${student.id}"
        binding.tvName.text = "Họ tên: ${student.name}"
        binding.tvGpa.text = "GPA: ${student.gpa}"
    }
}