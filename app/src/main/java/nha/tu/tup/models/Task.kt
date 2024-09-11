package nha.tu.tup.models

data class Task (
    val taskTitle: String,
    val taskDue: String,
    val memberNumber: Int,
    val teamName: String = "Prime Tea"
)