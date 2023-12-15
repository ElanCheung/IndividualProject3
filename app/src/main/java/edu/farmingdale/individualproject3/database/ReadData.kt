package edu.farmingdale.individualproject3.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.farmingdale.individualproject3.R

class ReadData : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DBAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_data)

        val userRepository = NameRepository.getInstance(this)
        val users = userRepository.getAllUsers()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = DBAdapter(users)
        recyclerView.adapter = adapter
    }
}

