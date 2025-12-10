package com.example.budgettrackerbt.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.budgettrackerbt.dataClass.Income

@Dao
interface IncomeDao {

    @Insert
    suspend fun insertIncome(income: Income)

    @Query("SELECT * FROM income_table ORDER BY id DESC")
    fun getAllIncome(): LiveData<List<Income>>
}