package edu.farmingdale.individualproject3.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.farmingdale.individualproject3.R

class DBAdapter(private val users: List<User>) : RecyclerView.Adapter<DBAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //Add more views as needed
        val firstNameTextView: TextView = view.findViewById(R.id.tvLayoutFirst)
        val lastNameTextView: TextView = view.findViewById(R.id.tvLayoutLast)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlayout, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        //Bind more data as needed
        holder.firstNameTextView.text = user.firstName
        holder.lastNameTextView.text = user.lastName

    }

    override fun getItemCount() = users.size
}