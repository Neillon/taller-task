package com.example.myapplication.ui.theme

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Failed(val message: String) : LoginState()
}