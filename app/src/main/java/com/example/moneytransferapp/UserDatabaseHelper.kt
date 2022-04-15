package com.example.moneytransferapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context: Context?, factory: SQLiteDatabase.CursorFactory?): SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    companion object{
        private val DATABASE_NAME = "banking_app"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "Users"
        val ACCOUNT_NUM_COL = "account_num"
        val NAME_COL = "name"
        val EMAIL_COL = "email"
        val BALANCE_COL =  "balance"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " (" +
                ACCOUNT_NUM_COL + " VARCHAR PRIMARY KEY, " +
                NAME_COL + " VARCHAR, " +
                EMAIL_COL + " VARCHAR, " +
                BALANCE_COL + " INTEGER NOT NULL" + ")")

        db?.execSQL(query)

        db?.execSQL("insert into $TABLE_NAME values('20220401','Vaibhav Shanbhag','vaibhavshanbhag@gmail.com',10000)")
        db?.execSQL("insert into $TABLE_NAME values('20220410','Shreyas Naik','shreyasnaik@gmail.com',7000)")
        db?.execSQL("insert into $TABLE_NAME values('20220412','Virat Sharma','viratsharma@gmail.com',15000)")
        db?.execSQL("insert into $TABLE_NAME values('20220418','Piyush Nayak','piyushnayak@gmail.com',8500)")
        db?.execSQL("insert into $TABLE_NAME values('20220420','Sourav Joshi','souravjoshi@gmail.com',20000)")
        db?.execSQL("insert into $TABLE_NAME values('20220421','Vikram Rathore','vikramrathore@gmail.com',17000)")
        db?.execSQL("insert into $TABLE_NAME values('20220423','Vishwajeet Tekale','vishwajeettekale@gmail.com',25000)")
        db?.execSQL("insert into $TABLE_NAME values('20220425','Peter Parkar','peterparkar@gmail.com',5000)")
        db?.execSQL("insert into $TABLE_NAME values('20220427','Shivam Dubey','peterparkar@gmail.com',6500)")
        db?.execSQL("insert into $TABLE_NAME values('20220424','Sagar Iyer','peterparkar@gmail.com',3000)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion != newVersion){
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }
    }

    public fun readAllData(): Cursor{
        val db: SQLiteDatabase = this.writableDatabase
        val cursor: Cursor = db.rawQuery("select * from $TABLE_NAME",null)
        return cursor
    }

    public fun readSpecificData(accountNum: String): Cursor{
        val db: SQLiteDatabase = this.writableDatabase
        val cursor: Cursor = db.rawQuery("select * from $TABLE_NAME where $ACCOUNT_NUM_COL = $accountNum",null)
        return cursor
    }

    public fun updateUserBalance(accountNum: String, balance: Int){
        val db: SQLiteDatabase = this.writableDatabase
        val query = "update $TABLE_NAME set $BALANCE_COL = $balance where $ACCOUNT_NUM_COL = $accountNum"
        db.execSQL(query)
    }
}