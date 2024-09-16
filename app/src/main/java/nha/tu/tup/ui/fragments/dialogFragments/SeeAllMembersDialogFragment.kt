package nha.tu.tup.ui.fragments.dialogFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import nha.tu.tup.R
import nha.tu.tup.databinding.FragmentSeeAllMembersDialogBinding
import nha.tu.tup.ui.adapters.SeeAllMemberAdapter2

class SeeAllMembersDialogFragment : DialogFragment() {

    private var _binding: FragmentSeeAllMembersDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var memberAdapter2: SeeAllMemberAdapter2

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

        _binding = FragmentSeeAllMembersDialogBinding.inflate(inflater, container, false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_see_all_members_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)

        onClickListenerSetUp()
        memberRvSetUp()
    }

    private fun onClickListenerSetUp() {
        binding.closeSeeAllMemberDialogFragmentBtn.setOnClickListener {
            dismiss()
        }
        binding.addMemberFab.setOnClickListener {
            openAddNewMembersDialogFragment()
        }
    }

    private fun openAddNewMembersDialogFragment(){
        val dialog = AddNewMemberDialogFragment()
        dialog.show(parentFragmentManager, "AddNewMemberDialogFragmentDialogFragment")
    }

    private fun memberRvSetUp() {
        val nameList = listOf<String>(
            "Abburt",
            "Enro",
            "Pigoco",
            "Oril",
            "Virel"
        )

        memberAdapter2 = SeeAllMemberAdapter2(nameList)
        val memberRvLayoutManager = LinearLayoutManager(requireContext())
        binding.seeAllMembersRv.apply {
            adapter = memberAdapter2
            layoutManager = memberRvLayoutManager
        }
    }
}