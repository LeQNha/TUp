package nha.tu.tup.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nha.tu.tup.AutthenciateScreen
import nha.tu.tup.models.User
import nha.tu.tup.repository.UserRepository
import nha.tu.tup.ui.acitvities.MainActivity

class UserViewModel(val userRepository: UserRepository) : ViewModel() {
    var _users: MutableLiveData<List<User>> = MutableLiveData()
    var _friendRequestSenders: MutableLiveData<List<User>> = MutableLiveData()
    var _foundUsers: MutableLiveData<List<User>> = MutableLiveData()
    var currentUser: User? = null
    fun register(
        username: String,
        email: String,
        password: String,
        context: Context,
        activity: AutthenciateScreen
    ) =
        userRepository.registerUser(username, email, password, context, activity)


    fun login(email: String, password: String, context: Context, activity: AutthenciateScreen){
        userRepository.loginUser(email, password, context, activity)
    }

    fun signOut(context: Context, activity: MainActivity) =
        userRepository.signOutUser(context, activity)

    fun getUser() = userRepository.getUser {
        currentUser = it
        println("_USER getUser = ${currentUser?.username}")
    }

    fun sendFriendRequest(requestReceiverId: String, context: Context) =
        userRepository.sendFriendRequest(
            requestReceiverId = requestReceiverId,
            context = context
        )

    fun getFriendRequests(){
        userRepository.getFriendRequests {
            _friendRequestSenders.postValue(it)
            println("thiet lap _friendRequestSenders")
        }
    }

    fun findUsers(userNameQuery: String) = viewModelScope.launch {
        _foundUsers.postValue(userRepository.findUsers(userNameQuery))
    }

    class UserViewModelFactory(val userRepository: UserRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserViewModel(userRepository) as T
        }
    }

}