package com.example.formvalidation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.formvalidation.databinding.ActivityMainBinding
import android.os.SystemClock
import android.view.View
import com.example.formvalidation.validator.FormValidator
import com.google.android.material.snackbar.Snackbar
import android.content.Intent
import com.example.formvalidation.model.User
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var lastClickTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRealtimeValidation()
        binding.btnCancel.setOnClickListener { resetForm() }
        binding.btnRegister.setOnClickListener { onRegisterClick() }
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

    private fun validateForm(): Boolean {
        var firstErrorView: View? = null

        val name = binding.etFullName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val pass = binding.etPassword.text.toString()

        if (!FormValidator.isValidName(name)) {
            binding.tilFullName.error = getString(R.string.err_invalid_name)
            if (firstErrorView == null) firstErrorView = binding.etFullName
        }
        if (!FormValidator.isValidEmail(email)) {
            binding.tilEmail.error = getString(R.string.err_invalid_email)
            if (firstErrorView == null) firstErrorView = binding.etEmail
        }
        if (!FormValidator.isValidPhone(phone)) {
            binding.tilPhone.error = getString(R.string.err_invalid_phone)
            if (firstErrorView == null) firstErrorView = binding.etPhone
        }
        if (!FormValidator.isValidPassword(pass)) {
            binding.tilPassword.error = getString(R.string.err_short_pass)
            if (firstErrorView == null) firstErrorView = binding.etPassword
        }

        firstErrorView?.requestFocus()   // nhảy con trỏ vào ô lỗi đầu tiên
        return firstErrorView == null
    }

    private fun onRegisterClick() {
        // Chống click đúp (slide 5)
        val now = SystemClock.elapsedRealtime()
        if (now - lastClickTime < 1000L) return
        lastClickTime = now

        if (!validateForm()) {
            Snackbar.make(binding.root, getString(R.string.msg_register_fail), Snackbar.LENGTH_LONG)
                .show()
            return
        }
        // Thành công: bước 8 sẽ chuyển sang màn hình trang chủ
        val user = User(
            fullName = binding.etFullName.text.toString().trim(),
            email = binding.etEmail.text.toString().trim(),
            phone = binding.etPhone.text.toString().trim(),
            password = binding.etPassword.text.toString()
        )
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra(HomeActivity.EXTRA_USER, user)
        startActivity(intent)
    }
}