package com.syarifhidayatullah.bwamov.home.tiket

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.syarifhidayatullah.bwamov.R
import com.syarifhidayatullah.bwamov.home.dashboard.ComingSoonAdapter
import com.syarifhidayatullah.bwamov.model.Film
import com.syarifhidayatullah.bwamov.utils.Preferences
import kotlinx.android.synthetic.main.fragment_ticket.*


class TicketFragment : Fragment() {
  private lateinit var preferences: Preferences
  private lateinit var mDatabaseReference: DatabaseReference
    private var dataList=ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        preferences= Preferences(context!!)
        mDatabaseReference=FirebaseDatabase.getInstance().getReference("Film")
        rcv_checkout_tiket.layoutManager=LinearLayoutManager(context)
        getData()
    }

    private fun getData() {
mDatabaseReference.addValueEventListener(object : ValueEventListener{
    override fun onCancelled(error: DatabaseError) {
        Toast.makeText(context,error.message,Toast.LENGTH_LONG).show()
    }

    override fun onDataChange(snapshot: DataSnapshot) {
        dataList.clear()
        for (getdataSnapshot in snapshot.children){
            val film=getdataSnapshot.getValue(Film::class.java)
            dataList.add(film!!)
        }

        rcv_checkout_tiket.adapter=ComingSoonAdapter(dataList){
            var intent= Intent(context,TicketActivity::class.java).putExtra("data",it)
            startActivity(intent)

        }
        txv_total.setText("${dataList.size} Movies")
    }

})
    }

}