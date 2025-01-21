package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.theme.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState> = _state

    private val validEmail = "taller@gmail.com"
    private val validPassword = "123"

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = LoginState.Loading

            if (email.isEmailInvalid()) {
                _state.value = LoginState.Failed("Email is invalid")
                return@launch
            }

            if (password.isPasswordInvalid()) {
                _state.value = LoginState.Failed("Password is invalid")
                return@launch
            }

            delay(3000L) // Simulates login

            try {
                _state.value = LoginState.Success
            } catch (e: Exception) {
                _state.value = LoginState.Failed(e.message.toString())
            }
        }
    }

    // To be implemented - To mock the check for email
    private fun String.isEmailInvalid(): Boolean = this != validEmail
    // To be implemented - To mock the check for password
    private fun String.isPasswordInvalid(): Boolean = this != validPassword
}