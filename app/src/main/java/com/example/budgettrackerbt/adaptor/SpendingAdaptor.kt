package com.example.budgettrackerbt.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budgettrackerbt.R
import com.example.budgettrackerbt.dataClass.Income
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SpendingAdapter(private var incomeList: List<Income>) :
    RecyclerView.Adapter<SpendingAdapter.SpendingViewHolder>() {

    inner class SpendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.item_image)
        val heading: TextView = itemView.findViewById(R.id.item_heading)
        val amount: TextView = itemView.findViewById(R.id.item_amount)
        val date: TextView = itemView.findViewById(R.id.item_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpendingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return SpendingViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpendingViewHolder, position: Int) {
        val income = incomeList[position]
        holder.heading.text = income.title
        holder.amount.text = "Rs. ${income.amount}"
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        holder.date.text = sdf.format(Date(income.date))
//
//        val imageRes = when (income.category) {
//            "Restaurant" -> R.drawable.restaurant
//            "Shopping" -> R.drawable.shopping
//            "Transport" -> R.drawable.transport
//            else -> {}
//        }
//        holder.image.setImageResource(imageRes as Int)

    }

    override fun getItemCount(): Int = incomeList.size

    fun updateData ( newList : List<Income>){
        incomeList = newList
        notifyDataSetChanged()
    }
}