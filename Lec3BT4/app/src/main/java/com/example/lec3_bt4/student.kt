package com.example.lec3_bt4
import java.io.Serializable

data class Student(
    val id: String,
    val name: String,
    val gpa: Double
) : Serializable