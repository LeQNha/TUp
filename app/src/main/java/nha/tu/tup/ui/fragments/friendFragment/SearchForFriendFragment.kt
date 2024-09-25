package nha.tu.tup.ui.fragments.friendFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import nha.tu.tup.R
import nha.tu.tup.databinding.FragmentHomeBinding
import nha.tu.tup.databinding.FragmentSearchForFriendBinding
import nha.tu.tup.adapters.SearchFriendAdapter

class SearchForFriendFragment : Fragment() {

    private var _binding : FragmentSearchForFriendBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_search_for_friend, container, false)
        _binding = FragmentSearchForFriendBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchFriendRvSetUp()
    }

    private fun searchFriendRvSetUp(){
        val users = listOf(
            "Jackie Mamiya",
            "Megan Hasselblad",
            "Christopher Taylor",
            "Dold Nalo"
        )

        val searchFriendAdapter = SearchFriendAdapter(users)
        val searchFriendLayoutManager = LinearLayoutManager(requireContext())
        binding.searchFriendRv.apply {
            layoutManager = searchFriendLayoutManager
            adapter = searchFriendAdapter
        }
    }
}