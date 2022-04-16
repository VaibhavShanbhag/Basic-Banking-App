package com.example.moneytransferapp

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.toolbar.*

class SendToUserList : AppCompatActivity() {
    lateinit var sendToUserListAdapter: SendToUserListAdapter
    lateinit var sendUserArrayList: ArrayList<SendUserModelClass>
    lateinit var recyclerView: RecyclerView
    var userDb: UserDatabaseHelper = UserDatabaseHelper(this,null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_to_user_list)

        accountNum = intent.getStringExtra("accountNum").toString()

        displaytoolBarName(accountNum)

        leftIcon.setOnClickListener {
            finish()
        }

        recyclerView = findViewById(R.id.userlisttransfer)
        sendUserArrayList = ArrayList<SendUserModelClass>()

        recyclerView.layoutManager = LinearLayoutManager(this)

        sendToUserListAdapter = SendToUserListAdapter(this,sendUserArrayList)
        recyclerView.adapter = sendToUserListAdapter

        displaySendUserList(accountNum)
    }

    @SuppressLint("Range")
    private fun displaytoolBarName(accountNum: String) {
        val cursor: Cursor = userDb.readSpecificData(accountNum)
        if(cursor != null)
            cursor.moveToFirst()

        val accountName = cursor.getString(cursor.getColumnIndex(UserDatabaseHelper.NAME_COL))

        tvtoolbar.text = accountName
    }

    private fun displaySendUserList(accountNum: String) {
        sendUserArrayList.clear()

        val cursor = userDb.readSendUserData(accountNum)

        val accountNumIndex = cursor.getColumnIndex(UserDatabaseHelper.ACCOUNT_NUM_COL)
        val accountNameIndex = cursor.getColumnIndex(UserDatabaseHelper.NAME_COL)

        while(cursor.moveToNext()){
            val accountNumText = cursor.getString(accountNumIndex)
            val accountNameText = cursor.getString(accountNameIndex)
            sendUserArrayList.add(SendUserModelClass(accountNumText,accountNameText))

        }

        sendToUserListAdapter.notifyDataSetChanged()

    }

    companion object{
        lateinit var accountNum: String;
    }
}