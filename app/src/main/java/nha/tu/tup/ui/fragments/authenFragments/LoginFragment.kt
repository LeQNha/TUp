package nha.tu.tup.ui.fragments.authenFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import nha.tu.tup.AutthenciateScreen
import nha.tu.tup.R
import nha.tu.tup.databinding.FragmentLoginBinding
import nha.tu.tup.ui.acitvities.BaseActivity
import nha.tu.tup.ui.acitvities.MainActivity
import nha.tu.tup.viewmodels.UserViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userViewModel = (activity as AutthenciateScreen).userViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onclickListenerSetUp()
    }

    private fun onclickListenerSetUp() {
//        binding.backButton.setOnClickListener {
//        }
        binding.authLoginButton.setOnClickListener {
            if (!binding.emailEditText.text.isEmpty() && !binding.passwordEditText.text.isEmpty()) {
                val authenticateActivity = activity as? AutthenciateScreen
                if (authenticateActivity != null) {
                    userViewModel.login(
                        binding.emailEditText.text.toString(),
                        binding.passwordEditText.text.toString(),
                        requireContext(),
                        authenticateActivity
                    )
                }
            } else {
                Toast.makeText(requireContext(), "Empty field!!!", Toast.LENGTH_SHORT).show()
            }
//            val intent = Intent(requireContext(), MainActivity::class.java)
//            startActivity(intent)
        }
        binding.registerNowBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }
}