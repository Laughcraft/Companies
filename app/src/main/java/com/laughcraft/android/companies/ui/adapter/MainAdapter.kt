package com.laughcraft.android.companies.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.laughcraft.android.companies.R
import com.laughcraft.android.companies.repository.entity.CompanyName
import com.squareup.picasso.Picasso

class MainAdapter(var names: List<CompanyName>,
                  var onItemClick: (position: Int)-> Unit) :
        RecyclerView.Adapter<MainAdapter.MainHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val itemHolder: MainHolder
        
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        
        itemHolder = MainHolder(view)
        return itemHolder
    }
    
    override fun getItemCount(): Int = names.size
    
    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(names[position], position)
    }
    
    inner class MainHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image)
        val textView: TextView = view.findViewById(R.id.text)
        
        fun bind(companyName: CompanyName, position: Int) {
            textView.text = companyName.name
            
            view.setOnClickListener { onItemClick.invoke(position) }
            
            Picasso.with(view.context)
                .load("http://megakohz.bget.ru/test_task/${companyName.img}").fit()
                .error(R.drawable.error)
                .into(imageView)
        }
    }
    
    
}