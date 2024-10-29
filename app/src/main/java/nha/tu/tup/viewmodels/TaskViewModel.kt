package nha.tu.tup.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nha.tu.tup.models.Task
import nha.tu.tup.repository.TaskRepository
import nha.tu.tup.repository.TeamRepository

class TaskViewModel(val taskRepository: TaskRepository) : ViewModel() {
    var _tasks: MutableLiveData<List<Task>> = MutableLiveData()

    fun addNewTask(task: Task) =
        taskRepository.addNewTask(task)


    fun getTasks(teamId: String) {
        taskRepository.getTasks(teamId) {
            _tasks.postValue(it)
        }
    }

    class TaskViewModelFactory(private val taskRepository: TaskRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TaskViewModel(taskRepository) as T
        }
    }
}