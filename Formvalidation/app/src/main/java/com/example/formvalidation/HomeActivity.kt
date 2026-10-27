package com.example.formvalidation

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.formvalidation.databinding.ActivityHomeBinding
import com.example.formvalidation.model.User

class HomeActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = getUserExtra()
        if (user == null) {
            finish()
            return
        }

        binding.tvWelcome.text = getString(R.string.home_welcome, user.fullName)
        binding.tvName.text = "Họ tên: ${user.fullName}"
        binding.tvEmail.text = "Email: ${user.email}"
        binding.tvPhone.text = "Số điện thoại: ${user.phone}"

        binding.btnLogout.setOnClickListener { finish() }
    }

    private fun getUserExtra(): User? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(EXTRA_USER, User::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra(EXTRA_USER) as? User
        }
}