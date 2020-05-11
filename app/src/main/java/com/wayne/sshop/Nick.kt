package com.wayne.sshop

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_nick.*

class Nick : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nick)
        done.setOnClickListener {
            val nickname = NIcktext.text.toString()
            setNickcname(nickname)
            FirebaseDatabase.getInstance().getReference("users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
                .child("nickname")
                .setValue(nickname)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
