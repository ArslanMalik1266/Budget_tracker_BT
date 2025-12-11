package com.example.budgettrackerbt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.budgettrackerbt.fragments.AddTransactionBottomSheet

import com.example.budgettrackerbt.viewModel.IncomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var incomeViewModel: IncomeViewModel
    private var totalIncome = 0
    private var totalExpense = 0
    private var savingAmount = 0
    private var savingPercentage = 0
    private var lastTransaction: Pair<String, Int>? = null
    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        window.navigationBarColor = ContextCompat.getColor(this, R.color.white)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.coordinator_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.nav_view)


        val toolbar =findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.top_app_bar)
        setSupportActionBar(toolbar)
         toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val prefs = getSharedPreferences("budget_prefs", MODE_PRIVATE)
        savingPercentage = prefs.getInt("savingPercentage", 0)


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val fab = findViewById<FloatingActionButton>(R.id.fab_btn)
        val incomeAmountTv = findViewById<TextView>(R.id.income_amount_tv)
        val expenseAmountTv = findViewById<TextView>(R.id.expense_amount_tv)
        val availableBalanceTv: TextView = findViewById(R.id.available_blnc_tv)
        val budgetPlannedTv: TextView = findViewById(R.id.budget_planned_tv)
        val savingGoalLayout: ConstraintLayout = findViewById(R.id.saving_goal_layout)
        incomeViewModel = ViewModelProvider(this).get(IncomeViewModel::class.java)

        fun updateBalanceUI() {
            savingAmount = totalIncome * savingPercentage / 100
            val availableBalance = totalIncome - totalExpense - savingAmount
            budgetPlannedTv.text = "Rs. $availableBalance"
            availableBalanceTv.text = "Rs. ${totalIncome - totalExpense}"
        }

        incomeViewModel.income.observe(this) { incomeList ->
            totalIncome = incomeList.sumOf { it.amount }
            incomeAmountTv.text = "Rs. $totalIncome"
            availableBalanceTv.text = "Rs. ${totalIncome - totalExpense}"
            updateBalanceUI()
        }

        incomeViewModel.expense.observe(this) { expenseList ->
            totalExpense = expenseList.sumOf { it.amount }
            expenseAmountTv.text = "Rs. $totalExpense"
            availableBalanceTv.text = "Rs. ${totalIncome - totalExpense}"
            updateBalanceUI()
        }

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
        savingGoalLayout.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.dialogue_saving_goal, null)
            val editText = view.findViewById<EditText>(R.id.saving_goal_input)
            val confirmBtn = view.findViewById<Button>(R.id.confirm_btn)

            confirmBtn.setOnClickListener {
                savingPercentage = editText.text.toString().toIntOrNull() ?: 0
                prefs.edit().putInt("savingPercentage", savingPercentage).apply()
                updateBalanceUI()
                dialog.dismiss()
            }

            dialog.setContentView(view)
            dialog.show()
        }

//        navView.setNavigationItemSelectedListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.nav_undo -> {
//                    lastTransaction?.let { (type, amount) ->
//                        if (type == "income") totalIncome -= amount
//                        else if (type == "expense") totalExpense -= amount
//                        updateBalanceUI()
//                        lastTransaction = null
//                    }
//                    drawerLayout.closeDrawers()
//                    true
//                }
//
//                R.id.nav_reset -> {
//                    totalIncome = 0
//                    totalExpense = 0
//                    savingPercentage = 0
//                    prefs.edit().clear().apply()
//                    updateBalanceUI()
//                    drawerLayout.closeDrawers()
//                    true
//                }
//
//                else -> false
//            }
//        }
    }


    private fun saveLastTransaction(type: String, amount: Int) {
        lastTransaction = Pair(type, amount)
    }
}

