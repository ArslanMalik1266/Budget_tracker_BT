package com.example.budgettrackerbt.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.budgettrackerbt.dataClass.Income
import com.example.budgettrackerbt.database.AppDatabase
import com.example.budgettrackerbt.repository.IncomeRepository
import kotlinx.coroutines.launch

class IncomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = IncomeRepository(AppDatabase.getDatabase(application).incomeDao())

    val allIncome: LiveData<List<Income>> = repo.getAllIncome

    fun addIncome(income: Income) {
        viewModelScope.launch {
            repo.insertIncome(income)

        }
    }
}