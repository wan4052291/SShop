package com.wayne.sshop

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_singup.*
import kotlin.math.sign

class Singup : AppCompatActivity() {
    val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)
        signup.setOnClickListener {
            val sEmail = email.text.toString()
            val sPassword = password.text.toString()
            auth.createUserWithEmailAndPassword(sEmail,sPassword)
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        AlertDialog.Builder(this)
                            .setTitle("Success")
                            .setMessage("Account created")
                            .setPositiveButton("OK"){_,_->
                                setResult(Activity.RESULT_OK)
                                finish()
                            }
                            .show()

                    }else{
                        AlertDialog.Builder(this)
                            .setTitle("Error")
                            .setMessage(it.exception?.message)
                            .setPositiveButton("OK",null)
                            .show()
                    }
                }
        }
    }
}
