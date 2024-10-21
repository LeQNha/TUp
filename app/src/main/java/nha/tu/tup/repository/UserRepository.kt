package nha.tu.tup.repository

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.toObject
import nha.tu.tup.AutthenciateScreen
import nha.tu.tup.firebase.FirebaseInstance
import nha.tu.tup.models.User
import nha.tu.tup.ui.acitvities.MainActivity

class UserRepository {
    val userCollectionRef = FirebaseInstance.firebaseFirestoreInstance.collection("users")
    val auth = FirebaseInstance.auth

    suspend fun registerUser(username: String, email: String, password: String, context: Context, activity: AutthenciateScreen) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val currentUser = auth.currentUser
                    val user = User(
                        userId = currentUser?.uid,
                        username = username,
                        email = currentUser?.email
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

    suspend fun loginUser(email: String, password: String, context: Context, activity: AutthenciateScreen) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Đăng nhập thành công
                    val user = auth.currentUser
                    Toast.makeText(
                        context,
                        "Login Successfully: ${user?.email}",
                        Toast.LENGTH_SHORT
                    ).show()
                    context.startActivity(Intent(context, MainActivity::class.java))
                } else {
                    // Đăng nhập thất bại
                    Toast.makeText(
                        context,
                        "Login Failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    suspend fun getUser(callback: (User?) -> Unit) {
        val currentUser = auth.currentUser
        val currentUserId = currentUser?.uid
        if (currentUserId != null) {
            userCollectionRef
                .document(currentUserId)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val user = document.toObject(User::class.java)
                        callback(user)
                    } else {
                        callback(null)
                    }
                }
                .addOnFailureListener {
                    callback(null) // Trả về null nếu xảy ra lỗi
                }
        } else {
            callback(null) // Trả về null nếu người dùng chưa đăng nhập
        }
    }

    fun signOutUser(context: Context, activity: MainActivity) {
        auth.signOut()
        context.startActivity(Intent(context, AutthenciateScreen::class.java))
        activity.finish()
    }

    fun addNewUser(user: User) {
        userCollectionRef
            .add(user)
            .addOnSuccessListener {
                Log.d("Firestore", "Add Successfully")
            }
    }

}