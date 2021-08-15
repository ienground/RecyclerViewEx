package net.ienlab.recyclerviewex.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import net.ienlab.recyclerviewex.R
import net.ienlab.recyclerviewex.adapter.RecyclerViewAdapter
import net.ienlab.recyclerviewex.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this

        val data = arrayListOf(false, false, false, false, false)
//        val data = arrayListOf("Hello", "World!", "From", "Korea", "Seoul")
        val adapter = RecyclerViewAdapter(data)

        binding.recyclerView.adapter = adapter

        binding.button.setOnClickListener {
            val c = Calendar.getInstance()
//            adapter.addItem(binding.position.text.toString().toInt(), "New Item ${c.get(Calendar.MINUTE)}${c.get(Calendar.SECOND)}")
        }
    }
}