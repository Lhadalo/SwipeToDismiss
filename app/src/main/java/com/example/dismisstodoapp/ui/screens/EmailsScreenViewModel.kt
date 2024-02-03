package com.example.dismisstodoapp.ui.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.dismisstodoapp.data.Email
import com.example.dismisstodoapp.data.demoEmails
import java.util.UUID

class EmailsScreenViewModel : ViewModel() {
    private val _emailsState = mutableStateListOf(*demoEmails)
    val emails: List<Email>
        get() = _emailsState

    fun removeEmail(email: Email): Boolean {
        return _emailsState.remove(email)
    }
}