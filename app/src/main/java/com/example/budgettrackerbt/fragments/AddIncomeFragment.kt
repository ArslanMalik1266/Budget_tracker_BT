package com.example.budgettrackerbt.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.budgettrackerbt.R
import com.example.budgettrackerbt.dataClass.Income
import com.example.budgettrackerbt.viewModel.IncomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddIncomeFragment : Fragment() {

    private lateinit var incomeViewModel: IncomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_income, container, false)
        incomeViewModel = ViewModelProvider(requireActivity()).get(IncomeViewModel::class.java)

        val titleEt = view.findViewById<EditText>(R.id.income_title)
        val amountEt = view.findViewById<EditText>(R.id.income_amount)
        val saveBtn = view.findViewById<Button>(R.id.save_income_btn)

        saveBtn.setOnClickListener {
            val title = titleEt.text.toString()
            val amount = amountEt.text.toString().toIntOrNull()

            if (title.isNotEmpty() && amount != null) {
                val income = Income(
                    title = title,
                    amount = amount,
                    date = System.currentTimeMillis()
                )

                // Insert into Room
                incomeViewModel.addIncome(income)
                amountEt.text.clear()
                (parentFragment as? BottomSheetDialogFragment)?.dismiss()

                Toast.makeText(requireContext(), "Income Added!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Enter valid data", Toast.LENGTH_SHORT).show()
            }
        }




        return view
    }


}