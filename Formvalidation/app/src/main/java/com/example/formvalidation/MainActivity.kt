package com.example.formvalidation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.formvalidation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRealtimeValidation()
        binding.btnCancel.setOnClickListener { resetForm() }
    }

    // Người dùng gõ phím mới thì xoá lỗi đỏ ngay (slide 7)
    private fun setupRealtimeValidation() {
        binding.etFullName.doAfterTextChanged { binding.tilFullName.error = null }
        binding.etEmail.doAfterTextChanged { binding.tilEmail.error = null }
        binding.etPhone.doAfterTextChanged { binding.tilPhone.error = null }
        binding.etPassword.doAfterTextChanged { binding.tilPassword.error = null }
    }

    // Nút Huỷ: xoá trắng các trường để nhập lại
    private fun resetForm() {
        binding.etFullName.text?.clear()
        binding.etEmail.text?.clear()
        binding.etPhone.text?.clear()
        binding.etPassword.text?.clear()

        binding.tilFullName.error = null
        binding.tilEmail.error = null
        binding.tilPhone.error = null
        binding.tilPassword.error = null

        binding.etFullName.requestFocus()
    }
}