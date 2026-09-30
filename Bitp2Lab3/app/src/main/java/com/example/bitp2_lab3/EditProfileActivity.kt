package com.example.bitp2_lab3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bitp2_lab3.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. String trả về nullable -> dùng ?: để có giá trị mặc định
        val id = intent.getStringExtra("EXTRA_STUDENT_ID") ?: "Chưa có"
        val name = intent.getStringExtra("EXTRA_STUDENT_NAME") ?: ""

        // 2. Số thực: bắt buộc có defaultValue
        val gpa = intent.getDoubleExtra("EXTRA_STUDENT_GPA", 0.0)

        // 3. Serializable: ép kiểu an toàn 'as?'
        val student = intent.getSerializableExtra("EXTRA_STUDENT_OBJECT") as? Student

        // Nạp dữ liệu lên giao diện
        binding.tvId.text = "MSSV: $id"
        binding.edtName.setText(name)
        binding.edtGpa.setText(gpa.toString())

        // Nút Lưu: trả dữ liệu mới về MainActivity
        binding.btnSave.setOnClickListener {
            val newName = binding.edtName.text.toString().trim()
            val newGpa = binding.edtGpa.text.toString().toDoubleOrNull()

            if (newName.isEmpty() || newGpa == null) {
                Toast.makeText(this, "Vui lòng nhập đúng tên và GPA", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val updated = student?.copy(name = newName, gpa = newGpa)
                ?: Student(id, newName, newGpa)

            val resultIntent = Intent().putExtra("EXTRA_STUDENT_OBJECT", updated)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        // Nút Quay lại: không lưu
        binding.btnBack.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}