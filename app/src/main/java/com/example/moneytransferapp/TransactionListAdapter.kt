package com.example.moneytransferapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_transfer_item.view.*

class TransactionListAdapter(var context: Context?, var transactionClassArrayList: ArrayList<TransactionModelClass>): RecyclerView.Adapter<TransactionListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_transfer_item,parent,false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            tvfromaccountname.text = "From: ".plus(transactionClassArrayList.get(position).fromAccountName)
            tvtoaccountname.text = "To: ".plus(transactionClassArrayList.get(position).toAccountName)
            tvamount.text = "Amount: ₹".plus(transactionClassArrayList.get(position).amount).plus("/-")

            if (transactionClassArrayList.get(position).status == 1){
                tvstatus.text = "Success"
                tvstatus.setTextColor(resources.getColor(R.color.success))
            }

            else{
                tvstatus.text = "Cancel"
                tvstatus.setTextColor(resources.getColor(R.color.cancel))
            }

        }
    }

    override fun getItemCount(): Int {
        return transactionClassArrayList.size
    }
}