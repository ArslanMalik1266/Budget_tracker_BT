package com.example.budgettrackerbt.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.budgettrackerbt.R



class AddExpenseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_expense, container, false)
    }
//
//    val spinner = view.findViewById<Spinner>(R.id.expense_category_spinner)
//
//    val categories = listOf("Restaurant", "Shopping", "Transport")
//    val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
//    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//    spinner.adapter = adapter
}