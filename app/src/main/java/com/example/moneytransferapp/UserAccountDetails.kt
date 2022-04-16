package com.example.moneytransferapp

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user_account_details.*
import kotlinx.android.synthetic.main.toolbar.*

class UserAccountDetails : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_account_details)

        val accountNum = intent.getStringExtra("AccountNum").toString()
        val userDbHelper: UserDatabaseHelper = UserDatabaseHelper(this,null)
        val cursor: Cursor = userDbHelper.readSpecificData(accountNum)

        if(cursor != null)
            cursor.moveToFirst()

        val accountNameText = cursor?.getString(cursor.getColumnIndex(UserDatabaseHelper.NAME_COL))
        val accountNumText = cursor?.getString(cursor.getColumnIndex(UserDatabaseHelper.ACCOUNT_NUM_COL))
        val accountEmailText = cursor?.getString(cursor.getColumnIndex(UserDatabaseHelper.EMAIL_COL))
        val accountBalanceText = cursor?.getString(cursor.getColumnIndex(UserDatabaseHelper.BALANCE_COL))

        tvtoolbar.text = accountNameText
        etaccountnum.setText(accountNumText)
        etaccountname.setText(accountNameText)
        etaccountemail.setText(accountEmailText)
        tvbalance.text = "Balance: ₹".plus(accountBalanceText).plus("/-")

        leftIcon.setOnClickListener {
            finish()
        }

        btntransfer.setOnClickListener {
            Intent(this,SendToUserList::class.java).also {
                it.putExtra("accountNum",accountNum)
                startActivity(it)
            }
        }
    }
}