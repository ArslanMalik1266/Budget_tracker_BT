package com.example.budgettrackerbt

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.budgettrackerbt.fragments.AddTransactionBottomSheet

import com.example.budgettrackerbt.viewModel.IncomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var incomeViewModel: IncomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        window.navigationBarColor = ContextCompat.getColor(this, R.color.white)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val fab = findViewById<FloatingActionButton>(R.id.fab_btn)
        val incomeAmountTv = findViewById<TextView>(R.id.income_amount_tv)
        incomeViewModel = ViewModelProvider(this).get(IncomeViewModel::class.java)

        // Observe LiveData
        incomeViewModel.allIncome.observe(this, Observer { incomeList ->
            val total = incomeList.sumOf { it.amount }
            incomeAmountTv.text = "Rs. $total"
        })

        fab.setOnClickListener {
            val bottomSheet = AddTransactionBottomSheet()
            bottomSheet.show(supportFragmentManager, "AddTransactionBottomSheet")
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    // Home screen
                    true
                }

                R.id.menu_history -> {
                    // History tab -> MySpendingActivity
                    val intent = Intent(this, mySpendings::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }

        }
    }
}