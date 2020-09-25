package com.syarifhidayatullah.bwamov.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.syarifhidayatullah.bwamov.R
import com.syarifhidayatullah.bwamov.model.Plays

class PlaysAdapter(private var data: List<Plays>, private var listener:(Plays) -> Unit) :
    RecyclerView.Adapter<PlaysAdapter.ViewHolder>() {

    lateinit var contexAdapter:Context


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaysAdapter.ViewHolder {
val layoutInflater=LayoutInflater.from(parent.context)
        contexAdapter=parent.context
        val inflatedView=layoutInflater.inflate(R.layout.row_item_plays,parent,false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: PlaysAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position],listener,contexAdapter)
     }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val txv_nama:TextView=view.findViewById(R.id.txv_nama)

        private val image:ImageView=view.findViewById(R.id.imv_foto)
        fun bindItem(data:Plays,listener:(Plays)->Unit,contex:Context){
            txv_nama.setText(data.nama)


            Glide.with(contex)
                .load(data.url)
                .apply(RequestOptions.circleCropTransform())
                .into(image)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}
