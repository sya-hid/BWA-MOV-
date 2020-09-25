package com.syarifhidayatullah.bwamov.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.syarifhidayatullah.bwamov.R
import com.syarifhidayatullah.bwamov.home.dashboard.DashboardFragment
import com.syarifhidayatullah.bwamov.home.setting.ProfilFragment
import com.syarifhidayatullah.bwamov.home.tiket.TicketFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragmentHome=
            DashboardFragment()
        val fragmentTicket=
            TicketFragment()
        val fragmentProfil=
            ProfilFragment()
        setFragment(fragmentHome)
        imv_dasdboard.setOnClickListener {
            setFragment(fragmentHome)
            changeIcon(imv_dasdboard,R.drawable.home_1)
            changeIcon(imv_ticket,R.drawable.ticket)
            changeIcon(imv_profil,R.drawable.profil)
        }
        imv_ticket.setOnClickListener {
            setFragment(fragmentTicket)
            changeIcon(imv_dasdboard,R.drawable.home)
            changeIcon(imv_ticket,R.drawable.ticket_1)
            changeIcon(imv_profil,R.drawable.profil)
        }
        imv_profil.setOnClickListener {
            setFragment(fragmentProfil)
            changeIcon(imv_dasdboard,R.drawable.home)
            changeIcon(imv_ticket,R.drawable.ticket)
            changeIcon(imv_profil,R.drawable.profil_1)
        }
    }
    private fun setFragment(fragment: Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame,fragment)
        fragmentTransaction.commit()
    }
    private fun changeIcon(imv:ImageView,int:Int){
        imv.setImageResource(int)
    }
}