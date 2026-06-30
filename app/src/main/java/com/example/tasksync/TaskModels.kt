package com.example.tasksync

data class TaskCard(
    val title: String,
    val tag: String? = null,
    val tagColor: String? = "#6200EE",
    val progress: Int = 0,
    val commentCount: Int = 0,
    val attachmentCount: Int = 0,
    val assigneeName: String = "User",
    val assigneeAvatarUrl: String? = null
)

data class TaskColumn(
    val title: String,
    val statusColor: String = "#FFB300", // Yellow default
    val cards: List<TaskCard>
)

data class Board(
    val title: String,
    val iconRes: Int = android.R.drawable.ic_menu_agenda,
    val color: String = "#2196F3"
)

data class Workspace(
    val title: String,
    val memberCount: Int = 0
)
