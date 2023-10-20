package com.example.percobaan_login


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.percobaan_login.databinding.FragmentLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: FragmentLoginBinding

   lateinit var email : EditText
    lateinit var pass: EditText
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        email = binding.etEmail
        pass = binding.etKataSandi
        button = binding.tombolMasuk

        binding.tombolMasuk.setOnClickListener(View.OnClickListener {
            if (email.text.toString() == "user" && pass.text.toString() == "1234") {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
            }
        })
        binding.pertanyaanDaftar.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
    }
}
