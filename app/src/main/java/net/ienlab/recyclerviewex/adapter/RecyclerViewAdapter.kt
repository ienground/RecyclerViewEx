package net.ienlab.recyclerviewex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.switchmaterial.SwitchMaterial
import net.ienlab.recyclerviewex.R
import net.ienlab.recyclerviewex.utils.ToggleAnimation

class RecyclerViewAdapter(var items: ArrayList<Boolean>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_recycler, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

//        holder.text.text = items[position]
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "clicked: ${position} : ${items[position]}, adapterPosition: ${holder.adapterPosition}", Toast.LENGTH_SHORT).show()
        }

        holder.btnDown.setOnClickListener {
            val show = holder.toggleLayout(!items[holder.adapterPosition], it, holder.layoutExpand)
            items[holder.adapterPosition] = show
        }
        holder.itemView.setOnClickListener {
            val show = holder.toggleLayout(!items[holder.adapterPosition], holder.btnDown, holder.layoutExpand)
            items[holder.adapterPosition] = show
        }
    }

    override fun getItemCount(): Int = items.size

    fun addItem(position: Int, data: String) {
//        items.add(position, data)
//        notifyItemRangeChanged(position, items.size - position)
        notifyItemInserted(position)



    }

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        val text: TextView = itemView.findViewById(R.id.text)

        val tvLabel: TextView = itemView.findViewById(R.id.tv_label)
        val groupTime: ConstraintLayout = itemView.findViewById(R.id.group_time)
        val tvApm: TextView = itemView.findViewById(R.id.tv_apm)
        val tvTime: TextView = itemView.findViewById(R.id.tv_time)
        val switchWidget: SwitchMaterial = itemView.findViewById(R.id.switchWidget)
        val layoutExpand: ConstraintLayout = itemView.findViewById(R.id.layout_expand)
        val checkRepeat: MaterialCheckBox = itemView.findViewById(R.id.check_repeat)
        val groupRing: LinearLayout = itemView.findViewById(R.id.group_ring)
        val tvRing: TextView = itemView.findViewById(R.id.tv_ring)
        val btnDown: ImageButton = itemView.findViewById(R.id.btn_down)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btn_delete)
        val line: View = itemView.findViewById(R.id.line1)
        val tvRepeatText: TextView = itemView.findViewById(R.id.tv_repeat_text)
        val groupRepeatDay: ConstraintLayout = itemView.findViewById(R.id.group_repeat_day)
        val btnsRepeatDay: List<ToggleButton> = arrayListOf(
            itemView.findViewById(R.id.btn_repeat_sun),
            itemView.findViewById(R.id.btn_repeat_mon),
            itemView.findViewById(R.id.btn_repeat_tue),
            itemView.findViewById(R.id.btn_repeat_wed),
            itemView.findViewById(R.id.btn_repeat_thu),
            itemView.findViewById(R.id.btn_repeat_fri),
            itemView.findViewById(R.id.btn_repeat_sat)
        )

        fun toggleLayout(isExpanded: Boolean, view: View, layoutExpand: View): Boolean {
            ToggleAnimation.toggleArrow(view, isExpanded)

            if (isExpanded) {
                ToggleAnimation.expand(layoutExpand)
            } else {
                ToggleAnimation.collapse(layoutExpand)
            }

            return isExpanded
        }

        fun toggleGroupRepeat(isExpanded: Boolean, layoutExpand: ConstraintLayout): Boolean {
            if (isExpanded) {
                ToggleAnimation.expand(layoutExpand)
            } else {
                ToggleAnimation.collapse(layoutExpand)
            }

            return isExpanded
        }
    }
}