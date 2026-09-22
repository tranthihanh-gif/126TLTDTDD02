package com.example.viewbinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            tvTitle.apply {
                text = "ỨNG DỤNG VIEW BINDING"
                textSize = 24f
            }

            edtName.also {
                it.hint = "Nhập họ và tên"
            }

            edtMssv.run {
                hint = "Nhập MSSV"
            }

            btnDisplay.setOnClickListener {

                val name = edtName.text.toString().let {
                    it.trim()
                }

                val mssv = edtMssv.text.toString().let {
                    it.trim()
                }

                tvResult.text = "Xin chào: $name\nMSSV: $mssv"
            }
        }
    }
}