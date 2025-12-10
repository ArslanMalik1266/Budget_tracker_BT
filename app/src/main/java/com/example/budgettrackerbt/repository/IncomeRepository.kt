package com.example.budgettrackerbt.repository

import androidx.lifecycle.LiveData
import com.example.budgettrackerbt.dao.IncomeDao
import com.example.budgettrackerbt.dataClass.Income

class IncomeRepository(private val incomeDao: IncomeDao) {

    val getAllIncome : LiveData<List<Income>> = incomeDao.getAllIncome()

    suspend fun insertIncome(income: Income) {
        incomeDao.insertIncome(income)
    }

}