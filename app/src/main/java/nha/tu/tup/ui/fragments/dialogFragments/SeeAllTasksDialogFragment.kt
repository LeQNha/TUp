package nha.tu.tup.ui.fragments.dialogFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import nha.tu.tup.R
import nha.tu.tup.databinding.FragmentSeeAllTasksDialogBinding
import nha.tu.tup.models.Task
import nha.tu.tup.ui.acitvities.task.CreateNewTask
import nha.tu.tup.ui.acitvities.task.LeaderTaskScreen
import nha.tu.tup.adapters.TeamTasksAdapter

class SeeAllTasksDialogFragment : DialogFragment() {

    private var _binding: FragmentSeeAllTasksDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var teamTasksAdapter: TeamTasksAdapter


    override fun onStart() {
        super.onStart()
        // Lấy ra window của dialog và chỉnh kích thước
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog?.window?.setBackgroundDrawable(resources.getDrawable(R.drawable.rounded_corner_background))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_see_all_tasks_dialog, container, false)
        _binding = FragmentSeeAllTasksDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskListRvSetUp()
        onClickListenerSetUp()
    }

    private fun taskListRvSetUp(){
        val taskList = listOf<Task>(
            Task("Learn material", "22h", 3),
            Task("Assemble the part", "3d", 2),
            Task("Write the code", "1w", 4),
            Task("Buy hat", "4h", 1),
            Task("Write report", "1m", 2),
            Task("Set up", "1w", 4)
        )

        teamTasksAdapter = TeamTasksAdapter(taskList)
        val taskListLayoutManager = LinearLayoutManager(requireContext())
        binding.seeAllTaskRv.apply {
            layoutManager = taskListLayoutManager
            adapter = teamTasksAdapter
        }

        teamTasksAdapter.setOnItemClickListener {
            val intent = Intent(requireContext(), LeaderTaskScreen::class.java)
            startActivity(intent)
        }
    }

    private fun onClickListenerSetUp() {
        binding.closeSeeAllTasksDialogFragmentBtn.setOnClickListener {
            dismiss()
        }
        binding.createNewTaskFab.setOnClickListener {
            val intent = Intent(requireContext(), CreateNewTask::class.java)
            startActivity(intent)
        }
    }
}