package com.example.budgettrackerbt.repository

import com.example.budgettrackerbt.dao.TransactionDao
import com.example.budgettrackerbt.dataClass.Transaction


class TransactionRepository(private val dao: TransactionDao) {

    val allTransactions = dao.getAllTransactions()
    val incomeList = dao.getIncome()
    val expenseList = dao.getExpense()

    suspend fun insert(transaction: Transaction) {
        dao.insert(transaction)
    }
}