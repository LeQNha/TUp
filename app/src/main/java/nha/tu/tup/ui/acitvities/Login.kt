package nha.tu.tup.ui.acitvities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import nha.tu.tup.R
import nha.tu.tup.databinding.ActivityLoginBinding

class Login : AppCompatActivity(){
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginAuth()
    }

    private fun loginAuth(){
        binding.authLoginButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}