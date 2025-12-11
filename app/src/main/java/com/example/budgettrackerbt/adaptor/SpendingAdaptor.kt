import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budgettrackerbt.R
import com.example.budgettrackerbt.dataClass.Transaction

class TransactionAdapter(private var list: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionVH>() {

    inner class TransactionVH(view: View) : RecyclerView.ViewHolder(view) {
        val icon = view.findViewById<ImageView>(R.id.item_image)
        val amount = view.findViewById<TextView>(R.id.item_amount)
        val category = view.findViewById<TextView>(R.id.item_heading)
        val date = view.findViewById<TextView>(R.id.item_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return TransactionVH(view)
    }

    override fun onBindViewHolder(holder: TransactionVH, position: Int) {
        val item = list[position]

        holder.category.text = item.category
        holder.amount.text = "Rs ${item.amount}"

        holder.icon.setImageResource(
            when (item.type) {
                "income" -> R.drawable.income_src
                "expense" -> when (item.category) {
                    "Food" -> R.drawable.restaurant
                    "Transport" -> R.drawable.transport
                    "Shopping" -> R.drawable.shopping
                    else -> R.drawable.expense_src
                }

                else -> R.drawable.expense_src
            }
        )
    }

    override fun getItemCount(): Int = list.size

    fun updateList(newList: List<Transaction>) {
        list = newList
        notifyDataSetChanged()
    }
}
