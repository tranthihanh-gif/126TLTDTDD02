package com.example.ktgk_2415141122103_tranthihanh

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

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

        // Hiển thị sẵn dữ liệu hiện tại
        edtTenKhoanChi_103.setText(intent.getStringExtra(KEY_TEN_103))
        edtSoTien_103.setText(intent.getStringExtra(KEY_SO_TIEN_103))
        edtGhiChu_103.setText(intent.getStringExtra(KEY_GHI_CHU_103))
    }
}