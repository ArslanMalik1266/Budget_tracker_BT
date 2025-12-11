package com.example.budgettrackerbt.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.budgettrackerbt.dataClass.Transaction

@Dao
interface TransactionDao {

    @Insert
    suspend fun insert(transaction: Transaction)

    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun getAllTransactions(): LiveData<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE type = 'income'")
    fun getIncome(): LiveData<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE type = 'expense'")
    fun getExpense(): LiveData<List<Transaction>>

}
