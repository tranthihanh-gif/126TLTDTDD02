package com.example.lec3_miniproj

import java.io.Serializable

data class Student(
    val id: String,
    val name: String,
    val className: String,
    val email: String,
    val gpa: Double
) : Serializable