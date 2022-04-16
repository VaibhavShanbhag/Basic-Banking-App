package com.example.moneytransferapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.toolbar.*

class TransferAmount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_amount)

        val toAccountNum = intent.getStringExtra("toAccountNum").toString()
        val fromAccountNum = intent.getStringExtra("fromAccountNum").toString()

        Log.d("To",toAccountNum)
        Log.d("From",fromAccountNum)

        leftIcon.setImageResource(R.drawable.ic_baseline_close_24)
        tvtoolbar.text = ""



    }
}