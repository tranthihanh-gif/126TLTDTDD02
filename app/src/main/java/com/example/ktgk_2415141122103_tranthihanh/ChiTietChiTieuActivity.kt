package com.example.ktgk_2415141122103_tranthihanh

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class ChiTietChiTieuActivity : AppCompatActivity() {
    private lateinit var tvTenKhoanChi_103: TextView
    private lateinit var tvSoTien_103: TextView
    private lateinit var tvGhiChu_103: TextView
    private lateinit var btnQuayLai_103: Button
    private lateinit var btnChinhSua_103: Button

    private var tenKhoanChi_103: String? = null
    private var soTien_103: String? = null
    private var ghiChu_103: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_chi_tieu)
        title = TIEU_DE_APP_103

        tvTenKhoanChi_103 = findViewById(R.id.tvTenKhoanChi_103)
        tvSoTien_103 = findViewById(R.id.tvSoTien_103)
        tvGhiChu_103 = findViewById(R.id.tvGhiChu_103)
        btnQuayLai_103 = findViewById(R.id.btnQuayLai_103)
        btnChinhSua_103 = findViewById(R.id.btnChinhSua_103)

        tenKhoanChi_103 = intent.getStringExtra(KEY_TEN_103)
        soTien_103 = intent.getStringExtra(KEY_SO_TIEN_103)
        ghiChu_103 = intent.getStringExtra(KEY_GHI_CHU_103)

        hienThiDuLieu_103()
        btnQuayLai_103.setOnClickListener { finish() }
    }

    private fun hienThiDuLieu_103() {
        tvTenKhoanChi_103.text = "Tên khoản chi: $tenKhoanChi_103"
        tvSoTien_103.text = "Số tiền: $soTien_103 VNĐ"
        tvGhiChu_103.text = "Ghi chú: $ghiChu_103"
    }
}