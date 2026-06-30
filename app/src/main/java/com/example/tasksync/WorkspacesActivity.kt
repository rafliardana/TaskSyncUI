package com.example.tasksync

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WorkspacesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_workspaces)

        setupSharedBoards()
        setupBottomNavigation()
    }

    private fun setupSharedBoards() {
        val rv: RecyclerView = findViewById(R.id.rvSharedBoards)
        val boards = listOf(
            Board("Global Assets", android.R.drawable.ic_dialog_map, "#4DB6AC"),
            Board("Marketing 2024", android.R.drawable.ic_dialog_email, "#FF8A65")
        )
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = DashboardBoardAdapter(boards) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun setupBottomNavigation() {
        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_workspaces
        
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_boards -> {
                    val intent = Intent(this, DashboardActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    startActivity(intent)
                    true
                }
                R.id.nav_workspaces -> true
                else -> false
            }
        }

        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            // Add workspace logic
        }
    }
}
