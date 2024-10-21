package nha.tu.tup.ui.acitvities.team

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import nha.tu.tup.R
import nha.tu.tup.adapters.SeeAllMemberAdapter2
import nha.tu.tup.databinding.ActivityCreateNewTeamBinding
import nha.tu.tup.models.Team
import nha.tu.tup.repository.TeamRepository
import nha.tu.tup.ui.acitvities.BaseActivity
import nha.tu.tup.ui.fragments.dialogFragments.AddNewMemberDialogFragment
import nha.tu.tup.viewmodels.TeamViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

class CreateNewTeam : BaseActivity() {

    private lateinit var binding : ActivityCreateNewTeamBinding
//    lateinit var teamViewModel: TeamViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNewTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val teamRepository = TeamRepository()
//        val teamViewModelProviderFactory = TeamViewModel.TeamViewModelFactory(teamRepository)
//        teamViewModel = ViewModelProvider(this, teamViewModelProviderFactory).get(TeamViewModel::class.java)

        memberRvSetUp()
        onClickListernerSetUp()
    }

    private fun onClickListernerSetUp(){
        binding.addNewMemberFab.setOnClickListener {
            openAddNewMembersDialogFragment()
        }
        binding.saveBtn.setOnClickListener {
            val teamName = binding.editTxtTeamName.text.toString()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") // Định dạng ngày
            val dateString = LocalDate.now().format(formatter)

//            val teamData = hashMapOf(
//                "teamName" to teamName,
//                "createdDate" to FieldValue.serverTimestamp() // Sử dụng serverTimestamp cho thời gian
//            )
            val team = Team(teamName = teamName, createdDate = Timestamp(Date()))
            teamViewModel.addNewTeam(team)
            binding.editTxtTeamName.text.clear()

            onBackPressed()
        }
    }

    private fun memberRvSetUp() {
        val nameList = listOf<String>(
            "Abburt",
            "Enro",
            "Pigoco",
            "Oril",
            "Virel"
        )

        val memberAdapter2 = SeeAllMemberAdapter2(nameList)
        val memberRvLayoutManager = LinearLayoutManager(this)
        binding.teamMembersRv.apply {
            adapter = memberAdapter2
            layoutManager = memberRvLayoutManager
        }
        val dividerItemDecoration = DividerItemDecoration(binding.teamMembersRv.context, DividerItemDecoration.VERTICAL)
        binding.teamMembersRv.addItemDecoration(dividerItemDecoration)
    }

    private fun openAddNewMembersDialogFragment(){
        val dialog = AddNewMemberDialogFragment()
        dialog.show(supportFragmentManager, "AddNewMemberDialogFragment")
    }
}