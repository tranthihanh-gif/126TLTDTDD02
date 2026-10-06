package com.example.lec3_bt4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {

    private lateinit var currentStudent: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val edtName = findViewById<EditText>(R.id.edtName)
        val edtGpa = findViewById<EditText>(R.id.edtGpa)
        val btnSave = findViewById<Button>(R.id.btnSave)

        // Nhận sinh viên từ MainActivity và điền sẵn vào ô nhập
        currentStudent = intent.getSerializableExtra("STUDENT") as Student
        edtName.setText(currentStudent.name)
        edtGpa.setText(currentStudent.gpa.toString())

        btnSave.setOnClickListener {
            val newName = edtName.text.toString().trim()
            val newGpa = edtGpa.text.toString().toDoubleOrNull()

            // Kiểm tra dữ liệu hợp lệ
            if (newName.isEmpty() || newGpa == null || newGpa !in 0.0..4.0) {
                Toast.makeText(this, "Dữ liệu nhập chưa hợp lệ!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Tạo đối tượng sinh viên đã chỉnh sửa
            val updatedStudent = currentStudent.copy(name = newName, gpa = newGpa)

            // 1. Tạo Intent chứa dữ liệu phản hồi
            val resultIntent = Intent().apply {
                putExtra("UPDATED_STUDENT", updatedStudent)
            }

            // 2. Gán kết quả thành công RESULT_OK kèm Intent
            setResult(Activity.RESULT_OK, resultIntent)

            // 3. Đóng màn hình hiện tại để quay về màn hình trước
            finish()
        }
    }
}