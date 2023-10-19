package com.example.percobaan_login

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.example.percobaan_login.databinding.FragmentRegisterBinding
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener,
    View.OnKeyListener {
    private lateinit var mBinding: FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = FragmentRegisterBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        mBinding.etNamaLengkap.onFocusChangeListener = this
        mBinding.etEmail.onFocusChangeListener = this
        mBinding.etKataSandi.onFocusChangeListener = this
        mBinding.etKonfirmasiKataSandi.onFocusChangeListener = this
    }

    private fun showError(binding: TextInputLayout, errorMessage: String) {
        binding.apply {
            isErrorEnabled = true
            error = errorMessage
        }
    }

    private fun clearError(binding: TextInputLayout) {
        binding.isErrorEnabled = false
    }

    private fun validateFullName(): Boolean {
        val value = mBinding.etNamaLengkap.text.toString()
        if (value.isEmpty()) {
            showError(mBinding.tilKataSandi, "Nama lengkap diperlukan")
            return false
        }
        clearError(mBinding.tilNamaLengkap)
        return true
    }

    private fun validateEmail(): Boolean {
        val value = mBinding.etEmail.text.toString()
        if (value.isEmpty()) {
            showError(mBinding.tilEmail, "Email diperlukan")
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            showError(mBinding.tilEmail, "Email tidak valid")
            return false
        }
        clearError(mBinding.tilEmail)
        return true
    }

    private fun validatePassword(): Boolean {
        val value = mBinding.etKataSandi.text.toString()
        if (value.isEmpty()) {
            showError(mBinding.tilKataSandi, "Isi Password terlebih dahulu")
            return false
        } else if (value.length < 8) {
            showError(mBinding.tilKataSandi, "Password Minimal 8 Karakter")
            return false
        }
        clearError(mBinding.tilKataSandi)
        return true
    }

    private fun validateConfirmPassword(): Boolean {
        val value = mBinding.etKonfirmasiKataSandi.text.toString()
        if (value.isEmpty()) {
            showError(mBinding.tilKataSandi, "isi konfirmasi Password")
            return false
        } else if (value.length < 8) {
            showError(mBinding.tilKonfirmasiKataSandi, "Password konfirmasi Minimal 8 Karakter")
            return false
        }
        clearError(mBinding.tilKonfirmasiKataSandi)
        return true
    }
        private fun validatePasswordAndConfirmPassword(): Boolean {
            val password = mBinding.etKataSandi.text.toString()
            val confirmPassword = mBinding.etKonfirmasiKataSandi.text.toString()
            if (password != confirmPassword) {
                showError(mBinding.tilKonfirmasiKataSandi, "Password tidak sesuai")
                return false
            }
            clearError(mBinding.tilKonfirmasiKataSandi)
            return true
        }

        override fun onClick(view: View?) {
            // Handle button clicks or other UI interactions here
        }

        override fun onFocusChange(view: View?, hasFocus: Boolean) {
            if (view != null) {
                when (view.id) {
                    R.id.et_nama_lengkap -> {
                        if (hasFocus && mBinding.tilNamaLengkap.isErrorEnabled) {
                            clearError(mBinding.tilNamaLengkap)
                        } else {
                            validateFullName()
                        }
                    }

                    R.id.et_email -> {
                        if (hasFocus && mBinding.tilEmail.isErrorEnabled) {
                            clearError(mBinding.tilEmail)
                        } else {
                            validateEmail()
                        }
                    }

                    R.id.et_kata_sandi -> {
                        if (hasFocus && mBinding.tilKataSandi.isErrorEnabled) {
                            clearError(mBinding.tilKataSandi)
                        } else {
                            if (validatePassword() && mBinding.etKonfirmasiKataSandi.text!!.isNotEmpty() &&
                                validateConfirmPassword() && validatePasswordAndConfirmPassword()
                            ) {
                                if (mBinding.tilKonfirmasiKataSandi.isErrorEnabled) {
                                    clearError(mBinding.tilKonfirmasiKataSandi)
                                }
                                mBinding.tilKonfirmasiKataSandi.apply {
                                    setStartIconDrawable(R.drawable.baseline_check_circle_24)
                                    setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                                }
                            }
                        }
                    }

                    R.id.et_konfirmasi_kata_sandi -> {
                        if (hasFocus && mBinding.tilKonfirmasiKataSandi.isErrorEnabled) {
                            clearError(mBinding.tilKonfirmasiKataSandi)
                        } else {
                            if (validateConfirmPassword() && validatePassword() && validatePasswordAndConfirmPassword()) {
                                if (mBinding.tilKonfirmasiKataSandi.isErrorEnabled) {
                                    clearError(mBinding.tilKonfirmasiKataSandi)
                                }
                                mBinding.tilKonfirmasiKataSandi.apply {
                                    setStartIconDrawable(R.drawable.baseline_check_circle_24)
                                    setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                                }
                            }
                        }
                    }
                }
            }
        }

        override fun onKey(view: View?, event: Int, keyEvent: KeyEvent?): Boolean {
            // Handle key events, e.g., Enter key
            return false
        }

    }
