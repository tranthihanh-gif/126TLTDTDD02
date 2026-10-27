package com.example.btevent

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.btevent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTextEvents()
    }

    // Huỷ toast cũ trước khi hiện toast mới để không bị xếp hàng khi gõ nhanh
    private fun showToast(message: String) {
        currentToast?.cancel()
        currentToast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        currentToast?.show()
    }
    private fun genderFromId(checkedId: Int): String = when (checkedId) {
        R.id.rbMale -> "Nam"
        R.id.rbFemale -> "Nữ"
        R.id.rbOther -> "Khác"
        else -> ""
    }

    private fun setupGenderEvent() {
        binding.rgGender.setOnCheckedChangeListener { _, checkedId ->
            showToast("Giới tính: ${genderFromId(checkedId)}")
        }
    }
    private fun setupTextEvents() {
        binding.etFullName.doAfterTextChanged { text ->
            binding.tilFullName.error = null
            showToast("Họ tên: $text")
        }
        binding.etEmail.doAfterTextChanged { text ->
            binding.tilEmail.error = null
            showToast("Email: $text")
        }
        binding.etPhone.doAfterTextChanged { text ->
            binding.tilPhone.error = null
            showToast("Số điện thoại: $text")
        }
    }
}