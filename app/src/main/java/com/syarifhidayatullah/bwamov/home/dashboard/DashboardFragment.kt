package com.syarifhidayatullah.bwamov.home.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import com.syarifhidayatullah.bwamov.DetailActivity
import com.syarifhidayatullah.bwamov.R
import com.syarifhidayatullah.bwamov.model.Film
import com.syarifhidayatullah.bwamov.utils.Preferences
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class DashboardFragment : Fragment() {


    private lateinit var preferences: Preferences
    private lateinit var mDatabaseReference: DatabaseReference
    private var dataList = ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(activity!!.applicationContext)
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Film")

        txv_nama.setText(preferences.getValues("nama"))
      currency(preferences.getValues("saldo")!!.toDouble(), txv_ewallet)

//        if (preferences.getValues("saldo").equals("")) {
//     // currency(preferences.getValues("saldo")!!.toDouble(), txv_ewallet)
//              txv_ewallet.setText("sssss")
////            txv_ewallet.setText(formatRupiah.format(7000))
//               }
        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(imv_profil)
        rcv_now_playing.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcv_coming_soon.layoutManager = LinearLayoutManager(context)
        getdata()
    }

    private fun getdata() {
        mDatabaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (getdata in snapshot.children) {
                    var film = getdata.getValue(Film::class.java)
                    dataList.add(film!!)
                }
                rcv_now_playing.adapter = NowPlayingAdapter(dataList) {
                    var intent= Intent(context,DetailActivity::class.java).putExtra("data",it)
                    startActivity(intent)
                }
                rcv_coming_soon.adapter=ComingSoonAdapter(dataList){
                    var intent= Intent(context,DetailActivity::class.java).putExtra("data",it)
                    startActivity(intent)
                }
            }

        })
    }

    private fun currency(saldo: Double, text: TextView) {
        val localID = Locale("id", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localID)
        text.setText(formatRupiah.format(saldo.toDouble()))

    }
}