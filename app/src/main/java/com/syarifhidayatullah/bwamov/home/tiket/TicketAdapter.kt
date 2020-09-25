package com.syarifhidayatullah.bwamov.home.tiket

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.syarifhidayatullah.bwamov.R
import com.syarifhidayatullah.bwamov.model.Checkout
import java.text.NumberFormat
import java.util.*

class TicketAdapter(private var data: List<Checkout>, private var listener: (Checkout) -> Unit) :
    RecyclerView.Adapter<TicketAdapter.ViewHolder>() {

    lateinit var contexAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contexAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_checkout_white, parent, false)
        return ViewHolder(
            inflatedView
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contexAdapter)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txv_seat: TextView = view.findViewById(R.id.txv_seat)

        fun bindItem(data: Checkout, listener: (Checkout) -> Unit, contex: Context) {
txv_seat.setText("Seat No. " + data.kursi)



            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}
