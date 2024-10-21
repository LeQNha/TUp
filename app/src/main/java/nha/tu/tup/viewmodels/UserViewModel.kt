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

class UserViewModel(val userRepository: UserRepository): ViewModel() {
    var _users: MutableLiveData<List<User>> = MutableLiveData()

    fun register(username: String, email: String, password: String, context: Context, activity: AutthenciateScreen) = viewModelScope.launch {
        userRepository.registerUser(username, email, password, context, activity)
    }

    fun login(email: String, password: String, context: Context, activity: AutthenciateScreen) = viewModelScope.launch {
        userRepository.loginUser(email, password, context, activity)
    }

    fun signOut(context: Context, activity: MainActivity) = userRepository.signOutUser(context, activity)

    class UserViewModelFactory(val userRepository: UserRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserViewModel(userRepository) as T
        }
    }

}