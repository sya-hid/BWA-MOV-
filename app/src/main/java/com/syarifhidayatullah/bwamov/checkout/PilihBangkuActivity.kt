package com.syarifhidayatullah.bwamov.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.syarifhidayatullah.bwamov.R
import com.syarifhidayatullah.bwamov.model.Checkout
import com.syarifhidayatullah.bwamov.model.Film
import kotlinx.android.synthetic.main.activity_pilih_bangku.*

class PilihBangkuActivity : AppCompatActivity() {
    var statusA1:Boolean=false
    var statusA2:Boolean=false
    var statusA3:Boolean=false
    var statusA4:Boolean=false
    var total:Int=0

    private var dataList=ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_bangku)

        val data=intent.getParcelableExtra<Film>("data")
        txv_title.text=data.judul

        a1.setOnClickListener {
            if (statusA1){
                a1.setImageResource(R.drawable.ic_rectangle_empty)
                statusA1=false
                total -= 1
                beliTiket(total)
            }else{
                a1.setImageResource(R.drawable.ic_rectangle_selected)
                statusA1=true
                total += 1
                beliTiket(total)
                val data =Checkout("A1","70000")
                dataList.add(data)
            }
        }
        a2.setOnClickListener {
            if (statusA2){
                a2.setImageResource(R.drawable.ic_rectangle_empty)
                statusA2=false
                total -= 1
                beliTiket(total)
            }else{
                a2.setImageResource(R.drawable.ic_rectangle_selected)
                statusA2=true
                total += 1
                beliTiket(total)
                val data =Checkout("A2","70000")
                dataList.add(data)
            }
        }
        a3.setOnClickListener {
            if (statusA3){
                a3.setImageResource(R.drawable.ic_rectangle_empty)
                statusA3=false
                total -= 1
                beliTiket(total)
            }else{
                a3.setImageResource(R.drawable.ic_rectangle_selected)
                statusA3=true
                total += 1
                beliTiket(total)
                val data =Checkout("A3","70000")
                dataList.add(data)
            }
        }
        a4.setOnClickListener {
            if (statusA4){
                a4.setImageResource(R.drawable.ic_rectangle_empty)
                statusA4=false
                total -= 1
                beliTiket(total)
            }else{
                a4.setImageResource(R.drawable.ic_rectangle_selected)
                statusA4=true
                total += 1
                beliTiket(total)
                val data =Checkout("A4","70000")
                dataList.add(data)
            }
        }
        btn_pilih_bangku.setOnClickListener {
            var intent=Intent(this@PilihBangkuActivity,CheckoutActivity::class.java).putExtra("data",dataList)
            startActivity(intent)
        }
    }

    private fun beliTiket(total: Int) {
        if (total == 0){
            btn_pilih_bangku.setText("Beli Tiket")
            btn_pilih_bangku.visibility= View.INVISIBLE
        }else{
            btn_pilih_bangku.setText("Beli Tiket ("+total+")")
            btn_pilih_bangku.visibility= View.VISIBLE
        }

    }
}