package com.example.moneytransferapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_transfer_amount.*
import kotlinx.android.synthetic.main.toolbar.*

class TransferAmount : AppCompatActivity() {
    companion object{
        lateinit var toAccountNumText: String
        lateinit var toAccountNameText: String
        var toAccountBalanceText: Int = 0

        lateinit var fromAccountNumText: String
        lateinit var fromAccountNameText: String
        var fromAccountBalanceText: Int = 0
    }


    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_amount)

        val toAccountNum = intent.getStringExtra("toAccountNum").toString()
        val fromAccountNum = intent.getStringExtra("fromAccountNum").toString()
        val userDbHelper: UserDatabaseHelper = UserDatabaseHelper(this,null)

        Log.d("To",toAccountNum)
        Log.d("From",fromAccountNum)

        leftIcon.setImageResource(R.drawable.ic_baseline_close_24)
        tvtoolbar.text = ""

        val cursorFrom: Cursor = userDbHelper.readSpecificData(fromAccountNum)
        val cursorTo: Cursor = userDbHelper.readSpecificData(toAccountNum)

        if(cursorFrom != null || cursorTo != null){
            cursorFrom.moveToFirst()
            cursorTo.moveToFirst()
        }

        toAccountNumText = cursorTo.getString(cursorTo.getColumnIndex(UserDatabaseHelper.ACCOUNT_NUM_COL))
        toAccountNameText = cursorTo.getString(cursorTo.getColumnIndex(UserDatabaseHelper.NAME_COL))
        toAccountBalanceText = cursorTo.getInt(cursorTo.getColumnIndex(UserDatabaseHelper.BALANCE_COL))

        fromAccountNumText = cursorFrom.getString(cursorTo.getColumnIndex(UserDatabaseHelper.ACCOUNT_NUM_COL))
        fromAccountNameText = cursorFrom.getString(cursorTo.getColumnIndex(UserDatabaseHelper.NAME_COL))
        fromAccountBalanceText = cursorFrom.getInt(cursorTo.getColumnIndex(UserDatabaseHelper.BALANCE_COL))

        toaccontname.text = "Transfering to ".plus(toAccountNameText)
        toaccontnum.text = "A/C No: ".plus(toAccountNumText)

        btntransfer.setOnClickListener {
            val amountText = etamount.text.toString()

            if(amountText.equals("") || amountText.equals("0")){
                Toast.makeText(this,"Transfer should be atleast of ₹1", Toast.LENGTH_SHORT).show()
            }

            else{
                val amountInt = Integer.parseInt(amountText)

                if(amountInt > fromAccountBalanceText){
                    Toast.makeText(this,"Your Account Balance is $fromAccountBalanceText", Toast.LENGTH_SHORT).show()
                }

                else{
                    val fromLeftBalance = fromAccountBalanceText - amountInt
                    val toAddedBalance = toAccountBalanceText + amountInt
                    userDbHelper.updateUserBalance(fromAccountNum,fromLeftBalance)
                    userDbHelper.updateUserBalance(toAccountNum,toAddedBalance)
                    val transactionDatabaseHelper = TransactionDatabaseHelper(this,null)
                    val db: SQLiteDatabase = transactionDatabaseHelper.writableDatabase
                    val contentValues = ContentValues()

                    contentValues.put(TransactionDatabaseHelper.FROM_ACCOUNT_NAME_COL, fromAccountNameText)
                    contentValues.put(TransactionDatabaseHelper.TO_ACCOUNT_NAME_COL, toAccountNameText)
                    contentValues.put(TransactionDatabaseHelper.AMOUNT_COL, amountInt)
                    contentValues.put(TransactionDatabaseHelper.STATUS_COL,1)

                    db.insert(TransactionDatabaseHelper.TABLE_NAME,null,contentValues)

                    Toast.makeText(this,"Transaction Successfull", Toast.LENGTH_SHORT).show()
                    Intent(this,MainActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                }

            }
        }

        leftIcon.setOnClickListener {
            dialogBuilder()
        }

    }

    override fun onBackPressed() {
        dialogBuilder()
    }

    private fun dialogBuilder(){
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Are you sure you want to cancel the transaction?")
        alertBuilder.setPositiveButton("Yes"){dialogInterface, which ->
            val transactionDatabaseHelper = TransactionDatabaseHelper(this,null)
            val db: SQLiteDatabase = transactionDatabaseHelper.writableDatabase
            val contentValues = ContentValues()

            contentValues.put(TransactionDatabaseHelper.FROM_ACCOUNT_NAME_COL, fromAccountNameText)
            contentValues.put(TransactionDatabaseHelper.TO_ACCOUNT_NAME_COL, toAccountNameText)
            contentValues.put(TransactionDatabaseHelper.AMOUNT_COL, 0)
            contentValues.put(TransactionDatabaseHelper.STATUS_COL,0)

            db.insert(TransactionDatabaseHelper.TABLE_NAME,null,contentValues)

            Toast.makeText(this,"Transaction Cancelled", Toast.LENGTH_SHORT).show()
            Intent(this,MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        alertBuilder.setNegativeButton("No",null)
        alertBuilder.create()
        alertBuilder.setCancelable(false)
        alertBuilder.show()
    }
}