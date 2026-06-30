package com.example.tasksync

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rvBoard: RecyclerView = findViewById(R.id.rvBoard)
        rvBoard.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvBoard.adapter = ColumnAdapter(getDummyData())
        
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val bottomNav: com.google.android.material.bottomnavigation.BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_boards -> {
                    // Navigate back to Dashboard
                    val intent = Intent(this, DashboardActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    startActivity(intent)
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
            // Logic to add a new card/column
        }
    }

    private fun getDummyData(): List<TaskColumn> {
        val avatar1 = "https://i.pravatar.cc/150?u=1"
        val avatar2 = "https://i.pravatar.cc/150?u=2"
        val avatar3 = "https://i.pravatar.cc/150?u=3"

        return listOf(
            TaskColumn("In Progress", "#FFB300", listOf(
                TaskCard("Create Login Page", "Frontend", "#2196F3", 40, 5, 2, "Septian Nando", avatar1),
                TaskCard("Create Mockup Dashboard", "UI Design", "#FF5722", 80, 2, 0, "Moana Gabriel", avatar2)
            )),
            TaskColumn("To Do", "#9E9E9E", listOf(
                TaskCard("Create All Documentation API", "Backend", "#4CAF50", 0, 2, 0, "Ihamadani", avatar3),
                TaskCard("Setup Firebase", "Backend", "#4CAF50", 10, 0, 1, "User", avatar1)
            )),
            TaskColumn("Review", "#673AB7", listOf(
                TaskCard("Fix Header Layout", "UI Design", "#FF5722", 100, 8, 3, "Septian Nando", avatar1)
            ))
        )
    }
}
