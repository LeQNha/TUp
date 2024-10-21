package nha.tu.tup.ui.fragments.authenFragments

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
import nha.tu.tup.databinding.FragmentSignUpBinding
import nha.tu.tup.viewmodels.UserViewModel

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
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
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickListenerSetUp()
    }

    private fun onClickListenerSetUp() {
        binding.authRegisterButton.setOnClickListener {
            if (!binding.usernameEditText.text.isEmpty() && !binding.emailEditText.text.isEmpty() && !binding.passwordEditText.text.isEmpty() && !binding.confirmPasswordEditText.text.isEmpty()) {
                if (binding.passwordEditText.text.toString() != binding.confirmPasswordEditText.text.toString()) {
                    Toast.makeText(requireContext(), "Reconfirm password!!!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    userViewModel.register(
                        binding.usernameEditText.text.toString(),
                        binding.emailEditText.text.toString(),
                        binding.passwordEditText.text.toString(),
                        requireContext(),
                        requireActivity() as AutthenciateScreen
                    )
                }
            } else {
                Toast.makeText(requireContext(), "Empty field!!!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.loginNowBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }
}