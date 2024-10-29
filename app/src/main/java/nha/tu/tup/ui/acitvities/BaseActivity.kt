package nha.tu.tup.ui.acitvities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import nha.tu.tup.R
import nha.tu.tup.repository.TaskRepository
import nha.tu.tup.repository.TeamRepository
import nha.tu.tup.repository.UserRepository
import nha.tu.tup.viewmodels.TaskViewModel
import nha.tu.tup.viewmodels.TeamViewModel
import nha.tu.tup.viewmodels.UserViewModel
open class BaseActivity : AppCompatActivity() {
    lateinit var teamViewModel: TeamViewModel
    lateinit var taskViewModel: TaskViewModel
    lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        teamViewModelSetUp()
        taskViewModelSetUp()
        userViewModelSetUp()
    }
    fun teamViewModelSetUp(){
        val teamRepository = TeamRepository()
        val teamViewModelProviderFactory = TeamViewModel.TeamViewModelFactory(teamRepository)
        teamViewModel = ViewModelProvider(this, teamViewModelProviderFactory).get(TeamViewModel::class.java)
    }
    private fun taskViewModelSetUp() {
        val taskRepository = TaskRepository()
        val taskViewModelProviderFactory = TaskViewModel.TaskViewModelFactory(taskRepository)
        taskViewModel = ViewModelProvider(this, taskViewModelProviderFactory).get(TaskViewModel::class.java)
    }
    fun userViewModelSetUp(){
        val userRepository = UserRepository()
        val userViewModelProviderFactory = UserViewModel.UserViewModelFactory(userRepository)
        userViewModel = ViewModelProvider(this , userViewModelProviderFactory).get(UserViewModel::class.java)
    }
}