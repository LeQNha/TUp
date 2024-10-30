package nha.tu.tup.repository

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import nha.tu.tup.AutthenciateScreen
import nha.tu.tup.firebase.FirebaseInstance
import nha.tu.tup.models.FriendRequest
import nha.tu.tup.models.User
import nha.tu.tup.ui.acitvities.MainActivity
import java.util.Date

class UserRepository() {
    val userCollectionRef = FirebaseInstance.firebaseFirestoreInstance.collection("users")
    val friendRequestCollectionRef =
        FirebaseInstance.firebaseFirestoreInstance.collection("friend_requests")
    val auth = FirebaseInstance.auth
    var currentUserAuth = auth.currentUser

    fun registerUser(
        username: String,
        email: String,
        password: String,
        context: Context,
        activity: AutthenciateScreen
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    // Khai báo lại để lấy currentUser vì khi khai báo lần đầu khi khởi tạo nó sẽ là null
                    currentUserAuth = auth.currentUser
                    val user = User(
                        userId = currentUserAuth?.uid ?: "",
                        username = username,
                        email = currentUserAuth?.email
                    )
                    addNewUser(user)

                    context.startActivity(Intent(context, MainActivity::class.java))
                } else {
                    Toast.makeText(
                        context,
                        "Register Failed: ${it.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    //    fun loginUser(email: String, password: String, context: Context, activity: AutthenciateScreen) {
//        auth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    // Đăng nhập thành công
//                    currentUserAuth = auth.currentUser
//                    Toast.makeText(
//                        context,
//                        "Login Successfully: ${currentUserAuth?.email}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    Log.i("Firebase", "Login Successfully: ${currentUserAuth?.email}")
//                    context.startActivity(Intent(context, MainActivity::class.java))
//                } else {
//                    // Đăng nhập thất bại
//                    Toast.makeText(
//                        context,
//                        "Login Failed: ${task.exception?.message}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//    }
    suspend fun loginUser(email: String, password: String, context: Context) {
        try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            // Đăng nhập thành công
            currentUserAuth = result.user
            println("___DANG NHAP THANH CONG")
            Toast.makeText(
                context,
                "Login Successfully: ${currentUserAuth?.email}",
                Toast.LENGTH_SHORT
            ).show()
            Log.i("Firebase", "Login Successfully: ${currentUserAuth?.email}")
        } catch (e: Exception) {
            // Đăng nhập thất bại
            Toast.makeText(
                context,
                "Login Failed: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

//    fun getUser(callback: (User?) -> Unit) {
//        val currentUserId = currentUserAuth?.uid
//        if (currentUserId != null) {
//            userCollectionRef
//                .whereEqualTo("userId", currentUserId)
//                .get()
//                .addOnSuccessListener { querySnapshot ->
//
//                    if (!querySnapshot.isEmpty) {
//                        // Lấy tài liệu đầu tiên
//                        val document = querySnapshot.documents[0]
//                        val user = document.toObject(User::class.java)
//                        println("DA GET DC USER")
//                        callback(user) // user có thể là null
//                    } else {
//                        callback(null) // Trả về null nếu không có tài liệu nào
//                    }
//
//                }
//                .addOnFailureListener {
//                    callback(null) // Trả về null nếu xảy ra lỗi
//                }
//        } else {
//            callback(null) // Trả về null nếu người dùng chưa đăng nhập
//        }
//    }

    suspend fun getUser(context: Context, callback: (User?) -> Unit) {
        var user: User? = null
        val currentUserId = currentUserAuth?.uid
        if (currentUserId != null) {
            val document = userCollectionRef.document(currentUserId).get().await()
            user = document.toObject<User>(User::class.java)
            println("___da nhan dc user = ${user?.username}")
        }
        callback(user)

//        context.startActivity(Intent(context, MainActivity::class.java))
    }

    fun signOutUser(context: Context, activity: MainActivity) {
        auth.signOut()
        context.startActivity(Intent(context, AutthenciateScreen::class.java))
        activity.finish()
    }

    fun addNewUser(user: User) {
        userCollectionRef
            .document(user.userId)
            .set(user)
            .addOnSuccessListener {
                Log.d("Firestore", "Add Successfully")
            }
    }

    fun sendFriendRequest(requestReceiverId: String, context: Context) {
        val friendRequest = FriendRequest(
            requestSenderId = currentUserAuth?.uid,
            requestReceiverId = requestReceiverId,
            requestDate = Timestamp(
                Date()
            )
        )
        friendRequest.friendRequestId = friendRequestCollectionRef.document().id
        friendRequestCollectionRef
            .add(friendRequest)
            .addOnSuccessListener {
                Toast.makeText(context, "Request sent", Toast.LENGTH_SHORT)
            }
            .addOnFailureListener {
                Toast.makeText(context, "Can not send friend request", Toast.LENGTH_SHORT)
            }
    }

    //    fun getFriendRequests(listener: (List<User>) -> Unit) {
//        friendRequestCollectionRef
//            .whereEqualTo("requestReceiverId", currentUserAuth?.uid)
//            .orderBy("requestDate", Query.Direction.DESCENDING)
//            .addSnapshotListener { snapshot, exception ->
//                if (exception != null) {
//                    Log.w("Firestore", "Listen failed", exception)
//                    return@addSnapshotListener
//                }
//                if (snapshot != null && !snapshot.isEmpty) {
//                    println("DA NHAN DC FRIEND REQUEST")
//                    val requestSenderIds = snapshot.documents.mapNotNull {
//                        it.getString("requestSenderId")
//                    }
//                    userCollectionRef
//                        .whereIn("userId", requestSenderIds)
//                        .get()
//                        .addOnSuccessListener {
//                            val requestSenders = it.toObjects<User>(User::class.java)
//                            println("FRIEND REQUEST USERS")
//                            listener(requestSenders)
//                        }
//                        .addOnFailureListener { e ->
//                            Log.e("FirestoreError", "Error fetching user details: ${e.message}")
//                        }
//                }
//            }
//    }
    fun getFriendRequests(listener: (List<User>) -> Unit) {
        friendRequestCollectionRef
            .whereEqualTo("requestReceiverId", currentUserAuth?.uid)
            .orderBy("requestDate", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    Log.w("Firestore", "Listen failed", exception)
                    return@addSnapshotListener
                }
                if (snapshot != null && !snapshot.isEmpty) {
                    println("DA NHAN DC FRIEND REQUEST")
                    val requestSenderIds = snapshot.documents.mapNotNull {
                        it.getString("requestSenderId")
                    }
                    userCollectionRef
                        .whereIn("userId", requestSenderIds)
                        .get()
                        .addOnSuccessListener {
                            val requestSenders = it.toObjects<User>(User::class.java)
                            println("FRIEND REQUEST USERS")
                            listener(requestSenders)
                        }
                        .addOnFailureListener { e ->
                            Log.e("FirestoreError", "Error fetching user details: ${e.message}")
                        }
                }
            }
        println("EII YOPOOOO")
    }

    suspend fun findUsers(userNameQuery: String): List<User> {
        var foundUserList = mutableListOf<User>()
        if (userNameQuery != "" && !userNameQuery.isEmpty()) {
            val querySnapshot = userCollectionRef.get().await()

            for (document in querySnapshot) {
                println()
                val name = document.getString("username")
                if (name != null && name != "" && name.lowercase()
                        .contains(userNameQuery.lowercase())
                    && document.getString("userId") != currentUserAuth?.uid
                ) {
                    foundUserList.add(document.toObject(User::class.java))
                }
            }
        }

        return foundUserList
    }

    fun addFriend(friend: User) {
        val friendCollectionRef =
            userCollectionRef.document(currentUserAuth?.uid ?: "").collection("friends")
        friendCollectionRef.add(friend.userId)
    }

}