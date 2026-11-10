package com.example.lec7.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.lec7.databinding.FragmentStudentDetailBinding
import com.example.lec7.model.Student

class StudentDetailFragment : Fragment() {

    private var _binding: FragmentStudentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Đăng ký listener trong onCreate (trước khi View được tạo)
        setFragmentResultListener("STUDENT_KEY") { _, bundle ->
            val student = Student(
                id = bundle.getInt("ID", -1),
                name = bundle.getString("NAME", ""),
                className = bundle.getString("CLASS", ""),
                gpa = bundle.getDouble("GPA", 0.0)
            )

            binding.tvStudentDetail.text =
                "Mã số SV: ${student.id}\n" +
                        "Họ và tên: ${student.name}\n" +
                        "Lớp: ${student.className}\n" +
                        "Điểm GPA tích lũy: ${student.gpa}\n" +
                        "Xếp loại: ${student.rank()}"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Bắt buộc: giải phóng ViewBinding
    }
}