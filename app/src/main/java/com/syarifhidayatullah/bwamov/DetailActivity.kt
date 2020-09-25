package com.syarifhidayatullah.bwamov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.syarifhidayatullah.bwamov.checkout.PilihBangkuActivity
import com.syarifhidayatullah.bwamov.home.dashboard.PlaysAdapter
import com.syarifhidayatullah.bwamov.model.Film
import com.syarifhidayatullah.bwamov.model.Plays
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var mDatabaseReference: DatabaseReference
    private var dataList = ArrayList<Plays>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<Film>("data")
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Film")
            .child(data.judul.toString())
            .child("play")


        txv_title.text = data.judul
        txv_genre.text = data.genre
        txv_desc.text = data.desc
        txv_rate.text = data.rating

        Glide.with(this)
            .load(data.poster)
            .into(imv_back)
        rcv_who_playe.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()
        btn_pilih_bangku.setOnClickListener {
            var intent =
                Intent(this@DetailActivity, PilihBangkuActivity::class.java).putExtra("data", data)
            startActivity(intent)
        }
    }

    private fun getData() {
        mDatabaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetailActivity, error.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (getDataSnapshot in snapshot.children) {
                    var Film = getDataSnapshot.getValue(Plays::class.java)
                    dataList.add(Film!!)

                }
                rcv_who_playe.adapter = PlaysAdapter(dataList) {

                }
            }

        })
    }
}