package com.syarifhidayatullah.bwamov.checkout

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

class CheckoutAdapter(private var data: List<Checkout>, private var listener: (Checkout) -> Unit) :
    RecyclerView.Adapter<CheckoutAdapter.ViewHolder>() {

    lateinit var contexAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contexAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_checkout, parent, false)
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
        private val txv_harga: TextView = view.findViewById(R.id.txv_harga)

        fun bindItem(data: Checkout, listener: (Checkout) -> Unit, contex: Context) {
            val localID = Locale("id", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localID)
            txv_harga.setText(formatRupiah.format(data.harga!!.toDouble()))


            if (data.kursi!!.startsWith("Total")) {
                txv_seat.setText(data.kursi)
                txv_seat.setCompoundDrawables(null, null, null, null)
            } else {
                txv_seat.setText("Seat No. " + data.kursi)
         //       txv_harga.setText(data.harga)
            }

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}
