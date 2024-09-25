package nha.tu.tup.ui.fragments.friendFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import nha.tu.tup.R
import nha.tu.tup.databinding.FragmentFriendListBinding
import nha.tu.tup.adapters.FriendListAdapter
import nha.tu.tup.adapters.SeeAllMemberAdapter2

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FriendListFragment : Fragment() {

    private var _binding : FragmentFriendListBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_friend_list, container, false)
        _binding = FragmentFriendListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        friendListRvSetUp()
    }

    private fun friendListRvSetUp() {
        val nameList = listOf<String>(
            "Abburt",
            "Enro Envage",
            "Pigoco Urg",
            "Oril",
            "Virel",
            "Alus Namie",
            "Maron Holrer"
        )

        val friendListAdapter = FriendListAdapter(nameList)
        val friendListRvLayoutManager = LinearLayoutManager(requireContext())
        binding.friendListRv.apply {
            adapter = friendListAdapter
            layoutManager = friendListRvLayoutManager
        }
    }
}