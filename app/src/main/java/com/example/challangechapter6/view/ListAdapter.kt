package com.example.challangechapter6.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challangechapter6.databinding.ListItemBinding
import com.example.challangechapter6.model.ResponseProductItem


class ListAdapter(private var listcar : List<ResponseProductItem>, private var mContext: Context): RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    class ViewHolder(var binding : ListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.nameCar.text = listcar[position].name
        holder.binding.categoryCar.text = listcar[position].category
        holder.binding.priceCar.text = listcar[position].price.toString()
        Glide.with(holder.itemView.context).load(listcar[position].imageLink).into(holder.binding.imgCar)

        holder.binding.Card.setOnClickListener {
            val bund = Bundle()
            val move = Intent(mContext, DetailActivity::class.java)
            move.putExtra("name", listcar[position].name.toString())
            move.putExtra("popularity", listcar[position].rating.toString())
            move.putExtra("imagelink", listcar[position].imageLink)
            move.putExtra("desc", listcar[position].description)
//            move.putExtra("name", "istcar!![position].name.toString()")
            startActivity(mContext, move,bund)
        }

    }

    override fun getItemCount(): Int {

        return listcar.size

    }
}