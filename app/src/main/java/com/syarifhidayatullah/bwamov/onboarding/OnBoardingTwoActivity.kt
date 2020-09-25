package com.syarifhidayatullah.bwamov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.syarifhidayatullah.bwamov.R
import kotlinx.android.synthetic.main.activity_on_boarding_two.*

class OnBoardingTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_two)

        btn_lanjutkan.setOnClickListener {
            var intent= Intent(this,OnBoardingThreeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}