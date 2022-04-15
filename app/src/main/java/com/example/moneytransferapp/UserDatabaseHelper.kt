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
        insertData("20220410","Shreyas Naik","shreyasnaik@gmail.com",7000,db)
        insertData("20220412","Virat Sharma","viratsharma@gmail.com",15000,db)
        insertData("20220418","Piyush Nayak","piyushnayak@gmail.com",8500,db)
        insertData("20220420","Sourav Joshi","souravjoshi@gmail.com",20000,db)
        insertData("20220421","Vikram Rathore","vikramrathore@gmail.com",17000,db)
        insertData("20220423","Vishwajeet Tekale","vishwajeettekale@gmail.com",25000,db)
        insertData("20220425","Peter Parkar","peterparkar@gmail.com",5000,db)
        insertData("20220427","Shivam Dubey","shivamdubey@gmail.com",6500,db)
        insertData("20220424","Sagar Iyer","sagariyer@gmail.com",3000,db)
        insertData("20220430","Ambati Rayudu","ambatirayudu@gmail.com",20000,db)
        insertData("20220422","Rohan Parkar","rohanparkar@gmail.com",12000,db)
        insertData("20220433","Gaurav Singh","gauravsingh@gmail.com",2500,db)
        insertData("20220440","Raj Singh","rajsingh@gmail.com",6000,db)
    }

    private fun insertData(accountNum: String, name: String, email: String, balance: Int, db: SQLiteDatabase?) {
        val query = ("INSERT INTO " + TABLE_NAME +
                " values(" + accountNum + ", " + name + ", " + email + ", " + balance + ")")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion != newVersion){
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }
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