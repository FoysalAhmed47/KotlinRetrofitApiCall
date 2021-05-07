package com.example.hellokotlin

import android.content.Context
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.row_items.view.*
import java.security.AccessControlContext

class MyAdapter(val context:Context,val userList: List<DataItem>):RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewTypep: Int): ViewHolder {
        var itemView=LayoutInflater.from(context).inflate(R.layout.row_items,parent,false)
        return ViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userId.text=userList[position].userId.toString()
        holder.title.text=userList[position].title

    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        lateinit var userId:TextView
        lateinit var title:TextView

        init {
            userId=itemView.userId
            title=itemView.title
        }
    }
}