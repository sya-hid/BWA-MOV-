package com.syarifhidayatullah.bwamov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.syarifhidayatullah.bwamov.R
import com.syarifhidayatullah.bwamov.home.HomeActivity
import com.syarifhidayatullah.bwamov.sign.signin.SignInActivity
import com.syarifhidayatullah.bwamov.utils.Preferences
import kotlinx.android.synthetic.main.activity_on_boarding_one.*

class OnBoardingOneActivity : AppCompatActivity() {
    lateinit var preferences: Preferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_one)
        preferences= Preferences(this)

        if (preferences.getValues("onboarding").equals("1")){
            finishAffinity()
            var intent=Intent(this, HomeActivity::class.java)
            startActivity(intent)
            //finish()
        }
        btn_lanjutkan.setOnClickListener {
            var intent= Intent(this,OnBoardingTwoActivity::class.java)
            startActivity(intent)
            finish()
        }
        btn_lewati_perkenalan.setOnClickListener {
            preferences.setValues("onboarding","1")
            finishAffinity()
            var intent=Intent(this,SignInActivity::class.java)
            startActivity(intent)
            //finish()
        }
    }
}