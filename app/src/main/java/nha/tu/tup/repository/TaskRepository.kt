package nha.tu.tup.repository

import android.util.Log
import nha.tu.tup.firebase.FirebaseInstance
import nha.tu.tup.models.Task

class TaskRepository {
    val taskCollectionRef = FirebaseInstance.firebaseFirestoreInstance.collection("tasks")

    fun addNewTask(task: Task) {
        val taskId = taskCollectionRef.document().id
        task.taskId = taskId
        taskCollectionRef
            .document(taskId)
            .set(task)
            .addOnSuccessListener {
                Log.d("Firestore", "Add Successfully")
            }
    }

    fun getTasks(teamId: String, listener: (List<Task>) -> Unit) {
        taskCollectionRef
            .whereEqualTo("teamId", teamId)
            .orderBy("taskCreatedDate", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.w("Firestore", "Listen failed", error)
                    return@addSnapshotListener
                }
                if (snapshot != null && !snapshot.isEmpty) {
                    val taskList = snapshot.documents.mapNotNull { documentSnapshot ->
                        documentSnapshot.toObject(Task::class.java)
                    }
                    Log.w("Firestore", "Get tasks", error)
                    Log.d("Firestore", "Snapshot updated with ${snapshot?.size()} tasks")
                    listener(taskList)
                }
            }
    }
}