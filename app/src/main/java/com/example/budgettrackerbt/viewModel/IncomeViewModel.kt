package com.example.budgettrackerbt.viewModel

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import androidx.lifecycle.viewModelScope
import com.example.budgettrackerbt.dataClass.Transaction
import com.example.budgettrackerbt.database.AppDatabase
import com.example.budgettrackerbt.repository.TransactionRepository
import kotlinx.coroutines.launch

class IncomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TransactionRepository

    val allTransactions: LiveData<List<Transaction>>
    val income: LiveData<List<Transaction>>
    val expense: LiveData<List<Transaction>>



    init {
        val dao = AppDatabase.getDatabase(application).transactionDao()
        repository = TransactionRepository(dao)

        allTransactions = repository.allTransactions
        income = repository.incomeList
        expense = repository.expenseList
    }

    fun addTransaction(income: Transaction) {
        viewModelScope.launch {
            repository.insert(income)

        }
    }

}