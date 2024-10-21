package nha.tu.tup.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nha.tu.tup.models.Team
import nha.tu.tup.repository.TeamRepository

class TeamViewModel(val teamRepository: TeamRepository): ViewModel() {

    var _teams :MutableLiveData<List<Team>> = MutableLiveData()

//    init {
//        getTeams()
//    }

    fun addNewTeam(team: Team) = viewModelScope.launch {
        teamRepository.addNewTeam(team)
    }

    fun getTeams() {
        teamRepository.getTeams { teamList ->
            _teams.postValue(teamList)
        }
    }

    class TeamViewModelFactory(private val teamRepository: TeamRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(TeamViewModel::class.java)) {
//                @Suppress("UNCHECKED_CAST")
//                return TeamViewModel(teamRepository) as T
//            }
//            throw IllegalArgumentException("Unknown ViewModel class")
            return TeamViewModel(teamRepository) as T
        }
    }
}