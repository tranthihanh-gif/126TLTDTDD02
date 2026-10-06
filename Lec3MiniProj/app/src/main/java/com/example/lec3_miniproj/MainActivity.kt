package com.example.lec3_miniproj

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.lec3_miniproj.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "TAG_LIFECYCLE"
    }

    private lateinit var binding: ActivityMainBinding


    private var student = Student("22505120005", "Tran Thi Hanh", "126TLTTD02", "2415141122103@sv.ute.udn.vn", 3.6)


    private val editLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { res ->
        if (res.resultCode == Activity.RESULT_OK) {
            (res.data?.getSerializableExtra("UPDATED") as? Student)?.let {
                student = it
                bindData(student)
                toast("Đã lưu thành công!")
            }
        }
    }


    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            binding.imgAvatar.setImageURI(it)
            toast("Đã đổi avatar!")
        }
    }


    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { ok ->
        toast(if (ok) "Đã cấp quyền Camera!" else "Bị từ chối quyền!")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Activity đang được khởi tạo và nạp layout")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindData(student)


        binding.btnEditProfile.setOnClickListener {
            val i = Intent(this, EditProfileActivity::class.java).apply {
                putExtra("STUDENT", student)
            }
            editLauncher.launch(i)
        }


        binding.btnChangeAvatar.setOnClickListener {
            galleryLauncher.launch("image/*")
        }

        binding.btnCallHotline.setOnClickListener {
            callAdvisor("0905123456")
        }

        binding.btnRequestCamera.setOnClickListener {
            cameraLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Activity đã hiển thị trên màn hình (chưa nhận tương tác)")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Activity ở trạng thái đỉnh stack, sẵn sàng tương tác!")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Activity bị che khuất một phần (mở dialog / chuẩn bị rời đi)")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: Activity bị ẩn hoàn toàn (bấm Home / mở Activity khác)")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: Người dùng mở lại Activity từ trạng thái Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Activity bị hủy hoàn toàn khỏi bộ nhớ RAM!")
    }

    private fun bindData(s: Student) {
        binding.tvName.text = s.name
        binding.tvDetails.text = "MSSV: ${s.id}\nLớp: ${s.className}\nEmail: ${s.email}"
        binding.tvGpaBadge.text = "GPA: ${s.gpa} (${classify(s.gpa)})"
    }

    // Xếp loại theo thang 4
    private fun classify(gpa: Double): String = when {
        gpa >= 3.6 -> "Xuất sắc"
        gpa >= 3.2 -> "Giỏi"
        gpa >= 2.5 -> "Khá"
        gpa >= 2.0 -> "Trung bình"
        else -> "Yếu"
    }

    private fun callAdvisor(phoneNumber: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        try {
            startActivity(dialIntent)
        } catch (e: ActivityNotFoundException) {
            toast("Không tìm thấy ứng dụng phù hợp để thực hiện tác vụ!")
        }
    }
}