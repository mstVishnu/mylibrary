package com.student.mylibrary


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.student.mylibrary.databinding.ItemSingleContactBinding

class ContactAdapter(
    private val context: Context,
    private val data: ArrayList<HomeResponseModel.Result.User>
) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSingleContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = data[position]
        holder.binding.name.text = user.UserUniqueId
        holder.binding.number.text = user.UserFullName
        holder.binding.type.text = user.UserType
        holder.binding.status.text = user.UserType

        Log.e("f",user.UserFullName)
        holder.itemView.setOnClickListener {
           val intent = Intent(context, ActivitySecond::class.java)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = data.size


    class ViewHolder(val binding: ItemSingleContactBinding) : RecyclerView.ViewHolder(binding.root)
}
