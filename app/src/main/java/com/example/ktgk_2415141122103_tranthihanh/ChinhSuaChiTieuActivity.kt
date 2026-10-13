package com.example.ktgk_2415141122103_tranthihanh

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Toast

class ChinhSuaChiTieuActivity : AppCompatActivity() {
    private lateinit var edtTenKhoanChi_103: EditText
    private lateinit var edtSoTien_103: EditText
    private lateinit var edtGhiChu_103: EditText
    private lateinit var btnLuu_103: Button
    private lateinit var btnHuy_103: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chinh_sua_chi_tieu)
        title = TIEU_DE_APP_103

        edtTenKhoanChi_103 = findViewById(R.id.edtTenKhoanChi_103)
        edtSoTien_103 = findViewById(R.id.edtSoTien_103)
        edtGhiChu_103 = findViewById(R.id.edtGhiChu_103)
        btnLuu_103 = findViewById(R.id.btnLuu_103)
        btnHuy_103 = findViewById(R.id.btnHuy_103)

        edtTenKhoanChi_103.setText(intent.getStringExtra(KEY_TEN_103))
        edtSoTien_103.setText(intent.getStringExtra(KEY_SO_TIEN_103))
        edtGhiChu_103.setText(intent.getStringExtra(KEY_GHI_CHU_103))
        btnLuu_103.setOnClickListener {
            val ten_103 = edtTenKhoanChi_103.text.toString().trim()
            val soTien_103 = edtSoTien_103.text.toString().trim().toDoubleOrNull()

            if (ten_103.isEmpty()) {
                edtTenKhoanChi_103.error = "Tên khoản chi không được để trống"
                return@setOnClickListener
            }
            if (soTien_103 == null || soTien_103 <= 0) {
                edtSoTien_103.error = "Số tiền phải là số lớn hơn 0"
                return@setOnClickListener
            }

            val ketQua_103 = Intent()
            ketQua_103.putExtra(KEY_TEN_103, ten_103)
            ketQua_103.putExtra(KEY_SO_TIEN_103, edtSoTien_103.text.toString().trim())
            ketQua_103.putExtra(KEY_GHI_CHU_103, edtGhiChu_103.text.toString())
            setResult(RESULT_OK, ketQua_103)
            finish()
        }

        btnHuy_103.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

}