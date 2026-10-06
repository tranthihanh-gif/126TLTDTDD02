package com.example.lec3_miniproj

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lec3_miniproj.databinding.ActivityEditProfileBinding


class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private var originalStudent: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        originalStudent = intent.getSerializableExtra("STUDENT") as? Student
        originalStudent?.let {
            binding.edtName.setText(it.name)
            binding.edtClass.setText(it.className)
            binding.edtGpa.setText(it.gpa.toString())
        }

        binding.btnSave.setOnClickListener {
            val name = binding.edtName.text.toString().trim()
            val className = binding.edtClass.text.toString().trim()
            val gpa = binding.edtGpa.text.toString().toDoubleOrNull()

            if (name.isEmpty() || gpa == null || gpa !in 0.0..4.0) {
                toast("Vui lòng nhập tên và GPA hợp lệ (0.0 - 4.0)!")
                return@setOnClickListener
            }

            val updated = originalStudent?.copy(
                name = name,
                className = className,
                gpa = gpa
            ) ?: return@setOnClickListener

            val resIntent = Intent().apply { putExtra("UPDATED", updated) }
            setResult(Activity.RESULT_OK, resIntent)   // 1. setResult TRƯỚC
            finish()                                   // 2. finish SAU
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
}