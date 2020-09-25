package com.syarifhidayatullah.bwamov.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syarifhidayatullah.bwamov.R
import com.syarifhidayatullah.bwamov.model.Film

class ComingSoonAdapter(private var data: List<Film>, private var listener:(Film) -> Unit) :
    RecyclerView.Adapter<ComingSoonAdapter.ViewHolder>() {

    lateinit var contexAdapter:Context


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComingSoonAdapter.ViewHolder {
val layoutInflater=LayoutInflater.from(parent.context)
        contexAdapter=parent.context
        val inflatedView=layoutInflater.inflate(R.layout.row_item_coming_soon,parent,false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: ComingSoonAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position],listener,contexAdapter)
     }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val txv_title:TextView=view.findViewById(R.id.txv_title)
        private val txv_genre:TextView=view.findViewById(R.id.txv_genre)
        private val txv_rate:TextView=view.findViewById(R.id.txv_rate)

        private val image:ImageView=view.findViewById(R.id.imv_back)
        fun bindItem(data:Film,listener:(Film)->Unit,contex:Context){
            txv_title.setText(data.judul)
            txv_genre.setText(data.genre)
            txv_rate.setText(data.rating)

            Glide.with(contex)
                .load(data.poster)
                .into(image)

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}
