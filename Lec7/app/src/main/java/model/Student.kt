package com.example.lec7.model

data class Student(
    val id: Int,
    val name: String,
    val className: String,
    val gpa: Double
) {
    // Phần mở rộng: xếp loại học lực theo GPA
    fun rank(): String = when {
        gpa >= 3.6 -> "Xuất sắc"
        gpa >= 3.2 -> "Giỏi"
        gpa >= 2.5 -> "Khá"
        else -> "Trung bình"
    }
}