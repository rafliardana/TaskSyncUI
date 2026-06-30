package com.example.tasksync

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        setupRecentlyViewed()
        setupWorkspaces()
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val bottomNav: com.google.android.material.bottomnavigation.BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_boards -> {
                    // Already in boards/dashboard
                    true
                }
                R.id.nav_workspaces -> {
                    startActivity(Intent(this, WorkspacesActivity::class.java))
                    true
                }
                else -> false
            }
        }
        
        findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            // Logic to add a new board
        }
    }

    private fun setupRecentlyViewed() {
        val rv: RecyclerView = findViewById(R.id.rvRecentlyViewed)
        val boards = listOf(
            Board("Android - Jago Bayar", android.R.drawable.ic_dialog_map, "#81D4FA"),
            Board("Swift Developer Teams", android.R.drawable.ic_dialog_email, "#9575CD")
        )
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = DashboardBoardAdapter(boards) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun setupWorkspaces() {
        val rv: RecyclerView = findViewById(R.id.rvWorkspaces)
        val boards = listOf(
            Board("Cloud Management", android.R.drawable.ic_dialog_dialer, "#9575CD"),
            Board("Backend Teams", android.R.drawable.ic_dialog_alert, "#CE93D8")
        )
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = DashboardBoardAdapter(boards) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
