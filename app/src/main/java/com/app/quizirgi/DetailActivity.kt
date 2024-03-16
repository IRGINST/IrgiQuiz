package com.app.quizirgi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.quizirgi.databinding.ActivityDetailBinding
import java.text.NumberFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    private lateinit var bind: ActivityDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bind.root)
        ViewCompat.setOnApplyWindowInsetsListener(bind.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dataNama = intent.getStringExtra("nama")
        val dataMember = intent.getStringExtra("jenisMember")
        val dataNamaBarang = intent.getStringExtra("namaBarang")
        val dataHargaBarang = intent.getIntExtra("hargaBarang", 0)
        val dataTotalHarga = intent.getIntExtra("totalHarga", 0)
        val dataDiskonHarga = intent.getDoubleExtra("diskonHarga", 0.0)
        val dataDiskonMember = intent.getIntExtra("diskonMember", 0)
        val dataJumlahBayar = intent.getDoubleExtra("jumlahBayar", 0.0)

        println(dataNama)
        println(dataMember)
        println(dataNamaBarang)
        println(dataHargaBarang)
        println(dataTotalHarga)
        println(dataDiskonHarga)
        println(dataDiskonMember)
        println(dataJumlahBayar)

        val locale = Locale("in", "ID")
        val format = NumberFormat.getNumberInstance(locale)

        bind.nama.text = "Selamat Datang $dataNama"
        bind.member.text = "Tipe Member $dataMember"
        bind.namaBarang.text = "Nama Barang : $dataNamaBarang"
        bind.hargaBarang.text = "Harga : Rp" + format.format(dataHargaBarang)
        bind.totalHarga.text = "Total Harga : Rp" + format.format(dataTotalHarga)
        bind.discountHarga.text = "Diskon Harga : Rp" + format.format(dataDiskonHarga)
        bind.diskonMember.text = "Diskon Member : $dataDiskonMember%"
        bind.jumlahBayar.text = "Jumlah Bayar : Rp" + format.format(dataJumlahBayar)

        val jumlah = format.format(dataJumlahBayar)

        bind.btnShare.setOnClickListener {
            val i = Intent()
            i.setAction(Intent.ACTION_SEND)
            i.putExtra(Intent.EXTRA_TEXT, "Jumlah Bayar : Rp $jumlah")
            i.setType("text/plain")
            startActivity(i)
        }
    }
}