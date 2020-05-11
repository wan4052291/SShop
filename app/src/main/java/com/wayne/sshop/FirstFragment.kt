package com.wayne.sshop

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.row_function.view.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    val colors = arrayOf<String>("Green","Blue","RED","RED","RED","RED","RED","RED","RED")


    val functions = listOf<String>("Camera","Invited Friend","Parking","News","Maps","Download Coupons")
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

//        recycler.layoutManager = LinearLayoutManager(this.activity)
//        recycler.setHasFixedSize(true)
//        recycler.adapter = FunctionAdapter()
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_first, container, false)
    }
    inner class FunctionAdapter : RecyclerView.Adapter<FunctionHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FunctionHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_function,parent,false)
            val holder = FunctionHolder(view)
            return holder
        }

        override fun getItemCount(): Int {
            return functions.size
        }

        override fun onBindViewHolder(holder: FunctionHolder, position: Int) {
            holder.name.text = functions.get(position)
        }

    }
    class FunctionHolder(view: View) : RecyclerView.ViewHolder(view){
        val name : TextView = view.row
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        try {
            val adapter = ArrayAdapter<String>(this.requireContext(),android.R.layout.simple_spinner_item,colors)
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            if(adapter == null){
                AlertDialog.Builder(this.requireActivity())
                    .setTitle("ERROR")
                    .setMessage("adapter null")
                    .show()
            }
            spinner1.adapter = adapter
        }catch (e : Exception){
            AlertDialog.Builder(this.requireActivity())
                .setTitle("ERROR")
                .setMessage(e.message.toString())
                .show()
        }
    }
}


