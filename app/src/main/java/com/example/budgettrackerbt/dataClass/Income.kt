package com.example.budgettrackerbt.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "income_table")
data class Income(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val amount : Int,
    val title : String,
    val date : Long
)
