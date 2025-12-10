package com.example.budgettrackerbt

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.budgettrackerbt.fragments.AddExpenseFragment
import com.example.budgettrackerbt.fragments.AddIncomeFragment

class AddTransactionPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2  // 0: Income, 1: Expense

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) AddIncomeFragment() else AddExpenseFragment()
    }
}
