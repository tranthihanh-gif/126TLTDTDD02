package com.example.bitp2_lab3

import java.io.Serializable

data class Student(
    val id: String,
    var name: String,
    var gpa: Double
) : Serializable