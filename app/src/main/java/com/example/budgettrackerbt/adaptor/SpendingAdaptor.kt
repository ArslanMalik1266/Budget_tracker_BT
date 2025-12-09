package com.example.budgettrackerbt.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budgettrackerbt.R
import com.example.budgettrackerbt.dataClass.Spending

class SpendingAdapter(private val spendingList: List<Spending>) :
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
        val spending = spendingList[position]
        holder.image.setImageResource(spending.imageRes)
        holder.heading.text = spending.heading
        holder.amount.text = spending.amount
        holder.date.text = spending.date
    }

    override fun getItemCount(): Int = spendingList.size
}