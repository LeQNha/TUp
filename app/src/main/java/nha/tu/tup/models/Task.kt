package nha.tu.tup.models

import com.google.firebase.Timestamp

data class Task (
    var taskId: String? = null,
    val taskTitle: String = "",
    val taskDescription: String = "",
    val taskCreatedDate: Timestamp? = null,
    val taskDue: Timestamp? = null,
    val teamId: String = ""
)