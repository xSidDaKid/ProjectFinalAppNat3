package com.example.quizzer.presentation.permission

import android.app.Dialog
import com.example.quizzer.domaine.entité.PermissionScore
import com.example.quizzer.domaine.entité.Quiz

interface IContratVuePresentateurPermission {

    interface IVuePermission{
        fun montrerDialog()

    }

    interface IPresentateurPermission{
        fun getTousPermissionsList(): Array<Pair<String, PermissionScore>>
        fun getPermission(position: Int): PermissionScore
        fun getListeQuiz(): Array<Quiz>
        fun dialogPermission(position: Int)
    }
}