package nha.tu.tup.ui.fragments.dialogFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import nha.tu.tup.R
import nha.tu.tup.databinding.FragmentSeeAllAssignedMembersDialogBinding
import nha.tu.tup.adapters.AssignedMembersAdapter

class SeeAllAssignedMembersDialogFragment : DialogFragment() {

    private var _binding : FragmentSeeAllAssignedMembersDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var assignedMembersAdapter: AssignedMembersAdapter

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
//        return inflater.inflate(R.layout.fragment_see_all_assigned_members_dialog, container, false)
        _binding = FragmentSeeAllAssignedMembersDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seeAllAssignedMemberRvSetUp()
        onCLickListenerSetUp()
    }

    private fun seeAllAssignedMemberRvSetUp(){
        val nameList = listOf<String>(
            "Abburt",
            "Enro",
            "Pigoco",
            "Oril",
            "Virel"
        )

        assignedMembersAdapter = AssignedMembersAdapter(nameList)
        val assignedMemberLayoutManager = LinearLayoutManager(requireContext())

        binding.seeAllAssignedMembersRv.apply {
            layoutManager = assignedMemberLayoutManager
            adapter = assignedMembersAdapter
        }
    }

    private fun onCLickListenerSetUp(){
        binding.closeSeeAllAssigedMembersDialogFragmentBtn.setOnClickListener {
            dismiss()
        }
    }

}