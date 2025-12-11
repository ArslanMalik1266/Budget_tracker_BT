import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.budgettrackerbt.R
import androidx.lifecycle.ViewModelProvider
import com.example.budgettrackerbt.dataClass.Transaction
import com.example.budgettrackerbt.viewModel.IncomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class IncomeFragment : Fragment() {

    private lateinit var viewModel: IncomeViewModel
    private lateinit var amountEt: EditText
    private lateinit var titleEt: EditText
    private lateinit var saveBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_add_income, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(IncomeViewModel::class.java)

        amountEt = view.findViewById(R.id.income_amount)
        titleEt = view.findViewById(R.id.income_title)
        saveBtn = view.findViewById(R.id.save_income_btn)

        saveBtn.setOnClickListener {
            val amount = amountEt.text.toString().toIntOrNull()
            val title = titleEt.text.toString()

            if (amount != null && title.isNotEmpty()) {
                val transaction = Transaction(
                    type = "income",
                    amount = amount,
                    category = title,
                    date = System.currentTimeMillis()
                )

                viewModel.addTransaction(transaction)

                amountEt.text.clear()
                titleEt.text.clear()

                Toast.makeText(requireContext(), "Income Added", Toast.LENGTH_SHORT).show()
                parentFragment?.let { fragment ->
                    if (fragment is BottomSheetDialogFragment) {
                        fragment.dismiss()
                    }
                }


            } else {
                Toast.makeText(requireContext(), "Enter valid data", Toast.LENGTH_SHORT).show()
            }

        }

        return view
    }
}
