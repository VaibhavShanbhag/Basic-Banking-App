package com.example.moneytransferapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TransactionDatabaseHelper(context: Context?, factory: SQLiteDatabase.CursorFactory?): SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    companion object{
        private val DATABASE_NAME = "transaction.db"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "Transaction_Table"
        val FROM_ACCOUNT_NAME_COL = "from_account_name"
        val TO_ACCOUNT_NAME_COL = "to_account_name"
        val AMOUNT_COL = "amount"
        val STATUS_COL =  "status"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE " + TABLE_NAME + " (" + FROM_ACCOUNT_NAME_COL + " VARCHAR, " + TO_ACCOUNT_NAME_COL + " VARCHAR, " + AMOUNT_COL + " INTEGER, " + STATUS_COL + " INTEGER)"
        db?.execSQL(query)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion != newVersion){
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }
    }

    public fun readTransactionData(): Cursor{
        val db: SQLiteDatabase = this.writableDatabase
        val cursor: Cursor = db.rawQuery("select * from $TABLE_NAME",null)

        return cursor
    }
}