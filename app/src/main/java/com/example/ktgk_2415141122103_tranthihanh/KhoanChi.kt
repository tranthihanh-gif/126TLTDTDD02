package com.example.ktgk_2415141122103_tranthihanh
import java.io.Serializable

class KhoanChi(
    var maKhoanChi: Int,
    var tenKhoanChi: String,
    var soTien: Double,
    var ghiChu: String,
    var daThanhToan: Boolean
) : Serializable