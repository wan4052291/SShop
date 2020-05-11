package com.wayne.sshop

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.row_function.view.*
import java.lang.reflect.Array
val functions = listOf<String>("Camera","Invited Friend","Parking","News","Maps","Download Coupons")
class MainActivity : AppCompatActivity() {
    private val RC_NICK: Int = 101
    private val RC_SIGNUP: Int = 100

    val auth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        auth.addAuthStateListener {
            if(auth.currentUser == null){
                val intent = Intent(this,Singup::class.java)
                startActivityForResult(intent,RC_SIGNUP)
            }
        }


//        val colors = arrayOf<String>("RED","GREEN","BLUE")
//        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,colors)
//        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
//        spinner.adapter = adapter
        val firstfagment = FirstFragment()

//        recycler.layoutManager = LinearLayoutManager(this)
//        recycler.setHasFixedSize(true)
//        recycler.adapter = FunctionAdapter()
        fab.setOnClickListener { view ->
            auth.signOut()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGNUP){
            if(resultCode == Activity.RESULT_OK){
                val intent = Intent(this,Nick::class.java)
                startActivityForResult(intent,RC_NICK)
            }
        }
        if(requestCode == RC_NICK){
            if(resultCode == Activity.RESULT_OK){
                database.getReference("users")
                    .child(auth.currentUser!!.uid)
                    .child("nickname")
                    .setValue(getNickname())
            }
        }
    }
//    inner class FunctionAdapter() : RecyclerView.Adapter<FunctionHolder>() {
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FunctionHolder {
//            val view = LayoutInflater.from(parent.context)
//                .inflate(R.layout.row_function,parent,false)
//            val holder = FunctionHolder(view)
//            return holder
//        }
//        override fun getItemCount(): Int {
//            return functions.size
//        }
//        override fun onBindViewHolder(holder: FunctionHolder, position: Int) {
//            holder.nameText.text = functions.get(position)
//        }
//    }
//    class FunctionHolder(view:View) : RecyclerView.ViewHolder(view) {
//        var nameText: TextView = view.row
//    }
    override fun onResume() {
        super.onResume()
       // mNickname.text = getNickname()
        if(auth.currentUser != null){
            database.getReference("users")
                .child(auth.currentUser!!.uid)
                .child("nickname")
                .addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                        AlertDialog.Builder(this@MainActivity)
                            .setTitle("ERROR")
                            .setMessage(p0.message)
                            .setPositiveButton("OK",null)
                            .show()
                    }
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        mNickname.text = dataSnapshot.value as String
                    }
                })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}



