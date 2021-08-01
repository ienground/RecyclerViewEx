package net.ienlab.recyclerviewex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import net.ienlab.recyclerviewex.R

class RecyclerViewAdapter(var items: ArrayList<String>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_recycler, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.text.text = items[position]
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "clicked: ${position} : ${items[position]}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = items.size

    fun addItem(position: Int, data: String) {
        items.add(position, data)
//        notifyItemInserted(position)
        

    }

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.text)
    }
}