package com.example.ktgk_2415141122103_tranthihanh

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat

class ChiTietChiTieuActivity : AppCompatActivity() {
    private lateinit var tvTenKhoanChi_103: TextView
    private lateinit var tvSoTien_103: TextView
    private lateinit var tvGhiChu_103: TextView
    private lateinit var btnQuayLai_103: Button
    private lateinit var btnChinhSua_103: Button

    private var khoanChi_103: KhoanChi? = null

    private val launcherChinhSua_103 =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { ketQua_103 ->
            if (ketQua_103.resultCode == RESULT_OK) {
                val moi_103 = ketQua_103.data?.let {
                    IntentCompat.getSerializableExtra(it, KEY_KHOAN_CHI_103, KhoanChi::class.java)
                }
                if (moi_103 != null) {
                    khoanChi_103 = moi_103
                    hienThiDuLieu_103()
                }
            }
            // RESULT_CANCELED: giữ nguyên dữ liệu cũ
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_chi_tieu)
        title = TIEU_DE_APP_103

        tvTenKhoanChi_103 = findViewById(R.id.tvTenKhoanChi_103)
        tvSoTien_103 = findViewById(R.id.tvSoTien_103)
        tvGhiChu_103 = findViewById(R.id.tvGhiChu_103)
        btnQuayLai_103 = findViewById(R.id.btnQuayLai_103)
        btnChinhSua_103 = findViewById(R.id.btnChinhSua_103)

        khoanChi_103 = IntentCompat.getSerializableExtra(intent, KEY_KHOAN_CHI_103, KhoanChi::class.java)

        hienThiDuLieu_103()

        btnQuayLai_103.setOnClickListener { finish() }
        btnChinhSua_103.setOnClickListener {
            val kc_103 = khoanChi_103
            if (kc_103 == null) {
                Toast.makeText(this, "Không có dữ liệu để chỉnh sửa", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent_103 = Intent(this, ChinhSuaChiTieuActivity::class.java)
            intent_103.putExtra(KEY_KHOAN_CHI_103, kc_103)
            launcherChinhSua_103.launch(intent_103)
        }
    }

    private fun hienThiDuLieu_103() {
        val kc_103 = khoanChi_103
        if (kc_103 == null || kc_103.tenKhoanChi.isBlank()) {
            tvTenKhoanChi_103.text = "Không nhận được tên khoản chi"
            Toast.makeText(this, "Thiếu tên khoản chi", Toast.LENGTH_SHORT).show()
        } else {
            tvTenKhoanChi_103.text = "Tên khoản chi: ${kc_103.tenKhoanChi}"
        }

        if (kc_103 == null || !kc_103.laSoTienHopLe()) {
            tvSoTien_103.text = "Số tiền không hợp lệ"
            Toast.makeText(this, "Số tiền không hợp lệ", Toast.LENGTH_SHORT).show()
        } else {
            tvSoTien_103.text = "Số tiền: ${kc_103.soTien} VNĐ"
        }

        tvGhiChu_103.text = "Ghi chú: ${kc_103?.ghiChu ?: ""}"
    }
}