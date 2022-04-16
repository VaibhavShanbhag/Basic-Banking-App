package com.example.moneytransferapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item_user_list.view.*

class SendToUserListAdapter(var context: Context?, var sendUserArrayList: ArrayList<SendUserModelClass>): RecyclerView.Adapter<SendToUserListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SendToUserListAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_user_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SendToUserListAdapter.ViewHolder, position: Int) {
        holder.itemView.apply {
            tvaccountnumber.text = "Account No: ".plus(sendUserArrayList.get(position).accountNum)
            tvaccountname.text = sendUserArrayList.get(position).name

            cardView.setOnClickListener {
                Intent(context,TransferAmount::class.java).also {
                    it.putExtra("toAccountNum",sendUserArrayList.get(position).accountNum)
                    it.putExtra("fromAccountNum",SendToUserList.accountNum)
                    context.startActivity(it)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return sendUserArrayList.size
    }
}