package com.syarifhidayatullah.bwamov.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.syarifhidayatullah.bwamov.R
import com.syarifhidayatullah.bwamov.model.Checkout
import com.syarifhidayatullah.bwamov.utils.Preferences
import kotlinx.android.synthetic.main.activity_checkout.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckoutActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()
    private var total: Int = 0
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preferences = Preferences(this)
        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout>

        for (a in dataList.indices) {
            total += dataList[a].harga!!.toInt()
        }
      //  dataList.add(Checkout("Total harus dibayar ", total.toString()))
        val localID = Locale("id", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localID)
        txv_total.setText(formatRupiah.format(total.toDouble()))

        rcv_checkout.layoutManager = LinearLayoutManager(this)
        rcv_checkout.adapter =
            CheckoutAdapter(dataList) {

            }
        btn_bayar.setOnClickListener {
            var intent= Intent(this,CheckoutSuccessActivity::class.java)
            startActivity(intent)
        }

    }
}