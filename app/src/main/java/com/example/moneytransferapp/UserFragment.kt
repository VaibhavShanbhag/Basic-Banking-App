package com.example.moneytransferapp

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserFragment: Fragment(){
    lateinit var userListAdapter: UserListAdapter
    lateinit var userClassArrayList: ArrayList<UserClass>
    lateinit var recyclerViewUser: RecyclerView
    lateinit var userDb: UserDatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v: View = inflater.inflate(R.layout.user_fragment,null)

        recyclerViewUser = v.findViewById(R.id.recyclerviewusers)
        userClassArrayList = ArrayList<UserClass>()

        userDb = UserDatabaseHelper(context,null)
        recyclerViewUser.layoutManager = LinearLayoutManager(context)

        userListAdapter = UserListAdapter(context,userClassArrayList)
        recyclerViewUser.adapter = userListAdapter

        displayUserList()

        return v
    }

    private fun displayUserList() {
        userClassArrayList.clear()

        var cursor: Cursor = userDb.readAllData()

        var accountNumIndex: Int = cursor.getColumnIndex(UserDatabaseHelper.ACCOUNT_NUM_COL)
        var nameIndex: Int = cursor.getColumnIndex(UserDatabaseHelper.NAME_COL)
        var emailIndex: Int = cursor.getColumnIndex(UserDatabaseHelper.EMAIL_COL)
        var balanceIndex: Int = cursor.getColumnIndex(UserDatabaseHelper.BALANCE_COL)

        while (cursor.moveToNext()){
            var accountNum = cursor.getString(accountNumIndex)
            var name = cursor.getString(nameIndex)
            var email = cursor.getString(emailIndex)
            var balance = cursor.getInt(balanceIndex)

            userClassArrayList.add(UserClass(accountNum,name,email,balance))
        }

        userListAdapter.notifyDataSetChanged()

    }
}