package nha.tu.tup.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.tabs.TabLayoutMediator
import nha.tu.tup.R
import nha.tu.tup.databinding.FragmentFriendBinding
import nha.tu.tup.adapters.FriendViewPagerAdapter

class FriendFragment : Fragment() {

    private var _binding: FragmentFriendBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_friend, container, false)
        _binding = FragmentFriendBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager2SetUp()
    }

    private fun viewPager2SetUp() {
        val friendViewPagerAdapter = FriendViewPagerAdapter(this)
        binding.friendViewPager2.adapter = friendViewPagerAdapter

        TabLayoutMediator(binding.friendTabLayout, binding.friendViewPager2) { tab, position ->
//            tab.icon = when(position){
//                0 -> ContextCompat.getDrawable(requireContext(), R.drawable.baseline_people_outline_32)
//                1 -> ContextCompat.getDrawable(requireContext(), R.drawable.baseline_search_32)
//                2 -> ContextCompat.getDrawable(requireContext(), R.drawable.baseline_person_add_alt_32)
//                else -> null
//            }
            when(position){
                0 -> tab.setIcon(R.drawable.ic_friends)
                1 -> tab.setIcon(R.drawable.ic_search_friend)
                2 -> tab.setIcon(R.drawable.ic_add_friend)
                else -> null
            }
        }.attach()

//        for(i in 0..3){
//            val itemView = LayoutInflater.from(requireContext()).inflate(R.layout.friend_tab_item, null) as ImageView
//            binding.friendTabLayout.getTabAt(i)?.customView = itemView
//        }
    }
}