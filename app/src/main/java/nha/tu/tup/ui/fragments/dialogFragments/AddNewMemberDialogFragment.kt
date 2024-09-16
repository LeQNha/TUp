package nha.tu.tup.ui.fragments.dialogFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import nha.tu.tup.R
import nha.tu.tup.databinding.FragmentAddNewMemberDialogBinding
import nha.tu.tup.ui.adapters.MemberAddAdapter

class AddNewMemberDialogFragment : DialogFragment() {

    private var _binding: FragmentAddNewMemberDialogBinding? = null
    private val binding get() = _binding!!

    lateinit private var memberAddAdapter: MemberAddAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_add_new_member_dialog, container, false)

        _binding = FragmentAddNewMemberDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        // Lấy ra window của dialog và chỉnh kích thước
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog?.window?.setBackgroundDrawable(resources.getDrawable(R.drawable.rounded_corner_background))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addMemberRvSetUp()
        onClickListenerSetUp()
    }

    private fun onClickListenerSetUp() {
        binding.closeAddMemberDialogFragmentBtn.setOnClickListener {
            dismiss()
        }
    }

    private fun addMemberRvSetUp() {
        val nameList = listOf<String>(
            "Abburt",
            "Enro",
            "Pigoco",
            "Oril",
            "Virel"
        )

        memberAddAdapter = MemberAddAdapter(nameList)
        val memberRvLayoutManager = LinearLayoutManager(requireContext())
        binding.addMembersRv.apply {
            adapter = memberAddAdapter
            layoutManager = memberRvLayoutManager
        }
    }

}