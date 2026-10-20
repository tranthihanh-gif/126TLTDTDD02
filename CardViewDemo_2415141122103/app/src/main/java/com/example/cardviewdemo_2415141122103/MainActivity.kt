
package com.example.cardviewdemo_2415141122103

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cardviewdemo_2415141122103.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isCustomized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val student = Student(
            name = "Trần Thị Hạnh",
            studentId = "2415141122103",
            className = "TLTDD02",
            gpa = 3.85
        )


        binding.tvStudentName.text = "Họ và tên: ${student.name}"
        binding.tvStudentInfo.text = "MSSV: ${student.studentId} • Lớp: ${student.className}"
        binding.tvGpa.text = "${student.gpa} / 4.0"
        binding.tvResult.text = classify(student.gpa)


        binding.btnCustomize.setOnClickListener {
            val density = resources.displayMetrics.density
            if (!isCustomized) {
                binding.cardStudent.setCardBackgroundColor(getColor(R.color.blue))
                binding.cardStudent.radius = 32 * density
                binding.cardStudent.cardElevation = 16 * density
            } else {
                binding.cardStudent.setCardBackgroundColor(getColor(R.color.card_dark))
                binding.cardStudent.radius = 24 * density
                binding.cardStudent.cardElevation = 8 * density
            }
            isCustomized = !isCustomized
        }
    }

    private fun classify(gpa: Double): String = when {
        gpa >= 3.6 -> "XUẤT SẮC"
        gpa >= 3.2 -> "GIỎI"
        gpa >= 2.5 -> "KHÁ"
        else -> "TRUNG BÌNH"
    }
}