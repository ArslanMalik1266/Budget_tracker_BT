import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.budgettrackerbt.R
import com.example.budgettrackerbt.dataClass.Transaction
import com.example.budgettrackerbt.viewModel.IncomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ExpenseFragment : Fragment() {

    private lateinit var viewModel: IncomeViewModel

    private lateinit var amountEt: EditText
    private lateinit var categorySpinner: Spinner
    private lateinit var saveBtn: Button

    val categories = listOf("Food", "Transport", "Shopping", "Bills", "Other")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_add_expense, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(IncomeViewModel::class.java)

        amountEt = view.findViewById(R.id.expense_amount)
        categorySpinner = view.findViewById(R.id.expense_category_spinner)
        saveBtn = view.findViewById(R.id.save_expense_btn)

        // Spinner Setup
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter

        saveBtn.setOnClickListener {

            val amount = amountEt.text.toString().toIntOrNull()
            val category = categorySpinner.selectedItem.toString()

            if (amount != null) {

                val transaction = Transaction(
                    type = "expense",
                    amount = amount,
                    category = category,
                    date = System.currentTimeMillis()
                )

                viewModel.addTransaction(transaction)

                amountEt.text.clear()

                Toast.makeText(requireContext(), "Expense Added!", Toast.LENGTH_SHORT).show()

                parentFragment?.let { fragment ->
                    if (fragment is BottomSheetDialogFragment) {
                        fragment.dismiss()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Enter valid amount", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
