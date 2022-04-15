package com.example.moneytransferapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context: Context, factory: SQLiteDatabase.CursorFactory?): SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " (" +
                ACCOUNT_NUM_COL + " VARCHAR PRIMARY KEY, " +
                NAME_COL + " VARCHAR, " +
                EMAIL_COL + " VARCHAR, " +
                BALANCE_COL + " INTEGER NOT NULL" + ")")

        db?.execSQL(query)

        insertData("20220401","Vaibhav Shanbhag","vaibhavshanbhag@gmail.com",10000,db)
    }

    private fun insertData(accountNum: String, name: String, email: String, balance: Int, db: SQLiteDatabase?) {
        val query = ("INSERT INTO " + TABLE_NAME +
                " values(" + accountNum + ", " + name + ", " + email + ", " + balance + ")")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object{
        private val DATABASE_NAME = "banking_app"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "Users"
        val ACCOUNT_NUM_COL = "account_num"
        val NAME_COL = "name"
        val EMAIL_COL = "email"
        val BALANCE_COL =  "balance"

    }
}