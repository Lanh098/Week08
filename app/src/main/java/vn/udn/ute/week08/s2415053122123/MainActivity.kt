package vn.udn.ute.week08.s2415053122123

import android.os.Bundle
import vn.udn.ute.week08.s2415053122123.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val D = 5.5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnKiemTra.setOnClickListener()
        {
            xuLyKiemTra()
        }
        binding.btnXoa.setOnClickListener()
        {
            lamSachDuLieu()
        }
    }
        private fun xuLyKiemTra() {
            xoaLoiCu()
            val ten = binding.edtTenHocPhan.text.toString().trim()
            val chuoiQT = binding.edtDiemQT.text.toString().trim()
            val chuoiCK = binding.edtDiemCK.text.toString().trim()
            if (kiemTraDauVao(ten, chuoiQT, chuoiCK)){
                val qt = chuoiQT.toDouble()
                val ck = chuoiCK.toDouble()
                val t = tinhT(qt, ck)
                val phanLoai = phanLoai(t,ck)
                hienThiKetQua(t, phanLoai)
            }
        }

    private fun kiemTraDauVao(ten: String, chuoiQT: String, chuoiCK: String): Boolean {
            var hopLe = true
            if (ten.isEmpty() || ten.length !in 1..40) {
                binding.tilTenHocPhan.error = "Tên học phần không hợp lệ"
                hopLe = false
        }
            val qt = chuoiQT.toDoubleOrNull()
            if (qt == null || qt < 0 || qt > 10) {
                binding.tilDiemQT.error = "Điểm quá trình không hợp lệ"
                hopLe = false
            }
            val ck = chuoiCK.toDoubleOrNull()
            if (ck == null || ck < 0 || ck > 10) {
                binding.tilDiemCK.error = "Điểm cuối kì không hợp lệ"
                hopLe = false
            }
            return hopLe
    }
        private fun tinhT(qt: Double, ck: Double): Double {
            return qt * 0.4 + ck * 0.6
        }
        private fun phanLoai(t: Double,ck: Double ) : String{
            if (t>=D && 2<=ck && ck>=3.0)
                return "Không đạt"
            return if (t>=D && ck>=3.0) "Đạt" else "Không đạt"
        }
        private fun hienThiKetQua(t: Double, phanLoai: String) {
            val tFormat = String.format("%.2f", t)
            binding.tvKetQua.text =
                "Tên học phần: ${binding.edtTenHocPhan.text}\n" +
                        "Điểm quá trình: ${binding.edtDiemQT.text}\n" +
                        "Điểm cuối kì: ${binding.edtDiemCK.text}\n" +
                        "Điểm tổng kết: $tFormat\n" +
                        "Kết quả: $phanLoai"

        }
        private fun xoaLoiCu() {
            binding.tilTenHocPhan.error = null
            binding.tilDiemQT.error = null
            binding.tilDiemCK.error = null
        }
        private fun lamSachDuLieu() {
            binding.edtTenHocPhan.text?.clear()
            binding.edtDiemQT.text?.clear()
            binding.edtDiemCK.text?.clear()
            xoaLoiCu()
            binding.tvKetQua.text = ""
            binding.edtTenHocPhan.requestFocus()
        }
    }
