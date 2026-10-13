package com.example.ktgk_2415141122103_tranthihanh

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    private lateinit var edtTenKhoanChi_103: EditText
    private lateinit var edtSoTien_103: EditText
    private lateinit var edtGhiChu_103: EditText
    private lateinit var btnThemKhoanChi_103: Button
    private lateinit var btnXemChiTiet_103: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = TIEU_DE_APP_103

        anhXaView_103()
        btnThemKhoanChi_103.setOnClickListener { themKhoanChi_103() }
        btnXemChiTiet_103.setOnClickListener { xemChiTiet_103() }
    }

    private fun anhXaView_103() {
        edtTenKhoanChi_103 = findViewById(R.id.edtTenKhoanChi_103)
        edtSoTien_103 = findViewById(R.id.edtSoTien_103)
        edtGhiChu_103 = findViewById(R.id.edtGhiChu_103)
        btnThemKhoanChi_103 = findViewById(R.id.btnThemKhoanChi_103)
        btnXemChiTiet_103 = findViewById(R.id.btnXemChiTiet_103)
    }

    private fun kiemTraDuLieu_103(): Boolean {
        val ten_103 = edtTenKhoanChi_103.text.toString().trim()
        val soTien_103 = edtSoTien_103.text.toString().trim().toDoubleOrNull()

        if (ten_103.isEmpty()) {
            edtTenKhoanChi_103.error = "Tên khoản chi không được để trống"
            return false
        }
        if (soTien_103 == null || soTien_103 <= 0) {
            edtSoTien_103.error = "Số tiền phải là số lớn hơn 0"
            return false
        }
        return true
    }

    private fun themKhoanChi_103() {
        if (kiemTraDuLieu_103()) {
            Toast.makeText(this, "Đã thêm khoản chi: ${edtTenKhoanChi_103.text.toString().trim()}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun xemChiTiet_103() {
        val intent_103 = Intent(this, ChiTietChiTieuActivity::class.java)
        intent_103.putExtra(KEY_TEN_103, edtTenKhoanChi_103.text.toString())
        intent_103.putExtra(KEY_SO_TIEN_103, edtSoTien_103.text.toString())
        intent_103.putExtra(KEY_GHI_CHU_103, edtGhiChu_103.text.toString())
        startActivity(intent_103)
    }
}