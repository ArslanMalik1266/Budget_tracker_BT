package com.example.budgettrackerbt

import TransactionAdapter
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budgettrackerbt.viewModel.IncomeViewModel
import com.google.android.material.appbar.MaterialToolbar

class mySpendings : AppCompatActivity() {
    private lateinit var incomeViewModel: IncomeViewModel
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var progressBar: ProgressBar
    var totalIncome = 0
    var totalExpense = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_my_spendings)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.grey_background)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.lower_recycler_view)

        transactionAdapter = TransactionAdapter(listOf())
        recyclerView.adapter = transactionAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        incomeViewModel = ViewModelProvider(this).get(IncomeViewModel::class.java)
        val incomeTv = findViewById<TextView>(R.id.Income_tv)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)

        incomeViewModel.allTransactions.observe(this) { incomeList ->
            transactionAdapter.updateList(incomeList)
        }

        incomeViewModel.income.observe(this) { incomeList ->
            totalIncome = incomeList.sumOf { it.amount }

            incomeTv.text = "Rs. $totalIncome"
            updateProgressBar()

        }
        incomeViewModel.expense.observe(this) { expenseList ->
            totalExpense = expenseList.sumOf { it.amount }
            updateProgressBar()
        }

    }

    fun updateProgressBar() {
        if (totalIncome > 0) {
            progressBar.max = totalIncome
            progressBar.progress = totalExpense
        } else {
            progressBar.max = 100
            progressBar.progress = 0
        }
    }

}