package com.example.moneytransferapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usersFragment: Fragment = UserFragment()
        val transactionFragment: Fragment = TransactionFragment()

        setCurrentFragment(usersFragment)

        bottomnavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.user -> setCurrentFragment(usersFragment)
                R.id.transaction -> setCurrentFragment(transactionFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flfragment,fragment)
            commit()
        }
    }
}