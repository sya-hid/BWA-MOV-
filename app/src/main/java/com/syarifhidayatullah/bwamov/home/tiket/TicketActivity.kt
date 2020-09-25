package com.syarifhidayatullah.bwamov.home.tiket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.syarifhidayatullah.bwamov.R
import com.syarifhidayatullah.bwamov.model.Checkout
import com.syarifhidayatullah.bwamov.model.Film
import kotlinx.android.synthetic.main.activity_ticket.*

class TicketActivity : AppCompatActivity() {
    private var dataList=ArrayList<Checkout>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        var data=intent.getParcelableExtra<Film>("data")

        txv_title.text=data.judul
        txv_genre.text=data.genre
        txv_rate.text=data.rating

        Glide.with(this)
            .load(data.poster)
            .into(imv_poster)

        rcv_checkout_tiket.layoutManager=LinearLayoutManager(this)
        dataList.add(Checkout("A1",""))
        dataList.add(Checkout("A2",""))

        rcv_checkout_tiket.adapter=TicketAdapter(dataList){

        }

    }
}