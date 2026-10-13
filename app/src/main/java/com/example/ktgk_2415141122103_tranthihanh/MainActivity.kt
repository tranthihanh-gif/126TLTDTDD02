package com.example.ktgk_2415141122103_tranthihanh

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.CheckBox
import android.widget.ArrayAdapter
import android.widget.Spinner
class MainActivity : AppCompatActivity() {
    private lateinit var edtTenKhoanChi_103: EditText

    private var khoanChiHienTai_103: KhoanChi? = null
    private var maTuTang_103 = 1
    private lateinit var edtSoTien_103: EditText
    private lateinit var edtGhiChu_103: EditText
    private lateinit var btnThemKhoanChi_103: Button
    private lateinit var btnXemChiTiet_103: Button
    private lateinit var chkDaThanhToan_103: CheckBox
    private lateinit var spnLoaiChi_103: Spinner
    private val danhSachLoaiChi_103 = listOf("Ăn uống", "Đi lại", "Học tập", "Khác")

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
        chkDaThanhToan_103 = findViewById(R.id.chkDaThanhToan_103)
        spnLoaiChi_103 = findViewById(R.id.spnLoaiChi_103)
        spnLoaiChi_103.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            danhSachLoaiChi_103
        )
    }

    private fun kiemTraDuLieu_103(): Boolean {
        val ten_103 = edtTenKhoanChi_103.text.toString().trim()
        val soTien_103 = edtSoTien_103.text.toString().trim().toDoubleOrNull()

        if (ten_103.isEmpty()) {
            edtTenKhoanChi_103.error = "Tên khoản chi không được để trống"
            edtTenKhoanChi_103.requestFocus()
            Toast.makeText(this, "Vui lòng nhập tên khoản chi", Toast.LENGTH_SHORT).show()
            return false
        }
        if (soTien_103 == null || soTien_103 <= 0) {
            edtSoTien_103.error = "Số tiền phải là số lớn hơn 0"
            edtSoTien_103.requestFocus()
            Toast.makeText(this, "Số tiền không hợp lệ", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    private fun themKhoanChi_103() {
        if (!kiemTraDuLieu_103()) return

        khoanChiHienTai_103 = KhoanChi(
            maKhoanChi = maTuTang_103++,
            tenKhoanChi = edtTenKhoanChi_103.text.toString().trim(),
            soTien = edtSoTien_103.text.toString().trim().toDouble(),
            ghiChu = edtGhiChu_103.text.toString(),
            daThanhToan = chkDaThanhToan_103.isChecked,
            loaiChi = spnLoaiChi_103.selectedItem.toString()
        )
        Toast.makeText(this, khoanChiHienTai_103!!.layThongTin(), Toast.LENGTH_LONG).show()
    }

    private fun xemChiTiet_103() {
        val khoanChi_103 = khoanChiHienTai_103
        if (khoanChi_103 == null) {
            Toast.makeText(this, "Hãy thêm khoản chi trước", Toast.LENGTH_SHORT).show()
            return
        }
        val intent_103 = Intent(this, ChiTietChiTieuActivity::class.java)
        intent_103.putExtra(KEY_KHOAN_CHI_103, khoanChi_103)
        startActivity(intent_103)
    }
}