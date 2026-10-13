package com.example.ktgk_2415141122103_tranthihanh
import java.io.Serializable

class KhoanChi(
    var maKhoanChi: Int,
    var tenKhoanChi: String,
    var soTien: Double,
    var ghiChu: String,
    var loaiChi: String = "Khác",
    var daThanhToan: Boolean
) : Serializable
{
fun layThongTin(): String {
    val trangThai = if (daThanhToan) "Đã thanh toán" else "Chưa thanh toán"
    return "Mã: $maKhoanChi | Tên: $tenKhoanChi | Số tiền: $soTien VNĐ | Ghi chú: $ghiChu | $trangThai"
}

fun laSoTienHopLe(): Boolean = soTien > 0
    fun dinhDangSoTien(): String =
    if (soTien % 1.0 == 0.0) soTien.toLong().toString() else soTien.toString()
}