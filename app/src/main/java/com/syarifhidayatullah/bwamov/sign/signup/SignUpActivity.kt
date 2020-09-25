package com.syarifhidayatullah.bwamov.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.syarifhidayatullah.bwamov.R
import com.syarifhidayatullah.bwamov.sign.signin.User
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    lateinit var username:String
    lateinit var password:String
    lateinit var nama:String
    lateinit var email:String

    lateinit var mDatabaseReference: DatabaseReference
lateinit var mFirebaseInstance:FirebaseDatabase
    lateinit var mDatabase:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        mFirebaseInstance= FirebaseDatabase.getInstance()
        mDatabase=FirebaseDatabase.getInstance().getReference()
        mDatabaseReference=mFirebaseInstance.getReference("User")

        btn_lanjutkan.setOnClickListener {

            username=edt_username.text.toString()
            password=edt_password.text.toString()
            nama=edt_nama.text.toString()
            email=edt_email.text.toString()
            if (username.equals("")){
                edt_username.error="Silahkan masukkna Username Anda"
                edt_username.requestFocus()
            }else if (password.equals("")){
                edt_password.error="Silahkan masukkna Password Anda"
                edt_password.requestFocus()
            }else if (email.equals("")){
                edt_email.error="Silahkan masukkna Email Anda"
                edt_email.requestFocus()
            }else if (nama.equals("")){
                edt_nama.error="Silahkan masukkna Nama Anda"
                edt_nama.requestFocus()
            }else{
                saveUser(email,nama,password,username)
            }
        }
    }

    private fun saveUser( email: String, nama: String,password: String,username: String ) {
        var user= User()
        user.email=email
        user.nama=nama
        user.password=password
        user.username=username

        if (username!=null){
            checkingUser(username,user)
        }
    }

    private fun checkingUser(username: String, data: User) {
        mDatabaseReference.child(username).addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@SignUpActivity,p0.message,Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
               var user=p0.getValue(User::class.java)
                if (user == null){
                    mDatabaseReference.child(username).setValue(data)

                    var intent= Intent(this@SignUpActivity,SignUpPhotoActivity::class.java).putExtra("nama",data?.nama)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@SignUpActivity,"User Sudah Ada",Toast.LENGTH_LONG).show()
                }

            }

        })
    }
}