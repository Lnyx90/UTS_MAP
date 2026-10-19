package org.umn.utsmap.data.model

data class NotificationItem(
    val id: String,
    val title: String,
    val description: String,
    val timestamp: String,
    val type: String,
    val statusBadge: String? = null,
    val isPaid: Boolean = false
)