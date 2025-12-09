package com.example.budgettrackerbt

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budgettrackerbt.adaptor.SpendingAdapter
import com.example.budgettrackerbt.dataClass.Spending
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.appbar.MaterialToolbar

class mySpendings : AppCompatActivity() {
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

        val recyclerView: RecyclerView = findViewById(R.id.lower_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val spendingList = listOf(
            Spending(R.drawable.shopping, "Shopping", "Rs. 1200", "08 Dec 2025"),
            Spending(R.drawable.transport, "Transport", "Rs. 300", "07 Dec 2025"),
            Spending(R.drawable.shopping, "Shopping", "Rs. 2500", "05 Dec 2025"),
            Spending(R.drawable.restaurant, "Restaurant", "Rs. 450", "04 Dec 2025")
        )

        val adapter = SpendingAdapter(spendingList)
        recyclerView.adapter = adapter

    }
}