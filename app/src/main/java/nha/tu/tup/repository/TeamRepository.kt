package nha.tu.tup.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import nha.tu.tup.firebase.FirebaseInstance
import nha.tu.tup.models.Member
import nha.tu.tup.models.Team
import nha.tu.tup.models.User

class TeamRepository {
    val teamCollectionRef = FirebaseInstance.firebaseFirestoreInstance.collection("teams")
    val userCollectionRef = FirebaseInstance.firebaseFirestoreInstance.collection("users")
    val memberCollectionRef = FirebaseInstance.firebaseFirestoreInstance.collection("members")
    val auth = FirebaseInstance.auth
    val currentUser = auth.currentUser

//    val _users : MutableLiveData

    suspend fun addNewTeam(team: Team) {
        val teamIdFirestore = teamCollectionRef.document().id
        team.teamId = teamIdFirestore

        val currentUserId = currentUser?.uid
        team.leaderId = currentUserId
        
        teamCollectionRef
            .add(team)
            .addOnSuccessListener {
                Log.d("Firestore", "Add Successfully")
            }
    }

    fun getTeams(listener: (List<Team>) -> Unit) {
        teamCollectionRef
            .orderBy("createdDate", Query.Direction.DESCENDING)
            .whereEqualTo("leaderId", currentUser?.uid)
            .addSnapshotListener{ snapshot, exeption ->
            if(exeption != null){
                Log.w("Firestore", "Listen failed", exeption)
                return@addSnapshotListener
            }
            if (snapshot != null && !snapshot.isEmpty) {
                val teamList = snapshot.documents.map { document ->
                    document.toObject(Team::class.java) ?: Team() // Handle null case
                }
                listener(teamList)
            }
        }
    }

    suspend fun getTeamById(teamId: String): Team? {
        var team: Team? = null
        teamCollectionRef
            .document(teamId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Chuyển đổi document thành đối tượng Team
                    team = document.toObject(Team::class.java)
                } else {
                    Log.d("Firestore", "No such document")
                }
            }
        return team
    }

    suspend fun getTeamByName(teamName: String): List<Team> {
        val teams: List<Team> = listOf()
        teamCollectionRef
            .whereEqualTo("teamName", teamName)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    // Chuyển đổi document thành đối tượng Book
                    val team = document.toObject(Team::class.java)
                    Log.d("Firestore", "Book: $team")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Firestore", "Error getting documents: ", exception)
            }
        return teams
    }

    suspend fun addMember(member: Member){
        val memberId = memberCollectionRef.document().id
        member.memberId = memberId

        memberCollectionRef
            .add(member)
            .addOnSuccessListener {
                Log.d("Firestore", "Add Successfully")
            }
    }

    suspend fun getMembers(teamId: String, listener: (List<User>) -> Unit){
        memberCollectionRef
            .whereEqualTo("teamId", teamId)
            .addSnapshotListener { querySnapshot, exception ->
                if (exception != null) {
                    // Xử lý lỗi nếu có
                    Log.e("FirestoreError", "Error fetching members: ${exception.message}")
                    return@addSnapshotListener
                }
                if (querySnapshot != null && !querySnapshot.isEmpty) {
                    val userIds = querySnapshot.documents.mapNotNull { it.getString("userId") }

                    userCollectionRef
                        .whereIn("userId", userIds)
                        .get()
                        .addOnSuccessListener {
                            val users = it.toObjects(User::class.java)
                            listener(users)
                        }
                        .addOnFailureListener { e ->
                            Log.e("FirestoreError", "Error fetching user details: ${e.message}")
                        }
                }else {
                    listener(emptyList())
                }
            }
    }

}