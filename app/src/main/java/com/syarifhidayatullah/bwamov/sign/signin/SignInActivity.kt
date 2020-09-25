package com.syarifhidayatullah.bwamov.sign.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.syarifhidayatullah.bwamov.home.HomeActivity
import com.syarifhidayatullah.bwamov.R
import com.syarifhidayatullah.bwamov.sign.signup.SignUpActivity
import com.syarifhidayatullah.bwamov.utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_in.*



class SignInActivity : AppCompatActivity() {
    lateinit var username:String
    lateinit var password:String
    lateinit var mDatabase:DatabaseReference
    lateinit var preference: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mDatabase=FirebaseDatabase.getInstance().getReference("User")
        preference=Preferences(this)
       preference.setValues("onboarding","1")
        if (preference.getValues("status").equals("1")){
            finishAffinity()
            var intent=Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    btn_masuk.setOnClickListener {
        username=edt_username.text.toString()
        password=edt_password.text.toString()
        if (username.equals("")){
            edt_username.error="Masukkan Username Anda"
            edt_username.requestFocus()
        }else if (password.equals("")){
        edt_password.error="Masukkan Password Anda"
        edt_password.requestFocus()
    }else{
            doLogin(username,password)
        }
    }
        btn_daftar.setOnClickListener {
            var  intent=Intent(this,
                SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun doLogin(username: String, password: String) {
    mDatabase.child(username).addValueEventListener(object :ValueEventListener{
        override fun onCancelled(p0: DatabaseError) {
            Toast.makeText(this@SignInActivity,p0.message,Toast.LENGTH_LONG).show()
        }

        override fun onDataChange(dataSnapshot: DataSnapshot) {
       var user=dataSnapshot.getValue(User::class.java)
            if (user == null){
                Toast.makeText(this@SignInActivity,"Usser Tidak Ditemukan",Toast.LENGTH_LONG).show()
            }else{
                if (user.password.equals(password)){

                    preference.setValues("nama",user.nama.toString())
                    preference.setValues("username",user.username.toString())
                    preference.setValues("email",user.email.toString())
                    preference.setValues("saldo",user.saldo.toString())
                    preference.setValues("url",user.url.toString())
                    preference.setValues("status","1")
                var intent= Intent(this@SignInActivity,
                    HomeActivity::class.java)
                startActivity(intent)}
                else{
                    Toast.makeText(this@SignInActivity,"Password Salah",Toast.LENGTH_LONG).show()
                }
            }
        }

    })
    }
}