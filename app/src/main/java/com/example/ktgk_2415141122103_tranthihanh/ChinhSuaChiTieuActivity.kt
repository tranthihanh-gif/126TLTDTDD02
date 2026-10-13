package com.example.ktgk_2415141122103_tranthihanh

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import android.widget.Toast
class ChinhSuaChiTieuActivity : AppCompatActivity() {
    private lateinit var edtTenKhoanChi_103: EditText
    private lateinit var edtSoTien_103: EditText
    private lateinit var edtGhiChu_103: EditText
    private lateinit var btnLuu_103: Button
    private lateinit var btnHuy_103: Button

    private var khoanChi_103: KhoanChi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chinh_sua_chi_tieu)
        title = TIEU_DE_APP_103

        edtTenKhoanChi_103 = findViewById(R.id.edtTenKhoanChi_103)
        edtSoTien_103 = findViewById(R.id.edtSoTien_103)
        edtGhiChu_103 = findViewById(R.id.edtGhiChu_103)
        btnLuu_103 = findViewById(R.id.btnLuu_103)
        btnHuy_103 = findViewById(R.id.btnHuy_103)

        khoanChi_103 = IntentCompat.getSerializableExtra(intent, KEY_KHOAN_CHI_103, KhoanChi::class.java)

        // Hiển thị sẵn dữ liệu hiện tại
        khoanChi_103?.let {
            edtTenKhoanChi_103.setText(it.tenKhoanChi)
            edtSoTien_103.setText(it.soTien.toString())
            edtGhiChu_103.setText(it.ghiChu)
        }

        btnLuu_103.setOnClickListener { luuChinhSua_103() }
        btnHuy_103.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    private fun luuChinhSua_103() {
        val kc_103 = khoanChi_103 ?: return
        val ten_103 = edtTenKhoanChi_103.text.toString().trim()
        val soTien_103 = edtSoTien_103.text.toString().trim().toDoubleOrNull()

        if (ten_103.isEmpty()) {
            edtTenKhoanChi_103.error = "Tên khoản chi không được để trống"
            edtTenKhoanChi_103.requestFocus()
            Toast.makeText(this, "Vui lòng nhập tên khoản chi", Toast.LENGTH_SHORT).show()
            return
        }
        if (soTien_103 == null || soTien_103 <= 0) {
            edtSoTien_103.error = "Số tiền phải là số lớn hơn 0"
            edtSoTien_103.requestFocus()
            Toast.makeText(this, "Số tiền không hợp lệ", Toast.LENGTH_SHORT).show()
            return
        }

        // Cập nhật bản sao nhận qua Intent rồi trả về
        kc_103.tenKhoanChi = ten_103
        kc_103.soTien = soTien_103
        kc_103.ghiChu = edtGhiChu_103.text.toString()

        val ketQua_103 = Intent()
        ketQua_103.putExtra(KEY_KHOAN_CHI_103, kc_103)
        setResult(RESULT_OK, ketQua_103)
        finish()
    }
}