package com.example.btevent

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.btevent.databinding.ActivityMainBinding
import android.content.Intent
import android.os.SystemClock
import android.util.Patterns
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentToast: Toast? = null
    private var lastClickTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTextEvents()
        setupGenderEvent()
        setupSocialEvents()
        setupRegisterButton()
    }


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
    private fun setupSocialEvents() {
        val checkBoxes = listOf(
            binding.cbFacebook, binding.cbInstagram, binding.cbTiktok, binding.cbZalo
        )
        checkBoxes.forEach { cb ->
            cb.setOnCheckedChangeListener { _, isChecked ->
                val state = if (isChecked) "đã chọn" else "đã bỏ chọn"
                showToast("${cb.text} $state")
            }
        }

    }

    private fun validateForm(): Boolean {
        var isValid = true
        val name = binding.etFullName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()

        if (name.length < 3) {
            binding.tilFullName.error = "Họ tên tối thiểu 3 ký tự"
            isValid = false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Email không đúng định dạng"
            isValid = false
        }
        if (!Regex("^(03|05|07|08|09)\\d{8}$").matches(phone)) {
            binding.tilPhone.error = "Số điện thoại không hợp lệ (10 số)"
            isValid = false
        }
        if (binding.rgGender.checkedRadioButtonId == -1) {
            showToast("Vui lòng chọn giới tính")
            isValid = false
        }
        return isValid
    }

    private fun setupRegisterButton() {
        binding.btnRegister.setOnClickListener {
            // Chống click đúp (slide 5)
            val now = SystemClock.elapsedRealtime()
            if (now - lastClickTime < 1000L) return@setOnClickListener
            lastClickTime = now

            if (!validateForm()) return@setOnClickListener

            val socials = ArrayList<String>()
            if (binding.cbFacebook.isChecked) socials.add(binding.cbFacebook.text.toString())
            if (binding.cbInstagram.isChecked) socials.add(binding.cbInstagram.text.toString())
            if (binding.cbTiktok.isChecked) socials.add(binding.cbTiktok.text.toString())
            if (binding.cbZalo.isChecked) socials.add(binding.cbZalo.text.toString())

            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_NAME, binding.etFullName.text.toString().trim())
                putExtra(DetailActivity.EXTRA_EMAIL, binding.etEmail.text.toString().trim())
                putExtra(DetailActivity.EXTRA_PHONE, binding.etPhone.text.toString().trim())
                putExtra(
                    DetailActivity.EXTRA_GENDER,
                    genderFromId(binding.rgGender.checkedRadioButtonId)
                )
                putStringArrayListExtra(DetailActivity.EXTRA_SOCIALS, socials)
            }
            startActivity(intent)
        }
    }
}

