package com.example.moneytransferapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.transactions_fragment.*

class TransactionFragment: Fragment() {
    lateinit var transactionListAdapter: TransactionListAdapter
    lateinit var transactionClassArrayList: ArrayList<TransactionModelClass>
    lateinit var recyclerViewTransaction: RecyclerView
    lateinit var transactiondb: TransactionDatabaseHelper
    lateinit var linearLayoutTransaction: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.transactions_fragment,null)

        recyclerViewTransaction = v.findViewById(R.id.recyclerviewtransactions)
        linearLayoutTransaction = v.findViewById(R.id.lltransaction)

        transactionClassArrayList = ArrayList<TransactionModelClass>()

        transactiondb = TransactionDatabaseHelper(context,null)
        recyclerViewTransaction.layoutManager = LinearLayoutManager(context)

        transactionListAdapter = TransactionListAdapter(context, transactionClassArrayList)
        recyclerViewTransaction.adapter = transactionListAdapter

        displayTransactionList()

        if(transactionClassArrayList.isEmpty()){
            recyclerViewTransaction.visibility = View.GONE
            linearLayoutTransaction.visibility = View.VISIBLE
        }

        return v
    }

    private fun displayTransactionList() {
        transactionClassArrayList.clear()

        var cursor = transactiondb.readTransactionData()

        var fromAmountNameIndex = cursor.getColumnIndex(TransactionDatabaseHelper.FROM_ACCOUNT_NAME_COL)
        var toAccountNameIndex = cursor.getColumnIndex(TransactionDatabaseHelper.TO_ACCOUNT_NAME_COL)
        var amountIndex = cursor.getColumnIndex(TransactionDatabaseHelper.AMOUNT_COL)
        var statusIndex = cursor.getColumnIndex(TransactionDatabaseHelper.STATUS_COL)

        while (cursor.moveToNext()){
            var fromAccountText = cursor.getString(fromAmountNameIndex)
            var toAccountNameText = cursor.getString(toAccountNameIndex)
            var amountInt = cursor.getInt(amountIndex)
            var statusInt = cursor.getInt(statusIndex)

            transactionClassArrayList.add(TransactionModelClass(fromAccountText,toAccountNameText,amountInt,statusInt))
        }
        transactionListAdapter.notifyDataSetChanged()
    }
}