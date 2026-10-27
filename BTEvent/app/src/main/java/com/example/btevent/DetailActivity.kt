package com.example.btevent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.btevent.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_PHONE = "extra_phone"
        const val EXTRA_GENDER = "extra_gender"
        const val EXTRA_SOCIALS = "extra_socials"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val socials = intent.getStringArrayListExtra(EXTRA_SOCIALS).orEmpty()

        binding.tvName.text = "Họ tên: ${intent.getStringExtra(EXTRA_NAME)}"
        binding.tvEmail.text = "Email: ${intent.getStringExtra(EXTRA_EMAIL)}"
        binding.tvPhone.text = "Số điện thoại: ${intent.getStringExtra(EXTRA_PHONE)}"
        binding.tvGender.text = "Giới tính: ${intent.getStringExtra(EXTRA_GENDER)}"
        binding.tvSocial.text = "Mạng xã hội: " +
                if (socials.isEmpty()) "Không chọn" else socials.joinToString(", ")

        binding.btnBack.setOnClickListener { finish() }
    }
}