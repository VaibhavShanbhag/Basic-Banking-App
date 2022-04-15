package com.example.moneytransferapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item.view.*

class UserListAdapter(var context: Context?, var userClassArrayList: ArrayList<UserClass>) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListAdapter.ViewHolder, position: Int) {
        holder.itemView.apply {
            cardView.setOnClickListener {
                Intent(context,UserAccountDetails::class.java).also {
                    it.putExtra("AccountNum",userClassArrayList.get(position).accountNum)
                    context.startActivity(it)
                }
            }

            tvaccountnumber.text = "Account No: ".plus(userClassArrayList.get(position).accountNum)
            tvaccountname.text = userClassArrayList.get(position).name
            tvaccountmail.text = userClassArrayList.get(position).email
            tvaccountbalance.text = "Balance: ₹".plus(userClassArrayList.get(position).balance)

        }
    }

    override fun getItemCount(): Int {
        return userClassArrayList.size
    }
}